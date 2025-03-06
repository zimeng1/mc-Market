package org.ethh.marketMakerManager.service;

import org.ethh.common.enums.AlgorithmStrategyOperate;
import org.ethh.dal.dto.StrategyOverviewDTO;
import org.ethh.dal.dto.StrategyParametersDTO;
import org.ethh.dal.entity.StrategyChangeLogPO;
import org.ethh.dal.entity.StrategyOperationLogPO;
import org.ethh.dal.service.base.BasePageReq;
import org.ethh.dal.service.base.BasePageRsp;
import org.ethh.marketMakerManager.model.AlgorithmStrategyInstanceDTO;
import org.ethh.marketMakerManager.model.request.AsCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyifei
 */
@Service
public interface AlgorithmStrategyService {


    AlgorithmStrategyInstanceDTO getById(Long id);

    AlgorithmStrategyInstanceDTO create(AsCreateRequest asCreateRequest);

    void operate(Long id, Long algorithmStrategyId, AlgorithmStrategyOperate operate);
    
    BasePageRsp<StrategyOverviewDTO> getStrategyData(BasePageReq req);
    
    StrategyParametersDTO getStrategyParams(Long id);
    
    String updateStrategyParams(Long id, String strategyParams);
    
    List<StrategyChangeLogPO> selectStrategyUpdateLog(Long strategyId);
    
    String delete(Long strategyInstanceId, Long algorithmStrategyId, Long algorithmParamId);
    
    List<StrategyOperationLogPO> selectStrategyOperateLog(Long strategyId);
    
    BasePageRsp<StrategyOverviewDTO> getStrategyDataHistory(BasePageReq req);
}
