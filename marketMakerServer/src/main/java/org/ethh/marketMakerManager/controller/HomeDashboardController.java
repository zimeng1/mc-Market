package org.ethh.marketMakerManager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.ethh.common.BaseDTO;
import org.ethh.common.annotation.StrategyOperateLog;
import org.ethh.dal.dto.*;
import org.ethh.dal.service.base.BasePageReq;
import org.ethh.dal.service.base.BasePageRsp;
import org.ethh.marketMakerManager.model.AccountInfoDTO;
import org.ethh.marketMakerManager.model.MarketDataDTO;
import org.ethh.marketMakerManager.model.PositionInfoDTO;
import org.ethh.marketMakerManager.model.Response.ApiResponse;
import org.ethh.marketMakerManager.service.AlgorithmStrategyService;
import org.ethh.marketMakerManager.service.IStrategyRecordService;
import org.ethh.marketMakerManager.service.grpc.clinet.MarketGrpcClientService;
import org.ethh.marketMakerManager.service.grpc.clinet.PositionGrpcClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * HomeDashboardController
 * 首页看板
 * @author GZM
 * @since 2024/11/25 下午3:20
 */
@Slf4j
@RestController
@Api(tags = "看板类")
@RequestMapping("/api/as_home")
public class HomeDashboardController {
	
	@Autowired
	MarketGrpcClientService marketGrpcClientService;
	
	@Autowired
	PositionGrpcClientService positionGrpcClientService;
	
	@Autowired
	AlgorithmStrategyService algorithmStrategyService;
	
	@Autowired
	IStrategyRecordService strategyRecordService;
	
	@ApiOperation("获取行情看板数据")
	@PostMapping("/marketSection")
	@StrategyOperateLog
	public ApiResponse<List<MarketDataDTO>> marketSection() {
		return ApiResponse.success(marketGrpcClientService.getMarketDataList());
	}
	
	@ApiOperation("获取策略看板数据")
	@PostMapping("/strategyData")
	@StrategyOperateLog
	public ApiResponse<BasePageRsp<StrategyOverviewDTO>> strategyData(@RequestBody BasePageReq req) {
		return ApiResponse.success(algorithmStrategyService.getStrategyData(req));
	}
	
	@ApiOperation("获取历史策略看板数据")
	@PostMapping("/strategyDataHistory")
	@StrategyOperateLog
	public ApiResponse<BasePageRsp<StrategyOverviewDTO>> strategyDataHistory(@RequestBody BasePageReq req) {
		return ApiResponse.success(algorithmStrategyService.getStrategyDataHistory(req));
	}
	
	@ApiOperation("获取订单簿数据")
	@PostMapping("/orderData")
	@StrategyOperateLog
	public ApiResponse<Map<String, BaseDTO>> orderData() {
		return ApiResponse.success(marketGrpcClientService.getOrderData());
	}
	
	@ApiOperation("获取策略表现")
	@PostMapping("/strategyPerformance")
	@StrategyOperateLog
	public ApiResponse<List<StrategyPerformanceDTO>> strategyPerformance(@ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
		return ApiResponse.success(strategyRecordService.getStrategyPerformance(algorithmStrategyId));
	}
	
	@ApiOperation("获取交易账户信息")
	@PostMapping("/accountInfo")
	@StrategyOperateLog
	public ApiResponse<AccountInfoDTO> accountInfo(@ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
		return ApiResponse.success(positionGrpcClientService.getAccountInfo(algorithmStrategyId));
	}
	
	@ApiOperation("获取历史仓位信息")
	@PostMapping("/historicalPositionInfo")
	@StrategyOperateLog
	public ApiResponse<List<HistoricalPositionInfoDTO>> historicalPositionInfo(@ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
		return ApiResponse.success(strategyRecordService.getHistoricalPositionInfo(algorithmStrategyId));
	}
	
	@ApiOperation("持仓信息")
	@PostMapping("/positionInfo")
	@StrategyOperateLog
	public ApiResponse<PositionInfoDTO> positionInfoDTO(@ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
		return ApiResponse.success(positionGrpcClientService.getPositionInfo(algorithmStrategyId));
	}
	
	@ApiOperation("账户资产变化")
	@PostMapping("/accountAssetsChange")
	@StrategyOperateLog
	public ApiResponse<List<AccountAssetsDTO>> accountAssetsChange(@ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
		return ApiResponse.success(strategyRecordService.accountAssetsChange(algorithmStrategyId));
	}
	
	@ApiOperation("收益曲线")
	@PostMapping("/yieldCurve")
	@StrategyOperateLog
	public ApiResponse<List<YieldCurveDTO>> yieldCurve(@ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
		return ApiResponse.success(strategyRecordService.yieldCurve(algorithmStrategyId));
	}
	
	@ApiOperation("挂单记录图表")
	@PostMapping("/registerChart")
	@StrategyOperateLog
	public ApiResponse<List<RegisterChartDTO>> registerChart(@ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
		return ApiResponse.success(strategyRecordService.registerChart(algorithmStrategyId));
	}
	
	@ApiOperation("挂单记录")
	@PostMapping("/registerRecord")
	@StrategyOperateLog
	public ApiResponse<List<RegisterRecordDTO>> registerRecord(@ApiParam(value = "算法策略ID")@RequestParam Long algorithmStrategyId) {
		return ApiResponse.success(strategyRecordService.registerRecord(algorithmStrategyId));
	}
	
	@ApiOperation("建立连接")
	@PostMapping("/addConnect")
	@StrategyOperateLog
	public String addConnect() throws Exception {
		// 在应用程序启动时执行的代码
		positionGrpcClientService.capitalSubscribe();
		positionGrpcClientService.positionSubscribe();
		marketGrpcClientService.streamMarketDataSnapshot();
		
		return "success";
	}
}
