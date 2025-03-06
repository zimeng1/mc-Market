package org.ethh.marketMakerManager.model;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * marketDataDTO
 *
 * @author GZM
 * @since 2024/11/25 下午3:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketDataDTO {
	
	@ApiModelProperty(notes = "产品名称")
	String product;
	
	@ApiModelProperty(notes = "最新价")
	String lastPrice;
	
	@ApiModelProperty(notes = "当日涨跌幅")
	String change;
	
	@ApiModelProperty(notes = "买方第一档 盘口深度")
	String bidQty;
	
	@ApiModelProperty(notes = "买入价")
	String bidPrice;
	
	@ApiModelProperty(notes = "卖出价")
	String askPrice;
	
	@ApiModelProperty(notes = "卖方第一档 盘口深度")
	String askQty;
	
	@ApiModelProperty(notes = "最后报价时间")
	String time;
	
	public static MarketDataDTO jsonToMarketData(String json) {
		MarketDataDTO marketDataDTO = new MarketDataDTO();
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		marketDataDTO.setProduct("GC2502");
		marketDataDTO.setAskPrice(jsonObject.getJSONObject("askPrice").getIntValue("intVal")+"");
		marketDataDTO.setBidPrice(jsonObject.getJSONObject("bidPrice").getIntValue("intVal")+"");
		return marketDataDTO;
	}
	
	
}
