package org.ethh.marketMakerManager.service.grpc.clinet;

import com.alibaba.fastjson2.JSONObject;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.ethh.common.BaseDTO;
import org.ethh.common.cache.SimpleCache;
import org.ethh.dal.entity.AlgorithmStrategy;
import org.ethh.dal.entity.TradingAccount;
import org.ethh.dal.entity.TradingProduct;
import org.ethh.dal.mapper.AlgorithmStrategyMapper;
import org.ethh.dal.mapper.TradingAccountMapper;
import org.ethh.dal.mapper.TradingProductMapper;
import org.ethh.marketMakerManager.model.AccountInfoDTO;
import org.ethh.marketMakerManager.model.PositionInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import position.Position;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * PositionGrpcClientService
 *
 * @author GZM
 * @since 2024/12/12 上午11:47
 */
@Service
@Slf4j
public class PositionGrpcClientService {
	
	@GrpcClient("positionDataService")
	private position.PositionServiceGrpc.PositionServiceBlockingStub positionServiceBlockingStub;
	
	@GrpcClient("positionDataService")
	private position.PositionServiceGrpc.PositionServiceStub positionServiceStub;
	
	@Autowired
	TradingAccountMapper tradingAccountMapper;
	
	@Autowired
	TradingProductMapper tradingProductMapper;
	
	@Autowired
	AlgorithmStrategyMapper algorithmStrategyMapper;
	
	public String getPosition(){
		// 构造请求
		position.Position.PositionRequest request = position.Position.PositionRequest.newBuilder().setSymbol("GC2412").build();
		// 调用 gRPC 服务，获取响应
		position.Position.PositionReply reply = positionServiceBlockingStub.position(request);
		// 返回服务端响应的数据
		return JSONObject.toJSONString(reply);
	}
	
	public String positionSubscribe() throws InterruptedException {
		// 构造请求
		position.Position.PositionRequest request = position.Position.PositionRequest.newBuilder().setSymbol("GC2412").build();
		
		positionServiceStub.positionSubscribe(request, new StreamObserver<Position.FullPosition>() {
			@Override
			public void onNext(Position.FullPosition fullPosition) {
				log.info("Received Qty: {}", fullPosition.getShortPositionQty());
				try {
					PositionInfoDTO positionInfoDTO = PositionInfoDTO.jsonToPositionInfoDTO(JSONObject.toJSONString(fullPosition));
					Map<String, BaseDTO> map = SimpleCache.get(SimpleCache.POSITION);
					if(map == null){
						map = new HashMap<>();
					}
					map.put(fullPosition.getAccountNo(),positionInfoDTO);
					SimpleCache.put(SimpleCache.POSITION,map);
				}catch (Exception e){
					log.error("Received positionSubscribe异常:{}", e.getMessage());
				}
				
			}
			
			@Override
			public void onError(Throwable e) {
				log.error("positionSubscribe异常:{}", e.getMessage());
			}
			
			@Override
			public void onCompleted() {
				log.info("positionSubscribe Server closed the stream.");
			}
		});
		
		// 阻塞线程以等待来自服务端推送的消息
		Thread.sleep(10000);
		
		return "";
	}
	
	public String capitalSubscribe() throws InterruptedException {
		// 构造请求
		position.Position.CapitalRequest request = position.Position.CapitalRequest.newBuilder().build();
		
		positionServiceStub.capitalSubscribe(request, new StreamObserver<Position.CapitalReply>() {
			@Override
			public void onNext(Position.CapitalReply capitalReply) {
				log.info("Received AccountNo: {}", capitalReply.getAccountNo());
				try {
					AccountInfoDTO accountInfoDTO = AccountInfoDTO.jsonToAccountInfoDTO(JSONObject.toJSONString(capitalReply));
					Map<String, BaseDTO> map = SimpleCache.get(SimpleCache.CAPITAL);
					if(map == null){
						map = new HashMap<>();
					}
					map.put(capitalReply.getAccountNo(),accountInfoDTO);
					SimpleCache.put(SimpleCache.CAPITAL,map);
				}catch (Exception e){
					log.error("Received capitalSubscribe异常:{}", e.getMessage());
				}
			}
			
			@Override
			public void onError(Throwable e) {
				log.error("capitalSubscribe异常:{}", e.getMessage());
			}
			
			@Override
			public void onCompleted() {
				log.info("capitalSubscribe Server closed the stream.");
			}
		});
		
		// 阻塞线程以等待来自服务端推送的消息
		Thread.sleep(10000);
		
		return "capitalSubscribe 连接成功";
		
	}
	
	public PositionInfoDTO getPositionInfo(long strategyId) {
		AlgorithmStrategy algorithmStrategy = algorithmStrategyMapper.getById(strategyId);
		TradingProduct tradingProduct = tradingProductMapper.getById(algorithmStrategy.getTradingProductId());
		
		Map<String, BaseDTO> map = SimpleCache.get(SimpleCache.POSITION);
		return (PositionInfoDTO)map.get(tradingProduct.getSymbol());
		
	}
	
	public AccountInfoDTO getAccountInfo(long strategyId) {
		AlgorithmStrategy algorithmStrategy = algorithmStrategyMapper.getById(strategyId);
		TradingAccount tradingAccount = tradingAccountMapper.getById(algorithmStrategy.getTradingAccountId());
		
		Map<String, BaseDTO> map = SimpleCache.get(SimpleCache.CAPITAL);
		AccountInfoDTO accountInfoDTO = (AccountInfoDTO) map.get(tradingAccount.getAccountId());
		if(accountInfoDTO == null){
			return null;
		}
		accountInfoDTO.setStrategyName(algorithmStrategy.getStrategyName());
		accountInfoDTO.setTradingAccount(tradingAccount.getAccountName());
		accountInfoDTO.setAccountType(tradingAccount.getAccountSubject());
		accountInfoDTO.setMarginRatio(BigDecimal.valueOf(tradingAccount.getCurrentMarginRatio()));
		return accountInfoDTO;
	}
	
}
