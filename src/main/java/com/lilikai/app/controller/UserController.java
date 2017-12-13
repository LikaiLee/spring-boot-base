package com.lilikai.app.controller;

import com.lilikai.app.core.ResultGenerator;
import com.lilikai.app.model.User;
import com.lilikai.app.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by LikaiLee on 2017/12/13.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/add")
    public String add(User user, HttpServletResponse response) {
        int flag = userService.save(user);
        if (flag == 1) {
            return ResultGenerator.ajaxReturn(response, "", "success", flag);
        } else {
            return ResultGenerator.ajaxReturn(response, "", "fail", 0);
        }
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id, HttpServletResponse response) {
        int flag = userService.deleteById(id);
        if (flag == 1) {
            return ResultGenerator.ajaxReturn(response, "", "success", flag);
        } else {
            return ResultGenerator.ajaxReturn(response, "", "fail", 0);
        }
    }

    @RequestMapping("/update")
    public String update(User user, HttpServletResponse response) {
        int flag = userService.update(user);
        if (flag == 1) {
            return ResultGenerator.ajaxReturn(response, "", "success", flag);
        } else {
            return ResultGenerator.ajaxReturn(response, "", "fail", 0);
        }
    }

    @RequestMapping("/detail")
    public String detail(@RequestParam Integer id, HttpServletResponse response) {
        User user = userService.findById(id);
        return ResultGenerator.ajaxReturn(response, user, "", 0);
    }

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, HttpServletResponse response) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return ResultGenerator.ajaxReturn(response, pageInfo, "", 0);
    }
}
