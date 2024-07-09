package edu.nau.epower_auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("list")
	public String listUser(ModelMap modelMap) {

		List<User> users = userService.listUser();
		modelMap.addAttribute("users", users);

		return "system/user/list";
	}

	@GetMapping("add")
	public String addPage(Model model) {

		model.addAttribute("adduser", new User());
		return "system/user/add";
	}

	@PostMapping("adduser")
	public String addUser(User user) {

		int add = userService.addUser(user);
		return "redirect:list";
	}

	@GetMapping("update")
	public String updatePage(@RequestParam("id") int userId, Model model) {

		User user = userService.getUser(userId);
		model.addAttribute("updateuser", user);
		return "system/user/update";
	}

	@PostMapping("updateuser")
	public String updateUser(User user) {
		int update = userService.updateUser(user);
		return "redirect:list";
	}

	@GetMapping("remove")
	public String removeUser(@RequestParam("id") int userId) {

		int remove = userService.removeUser(userId);
		return "redirect:list";
	}

}
