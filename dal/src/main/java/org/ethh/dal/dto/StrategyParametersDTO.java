package org.ethh.dal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * StrategyParametersDTO
 *
 * @author GZM
 * @since 2024/12/14 下午4:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "算法参数")
public class StrategyParametersDTO {
	@ApiModelProperty(notes = "策略ID")
	private Long strategyId;
	
	@ApiModelProperty(notes = "交易所ID")
	private Long exchangeId;
	
	@ApiModelProperty(notes = "交易产品ID")
	private Long tradingProductId;
	
	@ApiModelProperty(notes = "交易产品期数ID")
	private Long periodId;
	
	@ApiModelProperty(notes = "交易账户ID")
	private Long tradingAccountId;
	
	@ApiModelProperty(notes = "策略名称")
	private String strategyName;
	
	@ApiModelProperty(notes = "算法名称")
	private String algorithmName;
	
	@ApiModelProperty(notes = "算法参数")
	private String algorithmArgs;

}
