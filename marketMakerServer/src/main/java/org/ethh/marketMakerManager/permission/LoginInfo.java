package org.ethh.marketMakerManager.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * LoginInfo
 *
 * @author GZM
 * @since 2024/12/18 上午11:26
 */
@Data
@ApiModel(description = "登录信息")
public class LoginInfo {
	
	@ApiModelProperty(notes = "登录结果")
	String result;
	
	@ApiModelProperty(notes = "Token值")
	String token;
	
}
