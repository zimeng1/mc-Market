package org.ethh.dal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PositionInformationDTO
 * 持仓信息
 * @author GZM
 * @since 2024/11/29 下午3:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionInformationDTO {
	//产品
	private String product;
	//持仓数量
	private String positionQuantity;
	//建仓平均价
	private String AverageEntryPrice;
	//仓位市值
	private String positionMarketValue;
	//市场价
	private String marketPrice;
	//浮动盈亏
	private String FloatingPL;

}
