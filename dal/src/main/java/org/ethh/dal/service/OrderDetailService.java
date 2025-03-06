package org.ethh.dal.service;

import org.ethh.dal.dto.OrderDetailDTO;
import org.ethh.dal.entity.OrderDetailPO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ethh.dal.req.StrategyRequest;
import org.ethh.dal.service.base.BasePageRsp;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zimeng
 * @since 2024-12-11
 */
public interface OrderDetailService extends IService<OrderDetailPO> {
	
	BasePageRsp<OrderDetailDTO> pageOrderDetail(StrategyRequest strategyRequest);
}
