package edu.nau.epower_auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ThymeleafController {

	@GetMapping("index")
	public String index() {
		System.out.println("ThymeleafController :: index");
		return "index";
	}
	
	@GetMapping("login")
	public String login() {
		System.out.println("ThymeleafController :: login");
		return "login";
	}
	
	@GetMapping("/system/menu_list")
	public String menuList(ModelMap modelMap) {
		modelMap.addAttribute("name", "menu_list");
		return "system/menu/menu_list";
	}
	
	@GetMapping("/system/user_list")
	public String userList(ModelMap modelMap) {
		
		modelMap.addAttribute("name", "user_list");
		return "system/user/user_list";
	}
	
	
}
