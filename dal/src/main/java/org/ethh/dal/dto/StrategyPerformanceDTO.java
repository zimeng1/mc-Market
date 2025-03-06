package org.ethh.dal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * strategyPerformanceDTO
 *
 * @author GZM
 * @since 2024/12/5 下午6:43
 */
@Data
@ApiModel(description = "算法表现")
public class StrategyPerformanceDTO {
	
	@ApiModelProperty("交易标的")
	private String symbol;
	
	@ApiModelProperty("策略名称")
	private String strategyName;
	
	@ApiModelProperty("交易账户")
	private String tradingAccount;
	
	@ApiModelProperty("交易账户类型")
	private String accountType;
	
	@ApiModelProperty("账户期初金额")
	private double initialAmount;
	
	@ApiModelProperty("库存盈亏")
	private double inventoryPnL;
	
	@ApiModelProperty("做市盈亏")
	private double marketMakingPnL;
	
	@ApiModelProperty("手续费")
	private double fee;
	
	@ApiModelProperty("运行天数")
	private int runningDays;
	
	@ApiModelProperty("预计年化收益")
	private double annualizedReturn;

}
