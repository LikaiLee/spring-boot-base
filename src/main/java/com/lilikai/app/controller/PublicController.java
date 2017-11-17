package com.lilikai.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicController {

	@RequestMapping("/")
	public String login(Model model) {
		model.addAttribute("appName", "Spring-Boot App");
		return "public/index";
	}

	@RequestMapping(value = "/test")
	public String test(Model model) {
		model.addAttribute("appName", "Spring-Boot App");
		return "public/test";
	}
}
