package com.lilikai.app.service;
import com.lilikai.app.model.User;
import com.lilikai.app.core.Service;
import org.apache.ibatis.annotations.Param;


/**
 * Created by LikaiLee on 2017/12/13.
 */
public interface UserService extends Service<User> {
    User findTest(Integer id);
    User getUserTest(Integer id);
}
