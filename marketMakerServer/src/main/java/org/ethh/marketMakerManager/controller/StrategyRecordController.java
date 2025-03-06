package org.ethh.marketMakerManager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ethh.dal.dto.OrderDetailDTO;
import org.ethh.dal.dto.StockRevenueDTO;
import org.ethh.dal.dto.TradeDetailDTO;
import org.ethh.dal.service.base.BasePageRsp;
import org.ethh.marketMakerManager.model.Response.ApiResponse;
import org.ethh.dal.req.StrategyRequest;
import org.ethh.marketMakerManager.service.IStrategyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StrategyRecordController
 * 策略记录
 * @author GZM
 * @since 2024/12/10 下午7:15
 */
@Slf4j
@RestController
@Api(tags = "策略记录类")
@RequestMapping("/api/as_strategy")
public class StrategyRecordController {
	
	@Autowired
	IStrategyRecordService strategyRecordService;
	
	@ApiOperation("获取策略运行参数成交记录")
	@PostMapping("/tradeDetail")
	public ApiResponse<BasePageRsp<TradeDetailDTO>> pageTradeDetail(@RequestBody StrategyRequest strategyRequest) {
		return ApiResponse.success(strategyRecordService.pageTradeDetail(strategyRequest==null?new StrategyRequest():strategyRequest));
	}
	
	@ApiOperation("获取策略运行参数委托记录")
	@PostMapping("/orderDetail")
	public ApiResponse<BasePageRsp<OrderDetailDTO>> pageOrderDetail(@RequestBody StrategyRequest strategyRequest) {
		return ApiResponse.success(strategyRecordService.pageOrderDetail(strategyRequest==null?new StrategyRequest():strategyRequest));
	}
	
	@ApiOperation("获取库存收益")
	@PostMapping("/stockRevenue")
	public ApiResponse<BasePageRsp<StockRevenueDTO>> pageStockRevenue(@RequestBody StrategyRequest strategyRequest) {
		return ApiResponse.success(strategyRecordService.pageStockRevenue(strategyRequest==null?new StrategyRequest():strategyRequest));
	}
	
}
