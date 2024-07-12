package edu.nau.epower_auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.ListUtils;

import edu.nau.epower_auth.dao.Menu;
import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.dao.Url;
import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.service.LoginService;
import edu.nau.epower_auth.service.RoleService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * 登录控制器
 * 
 * @ClassName: LoginController
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-12 01:49:15
 */
@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private RoleService roleService;

	@GetMapping("login")
	public String loginPage(ModelMap modelMap) {

		// 创建登录用户绑定对象
		modelMap.put("user", new User());
		modelMap.addAttribute("msg", "请开始登录。");
		return "login";
	}

	@PostMapping("login")
	public String loginCheck(HttpServletRequest req, User user, ModelMap modelMap) {

		String msgStr = "";
		String urlStr = "login"; // 登录页面

		// 检查用户输入
		if (user != null) {
			// 用户输入都不为空
			if (StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getPassword())) {
				// 查询登录用户
				User loginUser = null;
				loginUser = loginService.findUserByUserNameAndPwd(user.getUsername(), user.getPassword());

				// 检查用户是否已登录
				if (loginUser != null) {

					List<Role> userList = null;
					List<Role> userRoleList = new ArrayList<Role>();
					List<Menu> userMenuList = new ArrayList<Menu>();
					List<Url> userUrlList = new ArrayList<Url>();

					// 获取用户所有的角色-权限-url信息
					userList = roleService.findRoleByUserId(loginUser.getId());

					if (!ListUtils.isEmpty(userList)) {

						// 开始装配用户的角色-权限-url信息
						for (Role role : userList) {
							userRoleList.add(role); // 装配role
							if (!ListUtils.isEmpty(role.getMenuList())) {
								for (Menu menu : role.getMenuList()) {
									userMenuList.add(menu); // 装配menu
									if (!ListUtils.isEmpty(menu.getUrlList())) {
										for (Url url : menu.getUrlList()) {
											userUrlList.add(url); // 装配url
										}
									}
								}
							}
						}
					}

					// 保存登录用户信息session
					HttpSession session = req.getSession();
					session.setAttribute("loginuser", loginUser); // 保存用户登录
					session.setAttribute("roles", userRoleList); // 保存用户角色
					session.setAttribute("menus", userMenuList); // 保存用户菜单
					session.setAttribute("urls", userUrlList); // 保存用户URL

					urlStr = "redirect:index"; // 重定向到index
				} else {
					msgStr = "请输入正确的用户名或密码。";
				}

			} else {
				msgStr = "请输入用户名或密码。";
			}

		} else {
			msgStr = "请输入登录信息。";
		}

		modelMap.put("msg", msgStr);
		return urlStr;
	}

	@GetMapping("logout")
	public String logOut(HttpServletRequest req, ModelMap modelMap) {

		HttpSession session = req.getSession();
		session.invalidate();
		modelMap.put("msg", "已退出登录。");
		return "redirect:login";
	}

}
