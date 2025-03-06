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
 * 交易账户
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradingAccount implements Serializable {

    /**
     * 主键，交易账户ID
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
     * 交易所主键
     */
    private Long exchangeId;

    /**
     * 账户主体
     */
    private String accountSubject;

    /**
     * 交易账户
     */
    private String accountId;
    
    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 初始余额
     */
    private Double initialCapital;

    /**
     * 追加保证金比例
     */
    private Double additionalMarginRatio;

    /**
     * 当前保证金比例
     */
    private Double currentMarginRatio;

    /**
     * 强平比例
     */
    private Double leverageRatio;

}
