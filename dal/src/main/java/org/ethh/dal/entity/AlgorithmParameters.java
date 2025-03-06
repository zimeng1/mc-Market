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
 * 算法参数
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlgorithmParameters implements Serializable {

    private long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;

    private String algorithmName;

    private String algorithmArgs;
}
