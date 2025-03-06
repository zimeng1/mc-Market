package org.ethh.marketMakerManager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ethh.dal.dto.*;
import org.ethh.dal.mapper.AlgorithmStrategyMapper;
import org.ethh.dal.mapper.TradeDetailMapper;
import org.ethh.dal.service.OrderDetailService;
import org.ethh.dal.service.TradeDetailService;
import org.ethh.dal.service.base.BasePageRsp;
import org.ethh.dal.req.StrategyRequest;
import org.ethh.marketMakerManager.service.IStrategyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * StrategyRecordServiceImpl
 *
 * @author GZM
 * @since 2024/12/10 下午7:28
 */
@Service
public class StrategyRecordServiceImpl implements IStrategyRecordService {
	
	@Autowired
	TradeDetailService tradeDetailService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	AlgorithmStrategyMapper algorithmStrategyMapper;
	
	@Autowired
	TradeDetailMapper tradeDetailMapper;
	
	@Override
	public BasePageRsp<TradeDetailDTO> pageTradeDetail(StrategyRequest strategyRequest) {
		return tradeDetailService.pageTradeDetail(strategyRequest);
	}
	
	@Override
	public BasePageRsp<OrderDetailDTO> pageOrderDetail(StrategyRequest strategyRequest) {
		return orderDetailService.pageOrderDetail(strategyRequest);
	}
	
	@Override
	public BasePageRsp<StockRevenueDTO> pageStockRevenue(StrategyRequest strategyRequest) {
		Page<StockRevenueDTO> page = new Page<>(strategyRequest.getCurrent(),strategyRequest.getSize());
		List<StockRevenueDTO> list = new ArrayList<>();
		int i = 0;
		while(i<10){
			list.add(
					new StockRevenueDTO(
							generateRandomDate(),
							generateRandomDate(),"GC2502",
							generateRandomAmountInt(),
							generateRandomAmountDouble(),
							generateRandomAmountInt(),
							generateRandomAmountDouble(),
							generateRandomAmountInt(),
							generateRandomAmountDouble(),
							generateRandomAmountInt(),
							generateRandomAmountDouble(),
							generateRandomAmountDouble()
					)
			);
			i++;
		}
		
		page.setRecords(list);
		
		return BasePageRsp.valueOf(page);
	}
	
	@Override
	public List<HistoricalPositionInfoDTO> getHistoricalPositionInfo(Long algorithmStrategyId) {
		return tradeDetailMapper.getHistoricalPositionInfo(algorithmStrategyId);
	}
	
	@Override
	public List<StrategyPerformanceDTO> getStrategyPerformance(Long algorithmStrategyId) {
		return tradeDetailMapper.getStrategyPerformance(algorithmStrategyId);
	}
	
	@Override
	public List<AccountAssetsDTO> accountAssetsChange(Long algorithmStrategyId) {
		return algorithmStrategyMapper.selectAccountAssetsChange(algorithmStrategyId);
	}
	
	@Override
	public List<YieldCurveDTO> yieldCurve(Long algorithmStrategyId) {
		List<YieldCurveDTO> list = new ArrayList<>();
		int i = 0;
		while(i<10){
			list.add(new YieldCurveDTO(algorithmStrategyId,generateRandomDate(),generateRandomAmount(),generateRandomAmount(),generateRandomAmount(),generateRandomAmount()));
			i++;
		}
		return list;
//		return algorithmStrategyMapper.selectYieldCurve(algorithmStrategyId);
	}
	
	@Override
	public List<RegisterChartDTO> registerChart(Long algorithmStrategyId) {
		List<RegisterChartDTO> list = new ArrayList<>();
		int i = 0;
		while(i<10){
			list.add(new RegisterChartDTO(algorithmStrategyId,i+1, generateRandomAmountDouble(), generateRandomAmountDouble(), generateRandomAmountDouble(), generateRandomAmountDouble(), generateRandomAmountDouble()));
			i++;
		}
		return list;
	}
	
	@Override
	public List<RegisterRecordDTO> registerRecord(Long algorithmStrategyId) {
		List<RegisterRecordDTO> list = new ArrayList<>();
		int i = 0;
		while(i<10){
			list.add(new RegisterRecordDTO(algorithmStrategyId,"sim_gdax",
					"BTC-USDT","Sell","open",generateRandomDate(), generateRandomAmountDouble(),
					generateRandomAmountDouble(),"post",0,0,0,0));
			i++;
		}
		return list;
	}
	
	private static final Random random = new Random();
	
	// 生成随机日期
	public static Date generateRandomDate() {
		long startEpochDay = LocalDate.of(2020, 1, 1).toEpochDay();
		long endEpochDay = LocalDate.of(2023, 12, 31).toEpochDay();
		long randomEpochDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay + 1));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		return Date.from(LocalDate.ofEpochDay(randomEpochDay).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	// 生成随机金额
	public static String generateRandomAmount() {
		return (100.0 + (10000.0 - 100.0) * random.nextDouble())+"";
	}
	
	// 生成随机金额
	public static Double generateRandomAmountDouble() {
		return (100.0 + (10000.0 - 100.0) * random.nextDouble());
	}
	
	// 生成随机金额
	public static int generateRandomAmountInt() {
		return (100 + (1000 - 100) * random.nextInt());
	}
	
	
}
