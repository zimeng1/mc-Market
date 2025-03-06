package org.ethh.dal.service;

import org.ethh.dal.dto.TradeDetailDTO;
import org.ethh.dal.entity.TradeDetailPO;
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
 * @since 2024-12-10
 */
public interface TradeDetailService extends IService<TradeDetailPO> {
	
	BasePageRsp<TradeDetailDTO> pageTradeDetail(StrategyRequest strategyRequest);
}
