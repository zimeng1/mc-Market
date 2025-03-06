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
 * 算法策略实体
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlgorithmStrategy implements Serializable {

    /**
     * 主键，算法策略ID
     */
    private Long id;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;
    
    /**
     * 策略名称
     */
    private String strategyName;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;

    /**
     * 交易所主键
     */
    private Long exchangeId;

    /**
     * 算法参数ID
     */
    private Long algorithmParametersId;

    /**
     * 交易产品ID
     */
    private Long tradingProductId;

    /**
     * 业务线ID
     */
    private Long businessLineId;

    /**
     * 交易账户ID
     */
    private Long tradingAccountId;

    /**
     * 算法状态
     */
    private String algorithmStatus;
}
