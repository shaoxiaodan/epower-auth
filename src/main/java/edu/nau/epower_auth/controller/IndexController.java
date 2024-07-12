package edu.nau.epower_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nau.epower_auth.service.RoleService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private RoleService roleService;

	@GetMapping("index")
	public String indexPage(HttpServletRequest req) {

		return "system/index";
	}

	@GetMapping("rolechg")
	public String chgRole() {

		// TODO
		return "";
	}

}
