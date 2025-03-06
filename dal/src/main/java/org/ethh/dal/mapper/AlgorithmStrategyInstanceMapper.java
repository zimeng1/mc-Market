package org.ethh.dal.mapper;


import org.apache.ibatis.annotations.*;
import org.ethh.dal.entity.AlgorithmStrategyInstance;

import java.util.List;

/**
 * @author wangyifei
 */

@Mapper
public interface AlgorithmStrategyInstanceMapper {

    @Select("SELECT * FROM algorithm_strategy_instance")
    List<AlgorithmStrategyInstance> getAll();

    @Select("SELECT * FROM algorithm_strategy_instance WHERE id = #{id}")
    AlgorithmStrategyInstance getById(@Param("id") Long id);

    @Insert("INSERT INTO algorithm_strategy_instance (algorithm_strategy_id, algorithm_strategy_operate) " +
            "VALUES (#{algorithmStrategyId}, #{algorithmStrategyOperate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(AlgorithmStrategyInstance algorithmStrategyInstance);

    @Update("UPDATE algorithm_strategy_instance SET " +
            "algorithm_strategy_id = #{algorithmStrategyId}, " +
            "algorithm_strategy_operate = #{algorithmStrategyOperate} " +
            "WHERE id = #{id}")
    void update(AlgorithmStrategyInstance algorithmStrategyInstance);

    @Delete("DELETE FROM algorithm_strategy_instance WHERE id = #{id}")
    void deleteById(@Param("id") Long id);
}
