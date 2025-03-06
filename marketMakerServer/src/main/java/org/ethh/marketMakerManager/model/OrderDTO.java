package org.ethh.marketMakerManager.model;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.ethh.common.BaseDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderDTO
 *
 * @author GZM
 * @since 2024/12/3 下午3:02
 */
@Data
public class OrderDTO extends BaseDTO {
	
	/**
	 * 买入价与深度
	 */
	@ApiModelProperty("买入价与深度")
	private List<PriceDetail> bidList;
	
	/**
	 * 卖入价与深度
	 */
	@ApiModelProperty("卖入价与深度")
	private List<PriceDetail> askList;
	
	/**
	 * 中位价
	 */
	@ApiModelProperty("中位价")
	private BigDecimal midPrice;
	
	/**
	 * 交易对
	 */
	@ApiModelProperty("交易对")
	private String symbol;
	
	public static OrderDTO jsonToOrderDTO(String json) {
		OrderDTO orderDTO = new OrderDTO();
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		List<OrderDTO.PriceDetail> bidPriceList = new ArrayList<>();
		List<OrderDTO.PriceDetail> askPriceList = new ArrayList<>();
		orderDTO.setSymbol(jsonObject.getString("symbol"));
		orderDTO.setMidPrice(jsonObject.getJSONObject("midPrice").getBigDecimal("intVal"));
		JSONArray asksList = jsonObject.getJSONArray("asksList");
		JSONArray bidsList = jsonObject.getJSONArray("bidsList");
		
		setPrice(askPriceList, asksList);
		orderDTO.setAskList(askPriceList);
		
		setPrice(bidPriceList, bidsList);
		orderDTO.setBidList(bidPriceList);
		
		return orderDTO;
		
	}
	
	private static void setPrice(List<OrderDTO.PriceDetail> askPriceList, JSONArray asksList) {
		for(Object ask : asksList) {
			OrderDTO.PriceDetail priceDetail = new OrderDTO.PriceDetail();
			JSONObject askJson = JSONObject.parseObject(ask.toString());
			priceDetail.setLevel(askJson.getString("level"));
			priceDetail.setPrice(askJson.getJSONObject("price").getBigDecimal("intVal"));
			priceDetail.setQty(askJson.getJSONObject("qty").getBigDecimal("intVal"));
			askPriceList.add(priceDetail);
		}
	}
	
	
	@Data
	public static class PriceDetail{
		String level;
		BigDecimal price;
		BigDecimal qty;
	}
	
}
