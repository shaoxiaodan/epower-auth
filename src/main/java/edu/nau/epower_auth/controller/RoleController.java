package edu.nau.epower_auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
@RequestMapping("/role")
public class RoleController {

	private Map<String, Object> params = new HashMap<>();
	
	@GetMapping("/one")
	public Object one() {
	
		String name = "aaa";
		params.put("name", name);
		System.out.println("** controller::params=" + params);
		return params;
	}
	
}
