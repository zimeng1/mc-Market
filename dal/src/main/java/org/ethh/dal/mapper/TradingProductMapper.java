package org.ethh.dal.mapper;

import org.apache.ibatis.annotations.*;
import org.ethh.dal.entity.TradingProduct;

import java.util.List;

/**
 * @author wangyifei
 */
@Mapper
public interface TradingProductMapper {

    @Select("SELECT * FROM trading_product")
    List<TradingProduct> getAll();

    @Select("SELECT * FROM trading_product WHERE id = #{id}")
    TradingProduct getById(@Param("id") Long id);
    
    @Select("SELECT id,symbol FROM trading_product WHERE product_name = #{name}")
    List<TradingProduct> getByName(@Param("name") String name);

    @Insert("INSERT INTO trading_product (symbol, exchange_id, product_type, product_kind, " +
            "price_precision, step_size, first_trading_day, last_trading_day, first_notice_day, last_notice_day, " +
            "first_delivery_day, last_delivery_day, initial_margin, margin_currency, contract_multiplier, " +
            "base_currency, profit_currency, fee) " +
            "VALUES (#{symbol}, #{exchangeId}, #{productType}, #{productKind}, " +
            "#{pricePrecision}, #{stepSize}, #{firstTradingDay}, #{lastTradingDay}, #{firstNoticeDay}, " +
            "#{lastNoticeDay}, #{firstDeliveryDay}, #{lastDeliveryDay}, #{initialMargin}, #{marginCurrency}, " +
            "#{contractMultiplier}, #{baseCurrency}, #{profitCurrency}, #{fee})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(TradingProduct tradingProduct);

    @Update("UPDATE trading_product SET " +
            "symbol = #{symbol}, " +
            "exchange_id = #{exchangeId}, " +
            "product_type = #{productType}, " +
            "product_kind = #{productKind}, " +
            "price_precision = #{pricePrecision}, " +
            "step_size = #{stepSize}, " +
            "first_trading_day = #{firstTradingDay}, " +
            "last_trading_day = #{lastTradingDay}, " +
            "first_notice_day = #{firstNoticeDay}, " +
            "last_notice_day = #{lastNoticeDay}, " +
            "first_delivery_day = #{firstDeliveryDay}, " +
            "last_delivery_day = #{lastDeliveryDay}, " +
            "initial_margin = #{initialMargin}, " +
            "margin_currency = #{marginCurrency}, " +
            "contract_multiplier = #{contractMultiplier}, " +
            "base_currency = #{baseCurrency}, " +
            "profit_currency = #{profitCurrency}, " +
            "fee = #{fee} " +
            "WHERE id = #{id}")
    void update(TradingProduct tradingProduct);

    @Delete("DELETE FROM trading_product WHERE id = #{id}")
    void deleteById(@Param("id") Long id);
    
}
