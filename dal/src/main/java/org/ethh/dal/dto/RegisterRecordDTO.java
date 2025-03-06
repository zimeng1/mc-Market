package org.ethh.dal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * RegisterRecordDTO
 *
 * @author GZM
 * @since 2024/12/16 下午4:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "挂单记录")
public class RegisterRecordDTO {
	
	@ApiModelProperty(notes = "策略ID")
	Long strategyId;
	
	String exchange;
	String pair;
	String side;
	String status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	Date date;
	double quantity;
	double limitPrice;
	String type;
	double execQty;
	double netExecQty;
	double avgPrice;
	double netConsid;


}
