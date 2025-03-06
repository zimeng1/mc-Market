package org.ethh.dal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wangyifei
 * 算法策略实例
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlgorithmStrategyInstance {

    /**
     * 主键，算法策略实例ID
     */
    private Long id;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;

    /**
     * 当前算法策略ID
     */
    private Long algorithmStrategyId;

    /**
     * 管控命令
     */
    private String algorithmStrategyOperate;

    /**
     * 初次启动时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date deployedAt;
}
