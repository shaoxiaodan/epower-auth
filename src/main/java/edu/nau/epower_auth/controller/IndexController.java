package edu.nau.epower_auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.ListUtils;

import edu.nau.epower_auth.common.ConstantUtils;
import edu.nau.epower_auth.common.SessionUtils;
import edu.nau.epower_auth.dao.Menu;
import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.service.MenuService;
import edu.nau.epower_auth.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 主页控制器
 * 
 * @ClassName: IndexController
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-13 03:03:23
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private UrlService urlService;

	/*
	 * 首页page
	 */
	@GetMapping("index")
	public String indexPage(HttpServletRequest req) {

		Role defRole = null;
		List<Role> roleList = null;

		// 读取session中的默认角色 & role list
		defRole = (Role) SessionUtils.retrieveSession(req, ConstantUtils.SESSION_DEF_ROLE);
		roleList = (List<Role>) SessionUtils.retrieveSession(req, ConstantUtils.SESSION_USER_ROLES);

		// 当前用户没有默认角色
		if (defRole == null) {
			// 拿role list中的第一个，作为用户的默认role
			defRole = roleList.get(0);

			// 保存默认role
			SessionUtils.updateSession(req, ConstantUtils.SESSION_DEF_ROLE, defRole);
		}

		// 渲染首页
		return "system/index";
	}

	/*
	 * 切换用户角色
	 */
	@PostMapping("rolechange")
	public String userRoleChg(HttpServletRequest req, @RequestParam(name = "def", defaultValue = "0") int roleId) {

		Role defRole = null; // 用户的默认角色
		List<Role> roleList = null; // 用户的role list

		// 1，读取session中的role list
		roleList = (List<Role>) SessionUtils.retrieveSession(req, ConstantUtils.SESSION_USER_ROLES);

		// 2，检查角色的roleId
		if (roleId > 0) {
			for (Role role : roleList) {
				if (role.getId() == roleId) {
					defRole = role; // 3，找到对应的角色
					break; // 跳出当前循环
				}
			}
		} else {
			// 4，设置一个默认的角色
			defRole = roleList.get(0);
		}

		// 5，通过人工非法输入def，试图返回不存在的role
		if (defRole == null) {
			// 设置一个非法数值，并传递到前端做处理
			defRole = new Role();
			defRole.setId(-999);
		}

		// 6，当前角色是否为ROOT超级管理员
		if (defRole.isRoot()) {
			// 获取所有菜单list
			List<Menu> menuList = menuService.listMenu();
			if (!ListUtils.isEmpty(menuList)) {
				for (Menu menu : menuList) {
					menu.setUrlList(urlService.findUrlByMenuId(menu.getId()));
				}
			}
			// 为ROOT角色，重新装配所有menu
			defRole.setMenuList(menuList);
		}

		// 7，更新defrole的session
		SessionUtils.updateSession(req, ConstantUtils.SESSION_DEF_ROLE, defRole);

		return "redirect:index";
	}

}
