package org.ethh.dal.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.ethh.dal.service.base.BasePageReq;

import java.util.Date;

/**
 * StrategyRequest
 *
 * @author GZM
 * @since 2024/12/10 下午7:32
 */
@Data
public class StrategyRequest extends BasePageReq {
	
	@ApiModelProperty(notes = "策略ID")
	private int algorithmStrategyId;
	
	@ApiModelProperty(notes = "开始时间")
	private Date startTime;
	
	@ApiModelProperty(notes = "结束时间")
	private Date endTime;
	
}
