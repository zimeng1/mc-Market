package org.ethh.marketMakerManager.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author wangyifei
 */
@Data
public class AsCreateRequest extends BaseRequest {
    
    @ApiModelProperty(notes = "业务线ID")
    private Long businessLineId;
    
    @ApiModelProperty(notes = "交易账户ID")
    private Long tradingAccountId;
    
    @ApiModelProperty(notes = "交易产品ID")
    private Long tradingProductId;
    
    @ApiModelProperty(notes = "交易产品期数ID")
    private Long periodId;
    
    @ApiModelProperty(notes = "交易所ID")
    private Long exchangeId;
    
    @ApiModelProperty(notes = "算法参数名称")
    private String algorithmName;
    
    @ApiModelProperty(notes = "算法参数")
    private String algorithmArgs;
    
    @ApiModelProperty(notes = "策略名称")
    private String strategyName;
}
