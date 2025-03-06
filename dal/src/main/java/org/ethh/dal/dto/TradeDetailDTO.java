package org.ethh.dal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TradeDetailDTO
 *
 * @author GZM
 * @since 2024/12/10 下午7:07
 */
@ApiModel(description = "成交记录")
@Data
public class TradeDetailDTO {
	
	@ApiModelProperty(notes = "委托时间")
	private String commissionTime;
	
	@ApiModelProperty(notes = "成交时间")
	private String dealTime;
	
	@ApiModelProperty(notes = "交易产品")
	private String tradingProduct;
	
	@ApiModelProperty(notes = "订单层级")
	private String orderLevel;
	
	@ApiModelProperty(notes = "算法状态")
	private String algorithmStatus;
	
	@ApiModelProperty(notes = "方向")
	private String direction;
	
	@ApiModelProperty(notes = "仓位方向")
	private String positionDirection;
	
	@ApiModelProperty(notes = "成交数量")
	private int dealQuantity;
	
	@ApiModelProperty(notes = "委托状态")
	private String orderStatus;
	
	@ApiModelProperty(notes = "成交均价")
	private double averageDealPrice;
	
	@ApiModelProperty(notes = "建仓均价")
	private double averageOpenPrice;
	
	@ApiModelProperty(notes = "已实现盈亏")
	private double realizedProfitLoss;
	
	@ApiModelProperty(notes = "手续费")
	private double commissionFee;
	
	
}
