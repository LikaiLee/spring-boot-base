package com.lilikai.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping("/map")
	public String map() {
		return "map/index";
	}
}
