package edu.nau.epower_auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

//	private static String returnUrl = "system/role/";
//	private static String redirectUrl = "redirect:list";

	@GetMapping("list")
	public String listRole(ModelMap modelMap) {

		List<Role> roles = roleService.listRole();
		modelMap.addAttribute("roles", roles);

		return "system/role/list";
	}

	@GetMapping("add")
	public String addPage(Model model) {

		model.addAttribute("addrole", new Role());
		return "system/role/add";
	}

	@PostMapping("addrole")
	public String addRole(Role role) {

		int add = roleService.addRole(role);
		return "redirect:list";
	}

	@GetMapping("update")
	public String updatePage(@RequestParam("id") int roleId, Model model) {

		Role role = roleService.getRole(roleId);
		model.addAttribute("updaterole", role);
		return "system/role/update";
	}

	@PostMapping("updaterole")
	public String updateRole(Role role) {
		int update = roleService.updateRole(role);
		return "redirect:list";
	}

	@GetMapping("remove")
	public String removeRole(@RequestParam("id") int roleId) {

		int remove = roleService.removeRole(roleId);
		return "redirect:list";
	}

}
