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

		System.out.println("login...");

		modelMap.put("user", new User());
//		modelMap.put("msg", "");

		return "login";
	}

	@PostMapping("loginchk")
	public String loginCheck(HttpServletRequest req, User user, ModelMap modelMap) {

		String userName = user.getUsername();
		String passWord = user.getPassword();
		String pathStr = "";
		String msgStr = "";

		User loginUser = loginService.findUserByUserNameAndPwd(userName, passWord);

		if (loginUser != null) {

			HttpSession session = req.getSession();
			session.setAttribute("user", loginUser);

			pathStr = "redirect:user/list";
		} else {
			msgStr = "用户名或密码错误。";
			modelMap.put("msg", msgStr);
			pathStr = "login";
		}

		return pathStr;
	}

	@GetMapping("logout")
	public String logOut(HttpServletRequest req) {

		HttpSession session = req.getSession();
		session.invalidate();

		return "redirect:login";
	}

}
