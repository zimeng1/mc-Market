package org.ethh.marketMakerManager.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Synchronized;
import org.ethh.common.BaseDTO;
import org.ethh.common.cache.SimpleCache;
import org.ethh.common.enums.AlgorithmStatus;
import org.ethh.common.enums.AlgorithmStrategyOperate;
import org.ethh.dal.dto.StrategyOverviewDTO;
import org.ethh.dal.dto.StrategyParametersDTO;
import org.ethh.dal.entity.*;
import org.ethh.dal.mapper.*;
import org.ethh.dal.service.base.BasePageReq;
import org.ethh.dal.service.base.BasePageRsp;
import org.ethh.marketMakerManager.exception.GlobalException;
import org.ethh.marketMakerManager.model.AccountInfoDTO;
import org.ethh.marketMakerManager.model.AlgorithmParametersDTO;
import org.ethh.marketMakerManager.model.AlgorithmStrategyDTO;
import org.ethh.marketMakerManager.model.AlgorithmStrategyInstanceDTO;
import org.ethh.marketMakerManager.model.covert.AlgorithmParametersCovert;
import org.ethh.marketMakerManager.model.covert.AlgorithmStrategyCovert;
import org.ethh.marketMakerManager.model.covert.AlgorithmStrategyInstanceCovert;
import org.ethh.marketMakerManager.model.request.AsCreateRequest;
import org.ethh.marketMakerManager.service.AlgorithmStrategyService;
import org.ethh.marketMakerManager.service.grpc.clinet.MarketGrpcClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

/**
 * @author wangyifei
 */
@Service
public class AlgorithmStrategyServiceImpl implements AlgorithmStrategyService {

    @Autowired
    private AlgorithmStrategyInstanceMapper algorithmStrategyInstanceMapper;

    @Autowired
    private AlgorithmStrategyMapper algorithmStrategyMapper;
    
    @Autowired
    private AlgorithmParametersMapper algorithmParametersMapper;

    @Autowired
    private TradingAccountMapper tradingAccountMapper;

    @Autowired
    private TradingProductMapper tradingProductMapper;

    @Autowired
    private BusinessLineMapper businessLineMapper;

    @Autowired
    private ExchangeMapper exchangeMapper;
    
    @Autowired
    private StrategyChangeLogMapper strategyChangeLogMapper;
    
    @Autowired
    private StrategyOperationLogMapper strategyOperationLogMapper;

    @Autowired
    private AlgorithmStrategyInstanceCovert algorithmStrategyInstanceCovert;

    @Autowired
    private AlgorithmStrategyCovert algorithmStrategyCovert;

    @Autowired
    private AlgorithmParametersCovert algorithmParametersCovert;

    @Autowired
    private MarketGrpcClientService marketGrpcClientService;

    // 定义每个操作允许的状态
    private static final Map<AlgorithmStrategyOperate, Set<AlgorithmStatus>> OPERATE_ALLOWED_STATUSES = new EnumMap<>(AlgorithmStrategyOperate.class);

    static {
        // 初始化操作与状态集合的映射
        OPERATE_ALLOWED_STATUSES.put(AlgorithmStrategyOperate.Deploy, EnumSet.of(AlgorithmStatus.Init));
        OPERATE_ALLOWED_STATUSES.put(AlgorithmStrategyOperate.Pause, EnumSet.of(AlgorithmStatus.Deployed, AlgorithmStatus.DeployedPosition, AlgorithmStatus.DeployedCloesed));
        OPERATE_ALLOWED_STATUSES.put(AlgorithmStrategyOperate.Recover, EnumSet.of(AlgorithmStatus.Paused));
        OPERATE_ALLOWED_STATUSES.put(AlgorithmStrategyOperate.Offline, EnumSet.of(AlgorithmStatus.Deployed, AlgorithmStatus.DeployedPosition, AlgorithmStatus.DeployedCloesed));
        OPERATE_ALLOWED_STATUSES.put(AlgorithmStrategyOperate.ForcedOffline, EnumSet.of(AlgorithmStatus.Paused));
    }

