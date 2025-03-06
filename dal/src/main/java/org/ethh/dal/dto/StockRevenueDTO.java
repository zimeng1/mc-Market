package org.ethh.dal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * StockRevenueDTO
 *
 * @author GZM
 * @since 2024/12/18 下午4:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "库存收益记录")
public class StockRevenueDTO {
	
	@ApiModelProperty(value = "库存变更开始时间", example = "2023-01-01 00:00:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date changeStartTime; // 库存变更开始时间
	
	@ApiModelProperty(value = "库存变更结束时间", example = "2023-01-31 23:59:59")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date changeEndTime;   // 库存变更结束时间
	
	@ApiModelProperty(value = "交易产品", example = "产品A")
	private String product;                  // 交易产品
	
	@ApiModelProperty(value = "期初数量", example = "100")
	private int initialQuantity;             // 期初数量
	
	@ApiModelProperty(value = "起初平均成本价", example = "50.00")
	private double initialAverageCost;   // 起初平均成本价
	
	@ApiModelProperty(value = "变化数量", example = "10")
	private int changeQuantity;              // 变化数量
	
	@ApiModelProperty(value = "变化平均成本价", example = "55.00")
	private double changeAverageCost;    // 变化平均成本价
	
	@ApiModelProperty(value = "库存变化累计交易手数", example = "5")
	private int cumulativeTransactionCount;  // 库存变化累计交易手数
	
	@ApiModelProperty(value = "库存变化累计手续费", example = "2.50")
	private double cumulativeFees;       // 库存变化累计手续费
	
	@ApiModelProperty(value = "期末数量", example = "90")
	private int finalQuantity;               // 期末数量
	
	@ApiModelProperty(value = "期末平均成本价", example = "52.50")
	private double finalAverageCost;     // 期末平均成本价
	
	@ApiModelProperty(value = "已实现盈亏", example = "100.00")
	private double realizedProfitLoss;   // 已实现盈亏
	
}
