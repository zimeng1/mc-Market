package org.ethh.dal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * YieldCurveDTO
 *
 * @author GZM
 * @since 2024/12/16 下午2:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "账户资产信息")
public class YieldCurveDTO {
	@ApiModelProperty(notes = "策略ID")
	Long strategyId;
	
	@ApiModelProperty(notes = "时间")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	Date createTime;
	
	@ApiModelProperty(notes = "库存收益")
	String stockReceipt;
	
	@ApiModelProperty(notes = "做市收益")
	String marketReceipt;
	
	@ApiModelProperty(notes = "手续费")
	String fee;
	
	@ApiModelProperty(notes = "产品价格")
	String productPrice;
	
}
