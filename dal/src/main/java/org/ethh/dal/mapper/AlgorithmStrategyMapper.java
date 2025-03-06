package org.ethh.dal.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.ethh.dal.dto.*;
import org.ethh.dal.entity.AlgorithmStrategy;
import org.ethh.dal.req.StrategyRequest;
import org.ethh.dal.service.base.BasePageReq;

import java.util.List;
import java.util.Map;

/**
 * @author wangyifei
 */
@Mapper
public interface AlgorithmStrategyMapper {

    @Select("SELECT * FROM algorithm_strategy")
    List<AlgorithmStrategy> getAll();

    @Select("SELECT * FROM algorithm_strategy WHERE id = #{id}")
    AlgorithmStrategy getById(@Param("id") Long id);

    @Insert("INSERT INTO algorithm_strategy (strategy_name, exchange_id, algorithm_parameters_id, " +
            "trading_product_id, business_line_id, trading_account_id, algorithm_status) " +
            "VALUES (#{strategyName}, #{exchangeId}, #{algorithmParametersId}, " +
            "#{tradingProductId}, #{businessLineId}, #{tradingAccountId}, #{algorithmStatus})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(AlgorithmStrategy algorithmStrategy);

    @Update("UPDATE algorithm_strategy SET " +
            "exchange_id = #{exchangeId}, " +
            "algorithm_parameters_id = #{algorithmParametersId}, " +
            "trading_product_id = #{tradingProductId}, " +
            "business_line_id = #{businessLineId}, " +
            "trading_account_id = #{tradingAccountId}, " +
            "algorithm_status = #{algorithmStatus} " +
            "WHERE id = #{id}")
    void update(AlgorithmStrategy algorithmStrategy);

    @Delete("DELETE FROM algorithm_strategy WHERE id = #{id}")
    void deleteById(@Param("id") Long id);
    
    Page<StrategyOverviewDTO> selectStrategyOverview(IPage<StrategyOverviewDTO> page);
    
    Page<StrategyOverviewDTO> selectStrategyOverviewUndeployed(IPage<StrategyOverviewDTO> page);
    
    StrategyParametersDTO selectStrategyParams(Long id);
    
    Boolean updateStrategyParams(Map<String,Object> map);
    
    List<AccountAssetsDTO> selectAccountAssetsChange(Long algorithmStrategyId);
    
    List<YieldCurveDTO> selectYieldCurve(Long algorithmStrategyId);
}
