package org.ethh.marketMakerManager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ethh.common.enums.AlgorithmStrategyOperate;

import java.util.Date;

/**
 * @author wangyifei
 * 算法策略实例
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlgorithmStrategyInstanceDTO {

    @ApiModelProperty(notes = "主键，算法策略实例ID")
    private Long id;

    @ApiModelProperty(notes = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    @ApiModelProperty(notes = "更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;

    @ApiModelProperty(notes = "当前算法策略ID")
    private Long algorithmStrategyId;

    @ApiModelProperty(notes = "管控命令")
    private AlgorithmStrategyOperate algorithmStrategyOperate;

    @ApiModelProperty(notes = "初次启动时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date deployedAt;
}
