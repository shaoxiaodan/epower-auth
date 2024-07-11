package edu.nau.epower_auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

	@GetMapping("login")
	public String loginPage() {

		System.out.println("login...");
		return "login";
	}

}
