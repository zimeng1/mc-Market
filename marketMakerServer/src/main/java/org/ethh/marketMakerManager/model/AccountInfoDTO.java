package org.ethh.marketMakerManager.model;

import com.alibaba.fastjson2.JSONObject;
import common.Common;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.ethh.common.BaseDTO;

import java.math.BigDecimal;

/**
 * AccountInfo
 *
 * @author GZM
 * @since 2024/12/5 下午7:00
 */
@Data
@ApiModel(description = "交易账户信息")
public class AccountInfoDTO extends BaseDTO {
	
	@ApiModelProperty(notes = "交易标的")
	private String tradingAsset;
	
	@ApiModelProperty(notes = "策略名称")
	private String strategyName;
	
	@ApiModelProperty(notes = "交易账户")
	private String tradingAccount;
	
	@ApiModelProperty(notes = "交易账户类型")
	private String accountType;
	
	@ApiModelProperty(notes = "账户净值")
	private BigDecimal accountEquity;
	
	@ApiModelProperty(notes = "账户余额")
	private BigDecimal accountBalance;
	
	@ApiModelProperty(notes = "占用保证金")
	private BigDecimal usedMargin;
	
	@ApiModelProperty(notes = "可用保证金")
	private BigDecimal availableMargin;
	
	@ApiModelProperty(notes = "浮动盈亏")
	private BigDecimal floatingPnL;
	
	@ApiModelProperty(notes = "保证金比例")
	private BigDecimal marginRatio;
	
	public static AccountInfoDTO jsonToAccountInfoDTO(String json) {
		AccountInfoDTO positionInfoDTO = new AccountInfoDTO();
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		BigDecimal available = jsonObject.getBigDecimal("available").setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal frozenDeposit = jsonObject.getBigDecimal("frozenDeposit").setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal money = jsonObject.getBigDecimal("money").setScale(2, BigDecimal.ROUND_HALF_UP);
		
		positionInfoDTO.setTradingAsset("GC");
		positionInfoDTO.setStrategyName("AS策略");
		positionInfoDTO.setTradingAccount("TestingAccount");
		positionInfoDTO.setAccountType("测试账号");
		positionInfoDTO.setAccountEquity(available.add(frozenDeposit));
		positionInfoDTO.setAccountBalance(money); // 设置账户余额为500.0
		positionInfoDTO.setUsedMargin(frozenDeposit); // 设置占用保证金为200.0
		positionInfoDTO.setAvailableMargin(available); // 设置可用保证金为300.0
		positionInfoDTO.setFloatingPnL(new BigDecimal(0)); // 设置浮动盈亏为50.0
		positionInfoDTO.setMarginRatio(new BigDecimal(0)); // 设置保证金比例为0.25
		return positionInfoDTO;
	}
}

