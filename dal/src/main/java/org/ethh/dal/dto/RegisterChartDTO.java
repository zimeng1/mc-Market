package org.ethh.dal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RegisterChartDTO
 *
 * @author GZM
 * @since 2024/12/16 下午3:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "挂单记录图表")
public class RegisterChartDTO {
	
	@ApiModelProperty(notes = "策略ID")
	Long strategyId;
	
	@ApiModelProperty(notes = "Ticks次数")
	private int Ticks;
	
	@ApiModelProperty(notes = "价格")
	private double price;
	
	@ApiModelProperty(notes = "市场价")
	private double marketPrice;
	
	@ApiModelProperty(notes = "中间价")
	private double midPrice;
	
	@ApiModelProperty(notes = "买一价")
	private double buyPrice;
	
	@ApiModelProperty(notes = "卖一价")
	private double askPrice;

}
