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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.nau.epower_auth.dao.Menu;
import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.dao.RoleMenu;
import edu.nau.epower_auth.service.MenuService;
import edu.nau.epower_auth.service.RoleService;

/**
 * 角色控制器
 * 
 * @ClassName: RoleController
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-13 03:15:54
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	/*
	 * 角色列表page
	 */
	@GetMapping("list")
	public String listRole(@RequestParam(defaultValue = "1") int pageNum, ModelMap modelMap) {

		int pageSize = 10;

		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles = roleService.listRole();
		PageInfo<Role> pages = new PageInfo<Role>(roles);

		modelMap.addAttribute("roles", roles);
		modelMap.addAttribute("pages", pages);

		return "system/role/list";
	}

	/*
	 * 角色添加page
	 */
	@GetMapping("add")
	public String addPage(Model model) {

		model.addAttribute("addrole", new Role());
		return "system/role/add";
	}

	/*
	 * 角色添加
	 */
	@PostMapping("addrole")
	public String addRole(Role role) {

		int add = roleService.addRole(role);
		return "redirect:list";
	}

	/*
	 * 角色更新page
	 */
	@GetMapping("update")
	public String updatePage(@RequestParam("rid") int roleId, Model model) {

		Role role = roleService.getRole(roleId);
		model.addAttribute("updaterole", role);
		return "system/role/update";
	}

	/*
	 * 角色更新
	 */
	@PostMapping("updaterole")
	public String updateRole(Role role) {
		int update = roleService.updateRole(role);
		return "redirect:list";
	}

	/*
	 * 角色删除
	 */
	@GetMapping("remove")
	public String removeRole(@RequestParam("rid") int roleId) {

		int remove = roleService.removeRole(roleId);
		return "redirect:list";
	}

	/*
	 * 角色授权page
	 */
	@GetMapping("auth")
	public String authPage(@RequestParam("rid") int roleId, Model model) {

		// 根据role id获取角色
		Role role = roleService.getRole(roleId);
		model.addAttribute("role", role);

		// 获取菜单列表
		List<Menu> menus = menuService.listMenu();
		model.addAttribute("menus", menus);

		// 根据role id获取角色所有菜单
		List<Menu> roleMenus = menuService.findMenuByRoleId(roleId);
		model.addAttribute("rolemenus", roleMenus);

		// 用role id创建menu映射对象，返回前端并绑定表单
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(roleId);
		model.addAttribute("addmenu", roleMenu);

		return "system/role/auth";
	}

	/*
	 * 角色授权添加
	 */
	@PostMapping("addmenu")
	public String addAuth(RoleMenu roleMenu) {

		// 1，先检查角色菜单是否存在
		RoleMenu rm = roleService.getRoleMenu(roleMenu);
		if (rm != null) {
			// 2，已存在角色菜单，先删除该角色菜单
			int remove = roleService.removeRoleMenuAuth(roleMenu);
		}

		// 3，再添加新的角色菜单
		int auth = roleService.addRoleMenuAuth(roleMenu);

		// 4，重定向返回auth列表
		return "redirect:auth?rid=" + roleMenu.getRoleId();
	}

	/*
	 * 角色授权删除
	 */
	@GetMapping("removemenu")
	public String removeAuth(@RequestParam("rid") int roleId, @RequestParam("mid") int menuId) {

		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(roleId);
		roleMenu.setMenuId(menuId);
		int remove = roleService.removeRoleMenuAuth(roleMenu);
		return "redirect:auth?rid=" + roleId;
	}

}
