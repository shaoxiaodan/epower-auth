package edu.nau.epower_auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.ListUtils;

import edu.nau.epower_auth.dao.Menu;
import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.dao.Url;
import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.service.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private RoleService roleService;

	@GetMapping("index")
	public String indexPage(HttpServletRequest req) {

//		int userId = 0;
////		userId = 1; // admin
//		userId = 2; // root
////		userId = 3; 

		String pathStr = "";

		// 获取登录用户
		User loginUser = (User) req.getSession().getAttribute("loginuser");

		// 检查用户是否已登录
		if (loginUser != null) {

			List<Role> userList = null;
			List<Role> userRoleList = new ArrayList<Role>();
			List<Menu> userMenuList = new ArrayList<Menu>();
			List<Url> userUrlList = new ArrayList<Url>();

			userList = roleService.findRoleByUserId(loginUser.getId());

			if (!ListUtils.isEmpty(userList)) {
				for (Role role : userList) {
					userRoleList.add(role); // 装配role
					if (!ListUtils.isEmpty(role.getMenuList())) {
						for (Menu menu : role.getMenuList()) {
							userMenuList.add(menu); // 装配menu
							if (!ListUtils.isEmpty(menu.getUrlList())) {
								for (Url url : menu.getUrlList()) {
									userUrlList.add(url); // 装配url
								}
							}
						}
					}
				}
			}

//			modelMap.addAttribute("roles", userRoleList);
//			modelMap.addAttribute("menus", userMenuList);
//			modelMap.addAttribute("urls", userUrlList);

			HttpSession session = req.getSession();
			session.setAttribute("roles", userRoleList);
			session.setAttribute("menus", userMenuList);
			session.setAttribute("urls", userUrlList);

			pathStr = "system/index";
		} else {
			pathStr = "redirect:login";
		}

//		return "system/index";
		return pathStr;
	}

}
