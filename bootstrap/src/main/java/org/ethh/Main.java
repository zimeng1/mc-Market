package org.ethh;


import com.alibaba.fastjson2.JSONObject;
import org.ethh.dal.entity.AlgorithmParameters;
import org.ethh.marketMakerManager.model.AlgorithmParametersDTO;
import org.ethh.marketMakerManager.model.covert.AlgorithmParametersCovert;
import org.ethh.marketMakerManager.service.AlgorithmParameterService;
import org.ethh.marketMakerManager.service.grpc.clinet.MarketGrpcClientService;
import org.ethh.marketMakerManager.service.grpc.clinet.PositionGrpcClientService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyifei
 */
@SpringBootApplication(scanBasePackages = {"org.ethh",  "org.ethh.dal", "org.ethh.marketMakerManager"})
@RestController
@EnableScheduling
@MapperScan(basePackages = {"org.ethh.dal.mapper"})
public class Main {

    private final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Autowired
    private AlgorithmParameterService algorithmParameterService;

    @Autowired
    private AlgorithmParametersCovert algorithmParametersCovert;

    @Autowired
    private MarketGrpcClientService marketGrpcClientService;
    
    @Autowired
    private PositionGrpcClientService positionGrpcClientService;

    public static void main(String[] args) {

        System.out.println("==start======");
        try {
            SpringApplication.run(Main.class, args);
        }catch(Throwable e) {
            System.out.println(e.toString());
        }
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        LOGGER.info("Hello {}!", name);
        return String.format("Hello %s! ", name);
    }

    @PostMapping("AlgorithmParameters")
    public String setAlgorithmParameters(String algorithmName, String algorithmArgs) {
        AlgorithmParameters algorithmParameters =  AlgorithmParameters.builder()
                .algorithmArgs(algorithmArgs).algorithmName(algorithmName).build();
        algorithmParameterService.insert(algorithmParameters);

        LOGGER.info("insert algorithmParameters:{}", algorithmParameters);

        AlgorithmParameters dbData = algorithmParameterService.getById(algorithmParameters.getId());
        return JSONObject.toJSONString(dbData);
    }

    @GetMapping("AlgorithmParameters")
    public String getAlgorithmParameters(Long id) {
        AlgorithmParameters dbData = algorithmParameterService.getById(id);
        AlgorithmParametersDTO dto  = algorithmParametersCovert.toDTO(dbData);
        return JSONObject.toJSONString(dbData);
    }

    @GetMapping("grpc")
    public String grpc() throws InterruptedException {
        return marketGrpcClientService.streamMarketDataSnapshot();
    }
    
    @GetMapping("grpcTest")
    public String grpcTest() throws Exception {
        return positionGrpcClientService.positionSubscribe();
        
        
    }
}