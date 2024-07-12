package edu.nau.epower_auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ThymeleafController {

	@GetMapping("/sys/menu_list")
	public String menuList(ModelMap modelMap) {
		modelMap.addAttribute("name", "menu_list");
		return "system/menu/menu_list";
	}
	
	@GetMapping("/sys/user_list")
	public String userList(ModelMap modelMap) {
		
		modelMap.addAttribute("name", "aaa");
		modelMap.addAttribute("age", 29);
		return "system/user/user_list";
	}
	
	
}
