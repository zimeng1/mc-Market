package org.ethh.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zimeng
 * @since 2024-12-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("strategy_operation_log")
public class StrategyOperationLogPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    private Long strategyId;

    private String operationContent;

    private String operationType;

    private String operator;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date modifiedAt;


}
