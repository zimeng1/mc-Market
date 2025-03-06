package org.ethh.dal.mapper;

import org.apache.ibatis.annotations.*;
import org.ethh.dal.entity.BusinessLine;

import java.util.List;

/**
 * @author wangyifei
 */
@Mapper
public interface BusinessLineMapper {
    @Select("SELECT * FROM business_line")
    List<BusinessLine> getAll();

    @Select("SELECT * FROM business_line WHERE id = #{id}")
    BusinessLine getById(@Param("id") Long id);

    @Insert("INSERT INTO business_line (group_id, group_name) " +
            "VALUES (#{groupId}, #{groupName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(BusinessLine businessLine);

    @Update("UPDATE business_line SET " +
            "group_id = #{groupId}, " +
            "group_name = #{groupName} " +
            "WHERE id = #{id}")
    void update(BusinessLine businessLine);

    @Delete("DELETE FROM business_line WHERE id = #{id}")
    void deleteById(@Param("id") Long id);
}
