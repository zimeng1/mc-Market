package org.ethh.marketMakerManager.service.grpc.clinet;

import common.Common;
import io.grpc.stub.StreamObserver;
import market.Market;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author wangyifei
 */
@GrpcService
public class MarketGrpcService extends market.MarketDataServiceGrpc.MarketDataServiceImplBase {

    @Override
    public void marketData(Market.MarketDataRequest request, StreamObserver<Market.MarketDataReply> responseObserver) {
        Market.MarketDataReply reply = null;
        try {
            reply = Market.MarketDataReply.newBuilder().setSymbol(request.getSymbol())
                    .setAskPrice(Common.BigDecimal.newBuilder().setIntVal(909).setScale(2).build())
                    .setBidPrice(Common.BigDecimal.newBuilder().setIntVal(1001).setScale(2).build())
                    .build();
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {
            responseObserver.onNext(reply);
        }
        responseObserver.onCompleted();
    }
    
    @Override
    public void marketDataSnapshot(Market.MarketDataSnapshotRequest request, StreamObserver<Market.MarketDataSnapshotReply> responseObserver){
        Market.MarketDataSnapshotReply reply = null;
        try {
            Market.Depth.Builder depthBuilder = Market.Depth.newBuilder();
            Random random = new Random();

// 生成买单数据
            List<Market.Depth> bidsDepths = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Market.Depth depth = depthBuilder
                        .setLevel(i)
                        .setPrice(Common.BigDecimal.newBuilder().setIntVal(random.nextInt(10000)).setScale(2).build())
                        .setQty(Common.BigDecimal.newBuilder().setIntVal(random.nextInt(10000)).setScale(2).build())
                        .build();
                bidsDepths.add(depth);
            }

// 生成卖单数据
            List<Market.Depth> asksDepths = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Market.Depth depth = depthBuilder
                        .setLevel(i)
                        .setPrice(Common.BigDecimal.newBuilder().setIntVal(random.nextInt(10000)).setScale(2).build())
                        .setQty(Common.BigDecimal.newBuilder().setIntVal(random.nextInt(10000)).setScale(2).build())
                        .build();
                asksDepths.add(depth);
            }
            
            reply = Market.MarketDataSnapshotReply.newBuilder().setSymbol(request.getSymbol())
                    .setSequenceNumber(999)
                    .addAllBids(bidsDepths)
                    .addAllAsks(asksDepths)
                    .setMidPrice(Common.BigDecimal.newBuilder().setIntVal(200).setScale(2).build())
                    .build();
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {
            responseObserver.onNext(reply);
        }
        responseObserver.onCompleted();
    }
}
