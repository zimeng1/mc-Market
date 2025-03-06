package org.ethh.dal.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ethh.dal.dto.OrderDetailDTO;
import org.ethh.dal.entity.OrderDetailPO;
import org.ethh.dal.mapper.OrderDetailMapper;
import org.ethh.dal.req.StrategyRequest;
import org.ethh.dal.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ethh.dal.service.base.BasePageRsp;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zimeng
 * @since 2024-12-11
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetailPO> implements OrderDetailService {
	
	@Override
	public BasePageRsp<OrderDetailDTO> pageOrderDetail(StrategyRequest req) {
		Page<OrderDetailDTO> page = new Page<>(req.getCurrent(), req.getSize());
		page = (Page<OrderDetailDTO>) baseMapper.pageOrderDetail(page, req);
		return BasePageRsp.valueOf(page);
	}
}
