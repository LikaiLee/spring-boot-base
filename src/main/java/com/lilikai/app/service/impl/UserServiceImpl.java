package com.lilikai.app.service.impl;

import com.lilikai.app.dao.UserMapper;
import com.lilikai.app.model.User;
import com.lilikai.app.service.UserService;
import com.lilikai.app.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by LikaiLee on 2017/12/13.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper xUserMapper;

    @Override
    public User findTest(Integer id) {
        return xUserMapper.findTest(id);
    }

    @Override
    public User getUserTest(Integer id) {
        return xUserMapper.getUserTest(id);
    }
}
