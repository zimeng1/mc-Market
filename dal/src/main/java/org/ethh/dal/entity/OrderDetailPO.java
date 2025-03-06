package org.ethh.dal.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2024-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order_detail")
public class OrderDetailPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("CreateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createtime;

    @TableField("UpdateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatetime;

    /**
     * 策略ID
     */
    @TableField("StrategyID")
    private String strategyid;

    /**
     * 策略批次ID
     */
    @TableField("BatchNo")
    private Long batchno;

    /**
     * 订单策略算法类型
     */
    @TableField("OrderAlgoType")
    private Integer orderalgotype;

    /**
     * 订单类型
     */
    @TableField("OrderType")
    private Integer ordertype;

    /**
     * 客户端订单编号
     */
    @TableField("ClientOrderID")
    private Long clientorderid;

    /**
     * 用户ID
     */
    @TableField("UserId")
    private String userid;

    /**
     * 资金账号
     */
    @TableField("AccountNo")
    private String accountno;

    /**
     * 交易所代码
     */
    @TableField("ExchangeCode")
    private String exchangecode;

    /**
     * 合约代码
     */
    @TableField("ContractCode")
    private String contractcode;

    /**
     * 订单号
     */
    @TableField("OrderNo")
    private String orderno;

    /**
     * 订单层级
     */
    @TableField("OrderLevel")
    private String orderlevel;

    /**
     * 委托数量
     */
    @TableField("OrderQty")
    private BigDecimal orderqty;

    /**
     * 已成交数量
     */
    @TableField("FilledQty")
    private BigDecimal filledqty;

    /**
     * 撤销数量
     */
    @TableField("CancelledQty")
    private BigDecimal cancelledqty;

    /**
     * 成交均价
     */
    @TableField("FilledAvgPrice")
    private BigDecimal filledavgprice;

    /**
     * 是否取消
     */
    @TableField("IsCanceled")
    private Integer iscanceled;

    /**
     * 成交总手续费
     */
    @TableField("FilledTotalFee")
    private BigDecimal filledtotalfee;

    /**
     * 顺序号
     */
    @TableField("SequenceNo")
    private Integer sequenceno;

    /**
     * 持买保证金
     */
    @TableField("LongPosMargin")
    private BigDecimal longposmargin;

    /**
     * 持卖保证金
     */
    @TableField("ShortPosMargin")
    private BigDecimal shortposmargin;


}
