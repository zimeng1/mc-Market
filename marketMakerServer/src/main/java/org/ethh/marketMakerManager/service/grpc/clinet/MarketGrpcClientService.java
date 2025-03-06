package org.ethh.marketMakerManager.service.grpc.clinet;

import com.alibaba.fastjson2.JSONObject;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import market.Market;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.ethh.common.BaseDTO;
import org.ethh.common.cache.SimpleCache;
import org.ethh.dal.entity.TradingProduct;
import org.ethh.dal.mapper.TradingProductMapper;
import org.ethh.dal.service.base.BasePageReq;
import org.ethh.marketMakerManager.model.MarketDataDTO;
import org.ethh.marketMakerManager.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wangyifei
 */
@Service
@Slf4j
public class MarketGrpcClientService {

    @GrpcClient("marketDataService")
    private market.MarketDataServiceGrpc.MarketDataServiceBlockingStub marketDataServiceBlockingStub;
    
    @GrpcClient("marketDataService")
    private market.MarketDataServiceGrpc.MarketDataServiceStub marketDataServiceStub;
//    position.PositionServiceGrpc.PositionServiceStub positionServiceStub;
    
    @Autowired
    TradingProductMapper tradingProductMapper;
    
    public String getDateFromServer() {
        // 构造请求
        Market.MarketDataRequest request = Market.MarketDataRequest.newBuilder().setSymbol("GC2512").build();
        // 调用 gRPC 服务，获取响应
        Market.MarketDataReply reply = marketDataServiceBlockingStub.marketData(request);
        // 返回服务端响应的数据
        return JSONObject.toJSONString(reply);
    }
    
    public String getOrderFromServer() {
        // 构造请求
        Market.OrderInstructionRequest request = Market.OrderInstructionRequest.newBuilder().setSymbol("GC2412").build();
        // 调用 gRPC 服务，获取响应
        Market.OrderInstructionReply reply = marketDataServiceBlockingStub.orderInstruction(request);
        // 返回服务端响应的数据
        return JSONObject.toJSONString(reply);
    }
    
    public String getMarketDataSnapshot(String symbol) {
        // 构造请求
        Market.MarketDataSnapshotRequest request = Market.MarketDataSnapshotRequest.newBuilder().setSymbol(symbol).build();
        // 调用 gRPC 服务，获取响应
        Market.MarketDataSnapshotReply reply = marketDataServiceBlockingStub.marketDataSnapshot(request);
        // 返回服务端响应的数据
        return JSONObject.toJSONString(reply);
    }
    
    public List<MarketDataDTO> getMarketDataList() {
        List<MarketDataDTO> list = new ArrayList<>();
        Market.MarketDataRequest request = Market.MarketDataRequest.newBuilder()
                .setSymbol("GC2502").build();
        // 调用 gRPC 服务，获取响应
        Market.MarketDataReply reply = marketDataServiceBlockingStub.marketData(request);
        list.add(MarketDataDTO.jsonToMarketData(JSONObject.toJSONString(reply)));
        // 返回服务端响应的数据
        return list;
    }

    public BigDecimal getPositionQuantity(String symbol, String accountId) {
        Assert.isTrue(StringUtils.hasText(symbol), "symbol is empty");
        Assert.isTrue(StringUtils.hasText(accountId), "accountId is empty");
        
        Market.MarketDataRequest request = Market.MarketDataRequest.newBuilder()
                .setSymbol(symbol).setAccountId(accountId).build();
        Market.MarketDataReply reply = marketDataServiceBlockingStub.marketData(request);

        Assert.notNull(reply.getPositionQuantity(), "positionQuantity is null");
        return new BigDecimal(reply.getPositionQuantity().getIntVal()).setScale((int)reply.getPositionQuantity().getScale(), RoundingMode.HALF_UP);
    }
    
    public String streamMarketDataSnapshot() throws InterruptedException {
        // 构造请求
        Market.MarketDataSnapshotRequest request = Market.MarketDataSnapshotRequest.newBuilder().build();
        marketDataServiceStub.streamMarketDataSnapshot(request, new StreamObserver<Market.MarketDataSnapshotReply>() {
            @Override
            public void onNext(Market.MarketDataSnapshotReply marketDataSnapshotReply) {
				log.info("Received symbol: {}", marketDataSnapshotReply.getSymbol());
                try {
                    OrderDTO orderDTO = OrderDTO.jsonToOrderDTO(JSONObject.toJSONString(marketDataSnapshotReply));
                    Map<String, BaseDTO> map = SimpleCache.get(SimpleCache.ORDER);
                    if(map == null){
                        map = new HashMap<>();
                    }
                    map.put(marketDataSnapshotReply.getSymbol(),orderDTO);
                    SimpleCache.put(SimpleCache.ORDER,map);
                }catch (Exception e){
                    log.error("Received streamMarketDataSnapshot异常:{}", e.getMessage());
                }
            }
            
            @Override
            public void onError(Throwable e) {
                log.error("MarketDataSnapshot异常:{}", e.getMessage());
            }
            
            @Override
            public void onCompleted() {
                log.info("MarketDataSnapshot Server closed the stream.");
            }
        });
        
        // 阻塞线程以等待来自服务端推送的消息
        Thread.sleep(10000);
        
        return "";
    }
    
    /**
     * 获取订单簿数据
     * @return
     */
//    public List<OrderDTO> getOrderData() {
//        List<TradingProduct> tradingProductList = tradingProductMapper.getAll();
//        List<OrderDTO> list = new ArrayList<>();
//
//        try {
//            for(TradingProduct tradingProduct : tradingProductList) {
//                String marketDataSnapshot = getMarketDataSnapshot(tradingProduct.getSymbol());
//                OrderDTO orderDTO = OrderDTO.jsonToOrderDTO(marketDataSnapshot);
//                list.add(orderDTO);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
    
    /**
     * 获取订单簿数据
     * @return
     */
    public Map<String, BaseDTO> getOrderData() {
        return SimpleCache.get(SimpleCache.ORDER);
    }
    
}
