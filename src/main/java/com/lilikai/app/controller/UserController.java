package com.lilikai.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lilikai.app.core.Result;
import com.lilikai.app.core.ResultGenerator;
import com.lilikai.app.model.User;
import com.lilikai.app.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by LiKaiLee on 2017/11/17.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	@ApiOperation(value = "添加用户", notes = "根据参数信息添加用户")
	@ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User")
	@PostMapping("/add")
	public Result add(User user) {
		userService.save(user);
		return ResultGenerator.genSuccessResult();
	}
	
	@GetMapping("/test/{name}")
	public Result test(@PathVariable("name") String name) {
		return ResultGenerator.genSuccessResult(userService.getTest(name));
	}

	@ApiOperation(value = "删除用户", notes = "根据id删除用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
	@PostMapping("/delete")
	public Result delete(@RequestParam Integer id) {
		userService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@ApiOperation(value = "更新用户", notes = "根据参数信息更新用户")
	@ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User")
	@PostMapping("/update")
	public Result update(User user) {
		userService.update(user);
		return ResultGenerator.genSuccessResult();
	}

	@ApiOperation(value = "获取某个用户", notes = "根据id获取一个用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
	@PostMapping("/detail")
	public Result detail(@RequestParam Integer id) {
		User user = userService.findById(id);
		return ResultGenerator.genSuccessResult(user);
	}

	@ApiOperation(value = "获取所有用户", notes = "获取用户分页数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "Integer") })
	@PostMapping("/list")
	public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<User> list = userService.findAll();
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@ApiOperation(value = "获取某个用户", notes = "根据id获取一个用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
	@GetMapping("/get/{id}")
	public Result getUserById(@PathVariable("id") Integer id) {
		User user = userService.getUserById(id);
		return ResultGenerator.genSuccessResult(user);
	}

	@ApiOperation(value = "获取所有用户", notes = "获取所有用户")
	@GetMapping("/getall")
	public Result getAll() {
		// List<User> list = userService.getAll();
		List<User> list = userService.findAll();
		return ResultGenerator.genSuccessResult(list);
	}
}
