package edu.nau.epower_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("login")
	public String loginPage(ModelMap modelMap) {

		// 创建登录用户绑定对象
		modelMap.put("user", new User());
		return "login";
	}

	@PostMapping("loginchk")
	public String loginCheck(HttpServletRequest req, User user, ModelMap modelMap) {

		String pathStr = "";
		String msgStr = "";

		// 用户输入不为空
		if (user != null) {
			// 查询登录用户
			User loginUser = null;
			loginUser = loginService.findUserByUserNameAndPwd(user.getUsername(), user.getPassword());

			if (loginUser != null) {
				// 保存登录用户信息session
				HttpSession session = req.getSession();
				session.setAttribute("loginuser", loginUser);
				pathStr = "redirect:index";
			} else {
				msgStr = "请输入正确的用户名或密码。";
				modelMap.put("msg", msgStr);
				pathStr = "login";
			}
		} else {
			modelMap.put("msg", "请输入登录信息。");
			pathStr = "login";
		}
		return pathStr;
	}

	@GetMapping("logout")
	public String logOut(HttpServletRequest req, ModelMap modelMap) {

		HttpSession session = req.getSession();
		session.invalidate();
		modelMap.put("msg", "已退出登录。");
		return "redirect:login";
	}

}
