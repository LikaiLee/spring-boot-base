package com.lilikai.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lilikai.app.core.Mapper;
import com.lilikai.app.model.User;

public interface UserMapper extends Mapper<User> {
	User getUserById(Integer id);
	
	@Select("select * from x_user where name = #{name}")
	List<User> getTest(@Param("name") String name);
}