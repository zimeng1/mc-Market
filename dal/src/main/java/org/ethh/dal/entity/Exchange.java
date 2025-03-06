package org.ethh.dal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangyifei
 * 交易所
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exchange implements Serializable {

    private long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;
    
    @ApiModelProperty(notes = "交易所名称")
    private String exchangeName;
    
    @ApiModelProperty(notes = "交易所Code")
    private String exchangeCode;
    
    @ApiModelProperty(notes = "节假日")
    private String holiday;
    
    @ApiModelProperty(notes = "交易时间段")
    private String tradingTime;
}
