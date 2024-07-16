package edu.nau.epower_auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.service.UserService;

@RestController
@RequestMapping("/")
public class PageHelperController {

	@Autowired
	private UserService userService;

	@GetMapping("testpage")
	public List<User> testPageHelper(int pageNum, int pageSize) {

		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userService.listUser(2);
		return users;
	}

	@GetMapping("testpage2")
	public PageInfo<User> testPageHelper2(int pageNum, int pageSize) {

		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userService.listUser(2);
		PageInfo<User> page = new PageInfo<User>(users);
		return page;
	}

}
