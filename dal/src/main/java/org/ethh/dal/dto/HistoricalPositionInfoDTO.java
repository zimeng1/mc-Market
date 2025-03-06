package org.ethh.dal.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * HistoricalPositionInfoDTO
 *
 * @author GZM
 * @since 2024/12/5 下午7:04
 */
@Data
@ApiModel(description = "历史仓位信息")
public class HistoricalPositionInfoDTO {
	
	@ApiModelProperty(notes = "产品")
	private String product;
	
	@ApiModelProperty(notes = "方向")
	private String direction;
	
	@ApiModelProperty(notes = "累计交易量（张）")
	private int totalVolume;
	
	@ApiModelProperty(notes = "均价")
	private double averagePrice;
	
	@ApiModelProperty(notes = "累计交易价值")
	private double totalTradeValue;
	
	@ApiModelProperty(notes = "累计盈亏")
	private double totalPnL;
	
	@ApiModelProperty(notes = "累计手续费")
	private double totalCommission;
}

