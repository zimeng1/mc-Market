package org.ethh.dal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author wangyifei
 * 交易产品信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradingProduct implements Serializable {

    private Long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;

    // 交易产品符号
    private String symbol;

    // 交易所主键
    private String exchangeId;

    // 交易产品类型
    private String productType;

    // 交易产品种类
    private String productKind;

    // 价格精度
    private Integer pricePrecision;

    // 步长
    private Integer stepSize;

    // 第一交易日
    private Timestamp firstTradingDay;

    // 最后交易日
    private Timestamp lastTradingDay;

    // 第一通知日

    private Timestamp firstNoticeDay;

    // 最后通知日
    private Timestamp lastNoticeDay;

    // 第一交割日
    private Timestamp firstDeliveryDay;

    // 最后交割日
    private Timestamp lastDeliveryDay;

    // 初始保证金
    private Long initialMargin;

    // 保证金货币
    private String marginCurrency;

    // 合约乘数
    private Integer contractMultiplier;

    // 基础货币
    private String baseCurrency;

    // 盈利货币
    private String profitCurrency;

    // 手续费
    private Double fee;
}
