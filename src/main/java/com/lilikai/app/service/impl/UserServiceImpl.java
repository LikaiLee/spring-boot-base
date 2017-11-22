package com.lilikai.app.service.impl;

import com.lilikai.app.core.AbstractService;
import com.lilikai.app.dao.UserMapper;
import com.lilikai.app.model.User;
import com.lilikai.app.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by LiKaiLee on 2017/11/17.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
	@Resource
	private UserMapper xUserMapper;

	@Override
	public User getUserById(Integer id) {
		return xUserMapper.getUserById(id);
	}

	@Override
	public List<User> getAll() {
		return xUserMapper.selectAll();
	}

	@Override
	public List<User> getTest(String name) {
		// TODO Auto-generated method stub
		return xUserMapper.getTest(name);
	}
	
}
