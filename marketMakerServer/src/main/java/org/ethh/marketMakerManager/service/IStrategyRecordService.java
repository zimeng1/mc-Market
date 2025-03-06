package org.ethh.marketMakerManager.service;

import io.swagger.annotations.ApiOperation;
import org.ethh.dal.dto.*;
import org.ethh.dal.service.base.BasePageRsp;
import org.ethh.dal.req.StrategyRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * StrategyRecordService
 *
 * @author GZM
 * @since 2024/12/10 下午7:26
 */
@Service
public interface IStrategyRecordService {
	
	@ApiOperation("获取策略运行参数成交记录")
	BasePageRsp<TradeDetailDTO> pageTradeDetail(StrategyRequest strategyRequest);
	
	BasePageRsp<OrderDetailDTO> pageOrderDetail(StrategyRequest strategyRequest);
	
	List<HistoricalPositionInfoDTO> getHistoricalPositionInfo(Long algorithmStrategyId);
	
	List<StrategyPerformanceDTO> getStrategyPerformance(Long algorithmStrategyId);
	
	List<AccountAssetsDTO> accountAssetsChange(Long algorithmStrategyId);
	
	List<YieldCurveDTO> yieldCurve(Long algorithmStrategyId);
	
	List<RegisterChartDTO> registerChart(Long algorithmStrategyId);
	
	List<RegisterRecordDTO> registerRecord(Long algorithmStrategyId);
	
	BasePageRsp<StockRevenueDTO> pageStockRevenue(StrategyRequest strategyRequest);
}
