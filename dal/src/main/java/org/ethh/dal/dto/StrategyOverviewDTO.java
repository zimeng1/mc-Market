package org.ethh.dal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * StrategyOverviewDTO
 * 策略概况
 * @author GZM
 * @since 2024/11/27 上午11:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StrategyOverviewDTO {
	
	@ApiModelProperty(notes = "算法策略实例ID")
	private String id;
	@ApiModelProperty(notes = "算法策略ID")
	private String algorithmStrategyId;
	@ApiModelProperty(notes = "算法参数ID")
	private String algorithmParametersId;
	@ApiModelProperty(notes = "交易标的")
	private String symbol;
	@ApiModelProperty(notes = "策略名称")
	private String strategyName;
	@ApiModelProperty(notes = "交易账户")
	private String accountName;
	@ApiModelProperty(notes = "交易账户Id")
	private String accountId;
	@ApiModelProperty(notes = "账户期除金额")
	private BigDecimal initialCapital;
	@ApiModelProperty(notes = "运行状态")
	private String algorithmStrategyOperate;
	@ApiModelProperty(notes = "算法状态")
	private String algorithmStatus;
	@ApiModelProperty(notes = "累计盈亏")
	private String accumulatedProfit;
	@ApiModelProperty(notes = "累计手续费")
	private String accumulatedFees;
	@ApiModelProperty(notes = "运行天数")
	private String runningDays;
	@ApiModelProperty(notes = "预计年化收益")
	private BigDecimal expectedAnnualReturn;
	@ApiModelProperty(notes = "账户净值")
	private BigDecimal accountValue;
	@ApiModelProperty(notes = "账户余额")
	private BigDecimal accountBalance;
	@ApiModelProperty(notes = "可用保证金")
	private BigDecimal availableMargin;
	@ApiModelProperty(notes = "浮动盈亏")
	private BigDecimal FloatingPL;
	@ApiModelProperty(notes = "保证金比例")
	private BigDecimal marginRatio;

}
