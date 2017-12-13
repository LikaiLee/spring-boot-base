package com.lilikai.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lilikai.app.core.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lilikai.app.model.User;
import com.lilikai.app.service.UserService;

@Controller
public class PublicController {
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        List<User> list = userService.findAll();
        model.addAttribute("appName", "Spring-Boot App");
        model.addAttribute("list", list);
        return "index";
    }

    @RequestMapping("/test")
    public String test(HttpServletResponse response) {
        User user = userService.findTest((3));
        return ResultGenerator.ajaxReturn(response, user, "info", 0);
    }

    @RequestMapping("/test2")
    public String test2(HttpServletResponse response) {
        User user = userService.getUserTest((3));
        return ResultGenerator.ajaxReturn(response, user, "info", 0);
    }
}
