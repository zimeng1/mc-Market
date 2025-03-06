package org.ethh.dal.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ethh.dal.dto.OrderDetailDTO;
import org.ethh.dal.dto.TradeDetailDTO;
import org.ethh.dal.entity.TradeDetailPO;
import org.ethh.dal.mapper.TradeDetailMapper;
import org.ethh.dal.req.StrategyRequest;
import org.ethh.dal.service.TradeDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ethh.dal.service.base.BasePageRsp;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zimeng
 * @since 2024-12-10
 */
@Service
public class TradeDetailServiceImpl extends ServiceImpl<TradeDetailMapper, TradeDetailPO> implements TradeDetailService {
	
	@Override
	public BasePageRsp<TradeDetailDTO> pageTradeDetail(StrategyRequest req) {
		Page<TradeDetailDTO> page = new Page<>(req.getCurrent(), req.getSize());
		page = (Page<TradeDetailDTO>) baseMapper.pageTradeDetail(page, req);
		return BasePageRsp.valueOf(page);
	}
}
