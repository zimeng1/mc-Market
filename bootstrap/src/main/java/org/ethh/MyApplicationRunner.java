package org.ethh;
import org.ethh.marketMakerManager.service.grpc.clinet.MarketGrpcClientService;
import org.ethh.marketMakerManager.service.grpc.clinet.PositionGrpcClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * MyApplicationRunner
 *
 * @author GZM
 * @since 2024/12/13 下午6:24
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
	
	private static final Logger log = LoggerFactory.getLogger(MyApplicationRunner.class);
	@Autowired
	private MarketGrpcClientService marketGrpcClientService;
	
	@Autowired
	private PositionGrpcClientService positionGrpcClientService;
	
	@Override
	public void run(ApplicationArguments args){
		// 在应用程序启动时执行的代码
		try {
			String result = positionGrpcClientService.capitalSubscribe();
			log.info(result);
		}catch (Exception e){
			log.error("capitalSubscribe启动异常:{}",e.getMessage());
		}
		
		try {
			String result = positionGrpcClientService.positionSubscribe();
			log.info(result);
		}catch (Exception e){
			log.error("positionSubscribe启动异常:{}",e.getMessage());
		}
		
		try {
			String result = marketGrpcClientService.streamMarketDataSnapshot();
			log.info(result);
		}catch (Exception e){
			log.error("streamMarketDataSnapshot启动异常:{}",e.getMessage());
		}
	}
}