    @Override
    public AlgorithmStrategyInstanceDTO getById(Long id) {
        AlgorithmStrategyInstance dbData = algorithmStrategyInstanceMapper.getById(id);
        return algorithmStrategyInstanceCovert.toDTO(dbData);
    }

    @Transactional
    @Override
    public AlgorithmStrategyInstanceDTO create(AsCreateRequest asCreateRequest) {

        createPreCheck(asCreateRequest.getBusinessLineId(),
                asCreateRequest.getTradingAccountId(),
                asCreateRequest.getTradingProductId(),
                asCreateRequest.getExchangeId());

        // 1. 新增算法参数
        AlgorithmParametersDTO algorithmParametersDTO = new AlgorithmParametersDTO(null, null, null,asCreateRequest.getAlgorithmName(), JSON.parseObject(asCreateRequest.getAlgorithmArgs()));
        AlgorithmParameters algorithmParameters = algorithmParametersCovert.toDO(algorithmParametersDTO);
        algorithmParametersMapper.insert(algorithmParameters);

        // 2. 创建算法策略
        AlgorithmStrategyDTO algorithmStrategyDTO = AlgorithmStrategyDTO.builder()
                .exchangeId(asCreateRequest.getExchangeId())
                .algorithmParametersId(algorithmParameters.getId())
                .strategyName(asCreateRequest.getStrategyName())
                .tradingProductId(asCreateRequest.getPeriodId())
                .businessLineId(asCreateRequest.getBusinessLineId())
                .tradingAccountId(asCreateRequest.getTradingAccountId())
                .algorithmStatus(AlgorithmStatus.Init)
                .build();
        AlgorithmStrategy algorithmStrategy = algorithmStrategyCovert.toDO(algorithmStrategyDTO);
        algorithmStrategyMapper.insert(algorithmStrategy);

        // 3. 创建算法实例
        AlgorithmStrategyInstanceDTO instanceDTO = AlgorithmStrategyInstanceDTO.builder()
                .algorithmStrategyId(algorithmStrategy.getId())
                .build();
        AlgorithmStrategyInstance instance = algorithmStrategyInstanceCovert.toDO(instanceDTO);
        algorithmStrategyInstanceMapper.insert(instance);
        
        addOperationLog(algorithmStrategy.getId(),"create");
        return algorithmStrategyInstanceCovert.toDTO(instance);
    }
    
    @Override
    @Transactional
    public String delete(Long strategyInstanceId, Long algorithmStrategyId, Long algorithmParamId) {
        algorithmParametersMapper.delete(algorithmParamId);
        algorithmStrategyInstanceMapper.deleteById(strategyInstanceId);
        algorithmStrategyMapper.deleteById(algorithmStrategyId);
        return "success";
    }
    
    
    
    @Override
    @Synchronized
    public void operate(Long id, Long algorithmStrategyId, AlgorithmStrategyOperate operate) {
        AlgorithmStrategyInstance dbData = algorithmStrategyInstanceMapper.getById(id);
        Assert.notNull(dbData, "not found by id: " + id);

        AlgorithmStrategyInstanceDTO dto  = algorithmStrategyInstanceCovert.toDTO(dbData);

        Assert.isTrue(dto.getAlgorithmStrategyId().compareTo(algorithmStrategyId) == 0,
                "algorithmStrategyId is not match, dto " + JSON.toJSONString(dto) + "request: " + algorithmStrategyId);

        operatePreCheck(dto, operate);

        if (operate.equals(AlgorithmStrategyOperate.Deploy)) {
            dto.setDeployedAt(Date.from(Instant.now()));
        }
        dto.setAlgorithmStrategyOperate(operate);
        
        addOperationLog(algorithmStrategyId,operate.name());
        algorithmStrategyInstanceMapper.update(algorithmStrategyInstanceCovert.toDO(dto));
    }
    
