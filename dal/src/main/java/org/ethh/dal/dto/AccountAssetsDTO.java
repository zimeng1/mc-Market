package org.ethh.dal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * AccountAssetsDTO
 *
 * @author GZM
 * @since 2024/12/16 下午1:56
 */
@Data
@ApiModel(description = "账户资产信息")
public class AccountAssetsDTO {
	
	@ApiModelProperty(notes = "策略ID")
	Long strategyId;
	
	@ApiModelProperty(notes = "时间")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	Date createTime;
	
	@ApiModelProperty(notes = "资产余额")
	String accountAssets;
}
