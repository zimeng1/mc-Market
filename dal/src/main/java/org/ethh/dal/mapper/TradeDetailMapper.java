package org.ethh.dal.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.ethh.dal.dto.HistoricalPositionInfoDTO;
import org.ethh.dal.dto.OrderDetailDTO;
import org.ethh.dal.dto.StrategyPerformanceDTO;
import org.ethh.dal.dto.TradeDetailDTO;
import org.ethh.dal.entity.TradeDetailPO;
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
 * @since 2024-12-10
 */
public interface TradeDetailMapper extends BaseMapper<TradeDetailPO> {
	
	IPage<TradeDetailDTO> pageTradeDetail(IPage<TradeDetailDTO> page, @Param("req") StrategyRequest req);
	
	List<HistoricalPositionInfoDTO> getHistoricalPositionInfo(Long algorithmStrategyId);
	
	List<StrategyPerformanceDTO> getStrategyPerformance(Long algorithmStrategyId);
}
