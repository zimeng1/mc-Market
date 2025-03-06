package org.ethh.marketMakerManager.model;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.ethh.common.BaseDTO;

/**
 * PositionInfoDTO
 *
 * @author GZM
 * @since 2024/12/5 下午7:02
 */
@Data
@ApiModel(description = "持仓信息")
public class PositionInfoDTO extends BaseDTO {
	
	@ApiModelProperty(notes = "产品")
	private String product;
	
	@ApiModelProperty(notes = "持买数量（张）")
	private double positionBuySize;
	
	@ApiModelProperty(notes = "持卖数量（张）")
	private double positionSaleSize;
	
	@ApiModelProperty(notes = "建仓持买平均价")
	private double averageBuyPrice;
	
	@ApiModelProperty(notes = "建仓持卖平均价")
	private double averageSalePrice;
	
	@ApiModelProperty(notes = "仓位买价市值")
	private double positionBuyValue;
	
	@ApiModelProperty(notes = "仓位卖价市值")
	private double positionSaleValue;
	
	@ApiModelProperty(notes = "市场价")
	private double marketPrice;
	
	@ApiModelProperty(notes = "浮动盈亏")
	private double floatingPnL;
	
	public static PositionInfoDTO jsonToPositionInfoDTO(String json) {
		PositionInfoDTO positionInfoDTO = new PositionInfoDTO();
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		positionInfoDTO.setProduct(jsonObject.getString("contractCode"));
		positionInfoDTO.setPositionBuySize(jsonObject.getDouble("longPositionQty"));
		positionInfoDTO.setPositionSaleSize(jsonObject.getDouble("shortPositionQty"));
		positionInfoDTO.setAverageBuyPrice(jsonObject.getDouble("longPosAveragePrx"));
		positionInfoDTO.setAverageSalePrice(jsonObject.getDouble("shortPosAveragePrx"));
		positionInfoDTO.setPositionBuyValue(jsonObject.getInteger("longPositionQty")*jsonObject.getDouble("longPosAveragePrx"));
		positionInfoDTO.setPositionSaleValue(jsonObject.getInteger("shortPositionQty")*jsonObject.getDouble("shortPosAveragePrx"));
		positionInfoDTO.setMarketPrice(0);
		positionInfoDTO.setFloatingPnL(0);
		
		return positionInfoDTO;
	}
	
}
