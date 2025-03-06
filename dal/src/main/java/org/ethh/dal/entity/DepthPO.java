package org.ethh.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author zimeng
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("depth")
public class DepthPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上一更新id
     */
    private Long lastUpdateId;

    /**
     * 买单价格
     */
    private BigDecimal bidPrice;

    /**
     * 买单深度
     */
    private BigDecimal bidAmount;

    /**
     * 卖单价格
     */
    private BigDecimal askPrice;

    /**
     * 卖单深度
     */
    private BigDecimal askAmount;

    /**
     * 交易所来源
     */
    private String market;

    /**
     * 交易对
     */
    private String symbol;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date gmtCreated;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date gmtModified;
    
//    public static OrderDTO convertToDTO(DepthPO depthPO) {
//        OrderDTO orderDTO = new OrderDTO();
//        BeanUtils.copyProperties(depthPO,orderDTO);
//        return orderDTO;
//    }
//
//    public static List<OrderDTO> convertToDTOList(List<DepthPO> depthList) {
//        List<OrderDTO> list = new ArrayList<OrderDTO>();
//        for (DepthPO depthPO : depthList) {
//            list.add(convertToDTO(depthPO)) ;
//        }
//        return list;
//    }


}
