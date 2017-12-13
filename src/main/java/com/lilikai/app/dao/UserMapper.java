package com.lilikai.app.dao;

import com.lilikai.app.core.Mapper;
import com.lilikai.app.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper extends Mapper<User> {
    @Select("select * from x_user where id = #{id}")
    @Results(value = {
            @Result(property = "realName", column = "real_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "addTime", column = "add_time", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "isActive", column = "is_active", javaType = Integer.class, jdbcType = JdbcType.INTEGER)})
    User findTest(@Param("id") Integer id);

    User getUserTest(Integer id);
}