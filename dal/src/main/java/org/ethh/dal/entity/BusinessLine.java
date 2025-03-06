package org.ethh.dal.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangyifei
 * 业务线数据
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessLine implements Serializable {

    /**
     * 主键，业务线ID
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
     * 分组ID
     */
    private String groupId;

    /**
     * 分组名称
     */
    private String groupName;
}
