package org.ethh.dal.mapper;


import org.apache.ibatis.annotations.*;
import org.ethh.dal.entity.TradingAccount;

import java.util.List;

/**
 * @author wangyifei
 */
@Mapper
public interface TradingAccountMapper {

    @Select("SELECT * FROM trading_account")
    List<TradingAccount> getAll();

    @Select("SELECT * FROM trading_account WHERE id = #{id}")
    TradingAccount getById(@Param("id") Long id);

    @Insert("INSERT INTO trading_account (exchange_id, account_subject, account_id, " +
            "initial_capital, additional_margin_ratio, current_margin_ratio, leverage_ratio) " +
            "VALUES (#{exchangeId}, #{accountSubject}, #{accountId}, " +
            "#{initialCapital}, #{additionalMarginRatio}, #{currentMarginRatio}, #{leverageRatio})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(TradingAccount tradingAccount);

    @Update("UPDATE trading_account SET " +
            "exchange_id = #{exchangeId}, " +
            "account_subject = #{accountSubject}, " +
            "account_id = #{accountId}, " +
            "initial_capital = #{initialCapital}, " +
            "additional_margin_ratio = #{additionalMarginRatio}, " +
            "current_margin_ratio = #{currentMarginRatio}, " +
            "leverage_ratio = #{leverageRatio} " +
            "WHERE id = #{id}")
    void update(TradingAccount tradingAccount);

    @Delete("DELETE FROM trading_account WHERE id = #{id}")
    void deleteById(@Param("id") Long id);
}
