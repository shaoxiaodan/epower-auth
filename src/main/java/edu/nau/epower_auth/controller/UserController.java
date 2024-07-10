package edu.nau.epower_auth.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.dao.UserRole;
import edu.nau.epower_auth.service.RoleService;
import edu.nau.epower_auth.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping("list")
	public String listUser(@RequestParam(defaultValue = "1") int pageNum, ModelMap modelMap) {

		int pageSize = 5;
		
//		if (pageNum == null) {
//			pageNum = 1;
//		}

		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userService.listUser();
		PageInfo<User> pages = new PageInfo<User>(users);

		modelMap.addAttribute("users", users);
		modelMap.addAttribute("pages", pages);

		return "system/user/list";
	}

	@GetMapping("add")
	public String addPage(Model model) {

		model.addAttribute("adduser", new User());
		return "system/user/add";
	}

	@PostMapping("adduser")
	public String addUser(User user) {

		if (user != null) {
			String encryptPwd = "";

//			encryptPwd = user.getPassword();
//			encryptPwd = EncryptUtils.getMD5(encryptPwd); // 密码md5加密
//			user.setPassword(encryptPwd);
			user.setCreateTime(new Date());
			int add = userService.addUser(user);
		}

		return "redirect:list";
	}

	@GetMapping("update")
	public String updatePage(@RequestParam("uid") int userId, Model model) {

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
	public String removeUser(@RequestParam("uid") int userId) {

		int remove = userService.removeUser(userId);
		return "redirect:list";
	}

	@GetMapping("auth")
	public String authPage(@RequestParam("uid") int userId, Model model) {

		User user = userService.getUser(userId);
		List<Role> userRoles = roleService.findRoleByUserId(userId);
		List<Role> roles = roleService.listRole();

		UserRole userRole = new UserRole();
		userRole.setUserId(userId);

		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		model.addAttribute("userroles", userRoles);
		model.addAttribute("addrole", userRole);

		return "system/user/auth";
	}

	@PostMapping("addrole")
	public String addAuth(UserRole userRole) {

		// 1，先检查用户角色是否存在
		UserRole ur = userService.getUserRole(userRole);
		if (ur != null) {
			// 2，已存在用户角色，先删除该用户角色
			int removeRole = userService.removeUserRoleAuth(userRole);
		}

		// 3，再添加新的角色
		int authRole = userService.addUserRoleAuth(userRole);

		// 4，重定向返回auth列表
		return "redirect:auth?uid=" + userRole.getUserId();
	}

	@GetMapping("removerole")
	public String removeAuth(@RequestParam("uid") int userId, @RequestParam("rid") int roleId) {

		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		int remove = userService.removeUserRoleAuth(userRole);
		return "redirect:auth?uid=" + userId;
	}
}
