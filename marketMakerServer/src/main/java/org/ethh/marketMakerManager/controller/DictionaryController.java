package org.ethh.marketMakerManager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.ethh.common.annotation.StrategyOperateLog;
import org.ethh.dal.entity.Exchange;
import org.ethh.dal.entity.TradingAccount;
import org.ethh.dal.entity.TradingProduct;
import org.ethh.dal.mapper.ExchangeMapper;
import org.ethh.dal.mapper.TradingAccountMapper;
import org.ethh.dal.mapper.TradingProductMapper;
import org.ethh.marketMakerManager.model.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DictionaryController
 * 数据字典类
 * @author GZM
 * @since 2024/12/11 下午3:26
 */
@Slf4j
@RestController
@Api(tags = "数据字典类")
@RequestMapping("/api/as_dictionary")
public class DictionaryController {
	
	@Autowired
	private ExchangeMapper exchangeMapper;
	
	@Autowired
	private TradingProductMapper tradingProductMapper;
	
	@Autowired
	private TradingAccountMapper tradingAccountMapper;
	
	@ApiOperation("获取交易所数据字典")
	@PostMapping("/getExchange")
	@StrategyOperateLog
	public ApiResponse<List<Exchange>> getExchange() {
		List<Exchange> exchanges = exchangeMapper.selectAll();
		return ApiResponse.success(exchanges);
	}
	
	@ApiOperation("获取交易产品期数")
	@PostMapping("/getProductTerm")
	@StrategyOperateLog
	public ApiResponse<List<TradingProduct>> getProductTerm(@ApiParam(value = "交易产品名称") @RequestParam String name) {
		List<TradingProduct> list = tradingProductMapper.getByName(name);
		return ApiResponse.success(list);
	}
	
	@ApiOperation("获取执行账户")
	@PostMapping("/getAccount")
	@StrategyOperateLog
	public ApiResponse<List<TradingAccount>> getAccount() {
		List<TradingAccount> list = tradingAccountMapper.getAll();
		return ApiResponse.success(list);
	}
	
	
}
