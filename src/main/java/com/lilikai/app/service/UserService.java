package com.lilikai.app.service;
import java.util.List;

import com.lilikai.app.core.Service;
import com.lilikai.app.model.User;


/**
 * Created by LiKaiLee on 2017/11/17.
 */
public interface UserService extends Service<User> {
	User getUserById(Integer id);
	List<User> getAll();
}
