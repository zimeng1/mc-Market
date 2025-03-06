package org.ethh.marketMakerManager.controller;


import com.alibaba.fastjson2.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.ethh.common.annotation.StrategyOperateLog;
import org.ethh.common.enums.AlgorithmStrategyOperate;
import org.ethh.dal.dto.StrategyParametersDTO;
import org.ethh.dal.entity.StrategyChangeLogPO;
import org.ethh.dal.entity.StrategyOperationLogPO;
import org.ethh.marketMakerManager.model.AlgorithmStrategyInstanceDTO;
import org.ethh.marketMakerManager.model.Response.ApiResponse;
import org.ethh.marketMakerManager.model.request.AsCreateRequest;
import org.ethh.marketMakerManager.service.AlgorithmStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangyifei
 * 算法实例操作
 */
@Slf4j
@RestController
@Api(tags = "策略操作类")
@RequestMapping("/api/as_operate")
public class AlgorithmStrategyInstanceOperateController {

    @Autowired
    private AlgorithmStrategyService algorithmStrategyService;
    
    @ApiOperation("新建算法策略")
    @PostMapping("/create")
    @StrategyOperateLog
    public ApiResponse<AlgorithmStrategyInstanceDTO> create(@RequestBody AsCreateRequest request) {
        Assert.notNull(request, "request is null");
        Assert.isTrue(StringUtils.hasText(request.getAlgorithmName()), "algorithmName is empty");
        Assert.isTrue(StringUtils.hasText(request.getAlgorithmArgs()), "algorithmArgs is empty");
        Assert.notNull(request.getExchangeId(), "exchangeId is empty");
        Assert.notNull(request.getTradingAccountId(), "tradingAccountId is empty");
        Assert.notNull(request.getTradingProductId(), "tradingProductId is empty");
        Assert.notNull(request.getPeriodId(), "periodId is empty");
        Assert.notNull(request.getBusinessLineId(), "businessLineId is empty");
        Assert.notNull(request.getStrategyName(), "StrategyName is empty");

        AlgorithmStrategyInstanceDTO asDto = algorithmStrategyService.create(request);

        return ApiResponse.success(asDto);
    }
    
    @ApiOperation("删除算法策略")
    @PostMapping("/delete")
    @StrategyOperateLog
    public ApiResponse<String> delete(@ApiParam(value = "算法策略实例ID", required = true)@RequestParam Long strategyInstanceId,
                                      @ApiParam(value = "算法策略ID", required = true)@RequestParam Long algorithmStrategyId,
                                      @ApiParam(value = "算法参数ID", required = true)@RequestParam Long algorithmParamId) {
        
        String str = algorithmStrategyService.delete(strategyInstanceId,algorithmStrategyId,algorithmParamId);
        return ApiResponse.success(str);
    }

    /**
     * 启动算法
     * @param strategyInstanceId 算法策略实例ID
     * @param algorithmStrategyId 算法策略id
     * @return 启动结果
     */
    @PostMapping("/deploy")
    @ApiOperation("启动算法")
    @StrategyOperateLog
    public ApiResponse<String> deploy(@ApiParam(value = "算法策略实例ID")@RequestParam Long strategyInstanceId,
                                      @ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
        algorithmStrategyService.operate(strategyInstanceId, algorithmStrategyId, AlgorithmStrategyOperate.Deploy);

        AlgorithmStrategyInstanceDTO dto = algorithmStrategyService.getById(strategyInstanceId);
        return ApiResponse.success(JSON.toJSONString(dto));
    }

    @PostMapping("/pause")
    @ApiOperation("暂停算法")
    @StrategyOperateLog
    public ApiResponse<String> pause(@ApiParam(value = "算法策略实例ID")@RequestParam Long strategyInstanceId,
                                     @ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
        algorithmStrategyService.operate(strategyInstanceId, algorithmStrategyId, AlgorithmStrategyOperate.Pause);

        AlgorithmStrategyInstanceDTO dto = algorithmStrategyService.getById(strategyInstanceId);
        return ApiResponse.success(JSON.toJSONString(dto));
    }

    @PostMapping("/recover")
    @ApiOperation("恢复算法")
    @StrategyOperateLog
    public ApiResponse<String> recover(@ApiParam(value = "算法策略实例ID")@RequestParam Long strategyInstanceId,
                                      @ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
        algorithmStrategyService.operate(strategyInstanceId, algorithmStrategyId, AlgorithmStrategyOperate.Recover);

        AlgorithmStrategyInstanceDTO dto = algorithmStrategyService.getById(strategyInstanceId);
        return ApiResponse.success(JSON.toJSONString(dto));
    }

    @PostMapping("/forced_offline")
    @ApiOperation("强制下线")
    @StrategyOperateLog
    public ApiResponse<String> forcedOffline(@ApiParam(value = "算法策略实例ID")@RequestParam Long strategyInstanceId,
                                             @ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
        algorithmStrategyService.operate(strategyInstanceId, algorithmStrategyId, AlgorithmStrategyOperate.ForcedOffline);

        AlgorithmStrategyInstanceDTO dto = algorithmStrategyService.getById(strategyInstanceId);
        return ApiResponse.success(JSON.toJSONString(dto));
    }

    @PostMapping("/offline")
    @ApiOperation("下线算法")
    @StrategyOperateLog
    public ApiResponse<String> offline(@ApiParam(value = "算法策略实例ID")@RequestParam Long strategyInstanceId,
                                       @ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
        algorithmStrategyService.operate(strategyInstanceId, algorithmStrategyId, AlgorithmStrategyOperate.Offline);

        AlgorithmStrategyInstanceDTO dto = algorithmStrategyService.getById(strategyInstanceId);
        return ApiResponse.success(JSON.toJSONString(dto));
    }
    
    @PostMapping("/getStrategyParams")
    @ApiOperation("获取算法参数")
    @StrategyOperateLog
    public ApiResponse<StrategyParametersDTO> getStrategyParams(@ApiParam(value = "算法策略ID") @RequestParam Long strategyId){
        return ApiResponse.success(algorithmStrategyService.getStrategyParams(strategyId));
    }
    
    @PostMapping("/updateStrategyParams")
    @ApiOperation("更新算法参数")
    @StrategyOperateLog
    public ApiResponse<String> updateStrategyParams(@ApiParam(value = "算法策略ID") @RequestParam Long strategyId,
                                                    @ApiParam(value = "算法参数") @RequestParam String strategyParams){
        return ApiResponse.success(algorithmStrategyService.updateStrategyParams(strategyId,strategyParams));
    }
    
    @PostMapping("/selectStrategyUpdateLog")
    @ApiOperation("查看算法更新日志")
    @StrategyOperateLog
    public ApiResponse<List<StrategyChangeLogPO>> selectStrategyUpdateLog(@ApiParam(value = "算法策略ID") @RequestParam Long strategyId){
        return ApiResponse.success(algorithmStrategyService.selectStrategyUpdateLog(strategyId));
    }
    
    @PostMapping("/selectStrategyOperateLog")
    @ApiOperation("查看算法操作日志")
    @StrategyOperateLog
    public ApiResponse<List<StrategyOperationLogPO>> selectStrategyOperateLog(@ApiParam(value = "算法策略ID") @RequestParam Long strategyId){
        return ApiResponse.success(algorithmStrategyService.selectStrategyOperateLog(strategyId));
    }
    
}
