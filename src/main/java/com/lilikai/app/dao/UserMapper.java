package com.lilikai.app.dao;

import java.util.List;

import com.lilikai.app.core.Mapper;
import com.lilikai.app.model.User;

public interface UserMapper extends Mapper<User> {
	User getUserById(Integer id);
}