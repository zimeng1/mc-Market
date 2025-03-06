package org.ethh.marketMakerManager.model;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wangyifei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlgorithmParametersDTO {

    private Long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;

    private String algorithmName;

    private JSONObject algorithmArgs;

}
