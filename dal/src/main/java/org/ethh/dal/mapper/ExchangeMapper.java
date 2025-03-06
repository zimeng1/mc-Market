package org.ethh.dal.mapper;

import org.apache.ibatis.annotations.*;
import org.ethh.dal.entity.Exchange;

import java.util.List;

/**
 * @author wangyifei
 */
@Mapper
public interface ExchangeMapper {

    @Insert("INSERT INTO exchange (id, exchange_name, exchange_code, holiday, trading_time) " +
            "VALUES (#{id}, #{exchangeName}, #{exchangeCode}, #{holiday}, #{tradingTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Exchange exchange);

    @Update("UPDATE exchange " +
            "SET exchange_name = #{exchangeName}, " +
            "    exchange_code = #{exchangeCode}, " +
            "    holiday = #{holiday}, " +
            "    trading_time = #{tradingTime} " +
            "WHERE id = #{id}")
    void update(Exchange exchange);

    @Delete("DELETE FROM exchange WHERE id = #{id}")
    void delete(Long id);

    @Select("SELECT * FROM exchange WHERE id = #{id}")
    Exchange getById(Long id);

    @Select("SELECT * FROM exchange")
    List<Exchange> selectAll();
}
