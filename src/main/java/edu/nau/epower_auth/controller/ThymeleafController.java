package edu.nau.epower_auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

	@GetMapping("hello")
	public String index() {
		
		//无需添加后缀，在配置文件中已经指定了页面返回后缀
		return "index";
	}
	
}
