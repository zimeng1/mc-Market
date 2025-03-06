package org.ethh.dal.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.ethh.dal.dto.OrderDetailDTO;
import org.ethh.dal.entity.OrderDetailPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ethh.dal.req.StrategyRequest;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zimeng
 * @since 2024-12-11
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetailPO> {
	
//	List<OrderDetailDTO> getOrderDetail(Map map);
	
	IPage<OrderDetailDTO> pageOrderDetail(IPage<OrderDetailDTO> page, @Param("req") StrategyRequest req);

}
