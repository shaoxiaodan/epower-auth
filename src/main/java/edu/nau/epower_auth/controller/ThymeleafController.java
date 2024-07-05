package edu.nau.epower_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thymeleaf")
public class ThymeleafController {

	@GetMapping("index")
	public String index() {
		
		System.out.println("ThymeleafController :: index");
		//无需添加后缀，在配置文件中已经指定了页面返回后缀
		return "index";
	}
	
	@GetMapping("ok")
	public String ok() {
		
		System.out.println("ThymeleafController :: ok");
		//无需添加后缀，在配置文件中已经指定了页面返回后缀
		return "ok";
	}
}
