package edu.nau.epower_auth.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;

import edu.nau.epower_auth.dao.Test;
import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.service.UserService;

/**
 * 
 * @ClassName: TestController
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 02:16:44
 */
@Controller
@RequestMapping("/")
public class TestController {

	@Autowired
	private UserService userService;

	@GetMapping("test")
	public String test(ModelMap modelMap, int pageNum, int pageSize) {

		PageHelper.startPage(pageNum, pageSize);

		String title = "new title";
		modelMap.addAttribute("title", title);

		Test user = new Test();
		user.setId(99);
		user.setUsername("Thymeleaf");
		user.setCreateTime(new Date());
		user.setVip(true);
		user.setTags(Arrays.asList("Java", "MySQL", "Thymeleaf", "Springboot"));
		modelMap.addAttribute("user", user);

		return "test";
	}

	@GetMapping("testlogin")
	public String testLogin(ModelMap modelMap) {

		List<User> userList = userService.listUser();
		modelMap.addAttribute("userlist", userList);
		System.out.println("testLogin, userList=" + userList);
		return "testlogin";
	}

}
