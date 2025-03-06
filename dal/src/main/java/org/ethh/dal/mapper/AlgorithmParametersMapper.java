package org.ethh.dal.mapper;

import org.apache.ibatis.annotations.*;
import org.ethh.dal.entity.AlgorithmParameters;

import java.util.List;

/**
 * @author wangyifei
 */

@Mapper
public interface AlgorithmParametersMapper {

    @Select("SELECT * FROM algorithm_parameters WHERE id = #{id}")
    AlgorithmParameters getById(Long id);

    @Select("SELECT * FROM algorithm_parameters LIMIT #{limit} OFFSET #{offset}")
    List<AlgorithmParameters> getAllWithPagination(@Param("limit") int limit, @Param("offset") int offset);

    Long insert(AlgorithmParameters algorithmParameter);

    @Update("UPDATE algorithm_parameters SET algorithm_name=#{algorithmName}, algorithm_args=#{algorithmArgs} WHERE id=#{id}")
    void update(AlgorithmParameters algorithmParameter);

    @Delete("DELETE FROM algorithm_parameters WHERE id=#{id}")
    void delete(Long id);
}
