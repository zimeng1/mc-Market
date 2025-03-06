package org.ethh.marketMakerManager.service.grpc.clinet;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import position.Position;

import java.util.Timer;
import java.util.TimerTask;

/**
 * PositionGrpcService
 *
 * @author GZM
 * @since 2024/12/12 上午11:46
 */
@GrpcService
public class PositionGrpcService extends position.PositionServiceGrpc.PositionServiceImplBase {
	
	@Override
	public void position(Position.PositionRequest request, StreamObserver<Position.PositionReply> responseObserver) {
		Position.PositionReply reply = null;
		try {
			reply = Position.PositionReply.newBuilder().setSymbol(request.getSymbol())
					.build();
		} catch (Exception e) {
			responseObserver.onError(e);
		} finally {
			responseObserver.onNext(reply);
		}
		responseObserver.onCompleted();
	}
	
	@Override
	public void positionSubscribe(Position.PositionRequest request,StreamObserver<position.Position.FullPosition> responseObserver){
		// 启动一个定时任务，每秒钟向客户端发送一条消息
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			int count = 0;
			
			@Override
			public void run() {
				String message = "Message " + count++;
				System.out.println("Sending message: " + message);
				
				// 构造消息对象并发送给客户端
				Position.FullPosition position = Position.FullPosition.newBuilder().setShortPositionQty(123.23)
						.build();
				responseObserver.onNext(position);
			}
		}, 0, 10000);
	}
	
}