    @Override
    public BasePageRsp<StrategyOverviewDTO> getStrategyData(BasePageReq req) {
        
        Page<StrategyOverviewDTO> page = new Page<>(req.getCurrent(),req.getSize());
        
        page = algorithmStrategyMapper.selectStrategyOverview(page);
        
        BasePageRsp<StrategyOverviewDTO> rsp = BasePageRsp.valueOf(page);
        
        Map<String, BaseDTO> map = SimpleCache.get(SimpleCache.CAPITAL);
        
        for (StrategyOverviewDTO strategyOverviewDTO : rsp.getRecords()) {
            if(map == null){
                continue;
            }
            AccountInfoDTO accountInfoDTO = (AccountInfoDTO)map.get(strategyOverviewDTO.getAccountId());
            if(accountInfoDTO == null){
                continue;
            }
            strategyOverviewDTO.setAccountValue(accountInfoDTO.getAccountEquity());
            strategyOverviewDTO.setAccountBalance(accountInfoDTO.getAccountBalance());
            strategyOverviewDTO.setAvailableMargin(accountInfoDTO.getAvailableMargin());
            strategyOverviewDTO.setMarginRatio(accountInfoDTO.getMarginRatio());
            strategyOverviewDTO.setFloatingPL(accountInfoDTO.getFloatingPnL());
            strategyOverviewDTO.setInitialCapital(strategyOverviewDTO.getInitialCapital().setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        
        return rsp;
    }
    
    @Override
    public BasePageRsp<StrategyOverviewDTO> getStrategyDataHistory(BasePageReq req) {
        
        Page<StrategyOverviewDTO> page = new Page<>(req.getCurrent(),req.getSize());
        
        page = algorithmStrategyMapper.selectStrategyOverviewUndeployed(page);
        
        BasePageRsp<StrategyOverviewDTO> rsp = BasePageRsp.valueOf(page);
        
        Map<String, BaseDTO> map = SimpleCache.get(SimpleCache.CAPITAL);
        
        for (StrategyOverviewDTO strategyOverviewDTO : rsp.getRecords()) {
            if(map == null){
                continue;
            }
            AccountInfoDTO accountInfoDTO = (AccountInfoDTO)map.get(strategyOverviewDTO.getAccountId());
            if(accountInfoDTO == null){
                continue;
            }
            strategyOverviewDTO.setAccountValue(accountInfoDTO.getAccountEquity());
            strategyOverviewDTO.setAccountBalance(accountInfoDTO.getAccountBalance());
            strategyOverviewDTO.setAvailableMargin(accountInfoDTO.getAvailableMargin());
            strategyOverviewDTO.setMarginRatio(accountInfoDTO.getMarginRatio());
            strategyOverviewDTO.setFloatingPL(accountInfoDTO.getFloatingPnL());
            strategyOverviewDTO.setInitialCapital(strategyOverviewDTO.getInitialCapital().setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        
        return rsp;
    }
    
    @Override
    public StrategyParametersDTO getStrategyParams(Long id) {
        return algorithmStrategyMapper.selectStrategyParams(id);
    }
    
    @Override
    @Transactional
    public String updateStrategyParams(Long id, String strategyParams) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("strategyParams",strategyParams);
        algorithmStrategyMapper.updateStrategyParams(map).toString();
        
        //更新操作日志
        StrategyChangeLogPO strategyChangeLogPO = new StrategyChangeLogPO();
        strategyChangeLogPO.setStrategyId(id);
        strategyChangeLogPO.setChangeContent(strategyParams);
        strategyChangeLogMapper.insert(strategyChangeLogPO);
        
        return "更新成功";
    }
    
    @Override
    public List<StrategyChangeLogPO> selectStrategyUpdateLog(Long strategyId) {
        QueryWrapper<StrategyChangeLogPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("strategy_id", strategyId);
        
        return strategyChangeLogMapper.selectList(queryWrapper);
    }
    
    @Override
    public List<StrategyOperationLogPO> selectStrategyOperateLog(Long strategyId) {
        QueryWrapper<StrategyOperationLogPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("strategy_id", strategyId);
        return strategyOperationLogMapper.selectList(queryWrapper);
    }
    
    
    private void createPreCheck(Long businessLineId, Long tradingAccountId, Long tradingProductId, Long exchangeId) {
        BusinessLine businessLine = businessLineMapper.getById(businessLineId);
        Assert.notNull(businessLine, "not found by id: " + businessLineId);

        TradingAccount tradingAccount = tradingAccountMapper.getById(tradingAccountId);
        Assert.notNull(tradingAccount, "not found by id: " + tradingAccountId);

        TradingProduct tradingProduct = tradingProductMapper.getById(tradingProductId);
        Assert.notNull(tradingProduct, "not found by id: " + tradingProductId);

        Exchange exchange = exchangeMapper.getById(exchangeId);
        Assert.notNull(exchange, "not found by id: " + exchangeId);
    }

    /**
     * 前置检查
     * @param dto 算法实例信息
     * @param operate 操作指令
     */
    private void operatePreCheck(AlgorithmStrategyInstanceDTO dto, AlgorithmStrategyOperate operate) {
        AlgorithmStrategy algorithmStrategy = algorithmStrategyMapper.getById(dto.getAlgorithmStrategyId());
        Assert.notNull(algorithmStrategy, "not found by id: " + dto.getAlgorithmStrategyId());

        AlgorithmStrategyDTO algorithmStrategyDTO = algorithmStrategyCovert.toDTO(algorithmStrategy);
        AlgorithmStatus currentStatus = algorithmStrategyDTO.getAlgorithmStatus();
        
        if(operate.equals(dto.getAlgorithmStrategyOperate())){
            throw new GlobalException("algorithmStrategyOperate 操作状态目前为: " + operate.name() + ".请等待算法状态更新");
        }

        if (currentStatus.equals(AlgorithmStatus.Undeploying) || currentStatus.equals(AlgorithmStatus.Undeployed)) {
            return;
        }

        // 检查操作是否合法
        Set<AlgorithmStatus> allowedStatuses = OPERATE_ALLOWED_STATUSES.get(operate);
        if (allowedStatuses == null || !allowedStatuses.contains(currentStatus)) {
            throw new GlobalException("algorithmStatus is not valid for operation: " + operate.name());
        }

        // 强制停止需要判断持仓已清空
        if (operate == AlgorithmStrategyOperate.ForcedOffline) {
            BigDecimal positionQuantity = positionQuantity(algorithmStrategyDTO);
            if (positionQuantity.compareTo(BigDecimal.ZERO) == 0) {
                throw new GlobalException("positionQuantity is zero, positionQuantity: " + positionQuantity);
            }
        }
    }

    private BigDecimal positionQuantity(AlgorithmStrategyDTO algorithmStrategyDTO) {
        TradingAccount tradingAccount = tradingAccountMapper.getById(algorithmStrategyDTO.getTradingAccountId());
        Assert.notNull(tradingAccount, "not found by id: " + algorithmStrategyDTO.getTradingAccountId());

        TradingProduct tradingProduct = tradingProductMapper.getById(algorithmStrategyDTO.getTradingProductId());
        Assert.notNull(tradingProduct, "not found by id: " + algorithmStrategyDTO.getTradingProductId());

         return marketGrpcClientService.getPositionQuantity(tradingProduct.getSymbol(), tradingAccount.getAccountId());
    }
    
    private void addOperationLog(Long strategyId,String content){
        StrategyOperationLogPO spl = new StrategyOperationLogPO();
        
        spl.setOperator("admin");
        spl.setOperationContent(content);
        spl.setOperationType(content);
        spl.setStrategyId(strategyId);

        strategyOperationLogMapper.insert(spl);
        
    }
    
}
