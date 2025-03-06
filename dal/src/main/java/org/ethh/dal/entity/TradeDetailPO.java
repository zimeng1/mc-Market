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
 * @since 2024-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("trade_detail")
public class TradeDetailPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @TableField("CreateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createtime;

    /**
     * 更新时间
     */
    @TableField("UpdateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatetime;

    /**
     * 策略ID
     */
    @TableField("StrategyID")
    private String strategyid;

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
     * 成交编号（包括订单编号）
     */
    @TableField("FilledNo")
    private String filledno;

    /**
     * 订单号
     */
    @TableField("OrderNo")
    private String orderno;

    /**
     * 系统编号
     */
    @TableField("SystemNo")
    private String systemno;

    /**
     * 本地编号
     */
    @TableField("LocalNo")
    private String localno;

    /**
     * 交易所代码
     */
    @TableField("ExchangeCode")
    private String exchangecode;

    /**
     * 合约代码
     */
    @TableField("TreatyCode")
    private String treatycode;

    /**
     * 买卖标识：1=买 2=卖
     */
    @TableField("BuySale")
    private Integer buysale;

    /**
     * 成交数量
     */
    @TableField("FilledNumber")
    private Integer fillednumber;

    /**
     * 成交价格
     */
    @TableField("FilledPrice")
    private BigDecimal filledprice;

    /**
     * 成交日期（yyyy-MM-dd）
     */
    @TableField("FilledDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date filleddate;

    /**
     * 成交时间（hh:mm:ss）
     */
    @TableField("FilledTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date filledtime;

    /**
     * 成交手续费
     */
    @TableField("Commission")
    private BigDecimal commission;

    /**
     * 定单类型：1=限价单, 2=市价单，3=限价止损，4=止损
     */
    @TableField("OrderType")
    private Integer ordertype;

    /**
     * 开仓或平仓：1=开仓 2=平仓，3=平今，4=平昨
     */
    @TableField("AddReduce")
    private Integer addreduce;


}
