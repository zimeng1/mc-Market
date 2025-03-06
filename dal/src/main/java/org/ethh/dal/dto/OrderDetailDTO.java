package org.ethh.dal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.ethh.common.BaseDTO;

/**
 * OrderDetailDTO
 * 策略运行参数委托记录
 * @author GZM
 * @since 2024/12/11 下午8:44
 */

@Data
@ApiModel(description = "策略运行参数委托记录")
public class OrderDetailDTO {
	
	@ApiModelProperty(notes = "委托时间")
	private String orderTime;
	
	@ApiModelProperty(notes = "委托单号码")
	private String orderNumber;
	
	@ApiModelProperty(notes = "最后更新时间")
	private String lastUpdateTime;
	
	@ApiModelProperty(notes = "成交单号")
	private String tradeNumber;
	
	@ApiModelProperty(notes = "交易产品")
	private String product;
	
	@ApiModelProperty(notes = "订单层级")
	private String orderLevel;
	
	@ApiModelProperty(notes = "算法状态")
	private String algoStatus;
	
	@ApiModelProperty(notes = "方向")
	private String direction;
	
	@ApiModelProperty(notes = "开平方向")
	private String openCloseDirection;
	
	@ApiModelProperty(notes = "建仓均价")
	private double averagePrice;
	
	@ApiModelProperty(notes = "委托状态")
	private String orderStatus;
	
	@ApiModelProperty(notes = "买一价")
	private double buyPrice;
	
	@ApiModelProperty(notes = "卖一价")
	private double sellPrice;
	
	@ApiModelProperty(notes = "市价")
	private double marketPrice;
	
	@ApiModelProperty(notes = "挂单价格（买价）")
	private double buyOrderPrice;
	
	@ApiModelProperty(notes = "挂单价格（卖价）")
	private double sellOrderPrice;
	
	@ApiModelProperty(notes = "最优中间价")
	private double bestMidPrice;
	
	@ApiModelProperty(notes = "最优点差")
	private double bestSpread;
	
	@ApiModelProperty(notes = "期望目标库存")
	private double targetInventory;
	
	@ApiModelProperty(notes = "实际库存")
	private double actualInventory;
	
	@ApiModelProperty(notes = "容忍度系数")
	private double toleranceFactor;
	
	@ApiModelProperty(notes = "波动率")
	private double volatility;
	
	@ApiModelProperty(notes = "订单簿流动性")
	private String orderBookLiquidity;
	
}
