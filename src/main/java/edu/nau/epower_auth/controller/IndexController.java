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
import edu.nau.epower_auth.service.RoleService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private RoleService roleService;

	@GetMapping("index")
	public String indexPage(ModelMap modelMap) {

		int userId = 0;
//		userId = 1; // admin
		userId = 2; // root
//		userId = 3; 
		
		List<Role> urList = roleService.findRoleByUserId(userId);

		List<Role> userRoles = new ArrayList<Role>();
		List<Menu> userMenus = new ArrayList<Menu>();
		List<Url> userUrls = new ArrayList<Url>();

		if (!ListUtils.isEmpty(urList)) {
			for (Role role : urList) {
				userRoles.add(role); // 装配role
				if (!ListUtils.isEmpty(role.getMenuList())) {
					for (Menu menu : role.getMenuList()) {
						userMenus.add(menu); // 装配menu
						if (!ListUtils.isEmpty(menu.getUrlList())) {
							for (Url url : menu.getUrlList()) {
								userUrls.add(url); // 装配url
							}
						}
					}
				}
			}
		}

		modelMap.addAttribute("roles", userRoles);
		modelMap.addAttribute("menus", userMenus);
		modelMap.addAttribute("urls", userUrls);

		return "index";
	}

}
