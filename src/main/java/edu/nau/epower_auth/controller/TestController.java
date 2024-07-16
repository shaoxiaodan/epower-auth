package edu.nau.epower_auth.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.ListUtils;

import com.github.pagehelper.PageHelper;

import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.dao.Test;
import edu.nau.epower_auth.dao.Url;
import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.dao.UserRole;
import edu.nau.epower_auth.service.MenuService;
import edu.nau.epower_auth.service.RoleService;
import edu.nau.epower_auth.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName: TestController
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 02:16:44
 */
@Controller
@RequestMapping("/")
public class TestController {

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	@GetMapping("test")
	public String test(ModelMap modelMap, int pageNum, int pageSize) {

		PageHelper.startPage(pageNum, pageSize);

		String title = "new title";
		modelMap.addAttribute("title", title);

		Test user = new Test();
		user.setId(99);
		user.setUsername("Thymeleaf");
		user.setCreateTime(new Date());
		user.setVip(true);
		user.setTags(Arrays.asList("Java", "MySQL", "Thymeleaf", "Springboot"));
		modelMap.addAttribute("user", user);

		return "test";
	}

	@GetMapping("testlogin")
	public String testLogin(ModelMap modelMap) {

		List<User> userList = userService.listUser(2);
		modelMap.addAttribute("userlist", userList);
		System.out.println("testLogin, userList=" + userList);
		return "testlogin";
	}

	@GetMapping("testurl")
	public String addUrlPage(ModelMap modelMap) {

		Map<Integer, Object> map = new HashMap<Integer, Object>();

		for (int i = 0; i < 10; i++) {
			map.put(i, "map-" + i);
		}
		modelMap.addAttribute("urlobjs", map);

		List<Url> leftList = new ArrayList<Url>();
		for (int i = 1; i <= 5; i++) {
			Url url = new Url();
			url.setId(i);
			url.setPath("left-" + i);
			leftList.add(url);
		}
		modelMap.addAttribute("leftlist", leftList);

		List<Url> rightList = new ArrayList<Url>();
		for (int i = 9; i <= 10; i++) {
			Url url = new Url();
			url.setId(i);
			url.setPath("right-" + i);
			rightList.add(url);
		}
		modelMap.addAttribute("rightlist", rightList);

		List<Url> allUrls = new ArrayList<Url>();
		allUrls.addAll(leftList);
		allUrls.addAll(rightList);
		modelMap.addAttribute("allurls", allUrls);

		return "testurl";
	}

	@PostMapping("testurl2")
	public String addUrlPage2(int[] urlhidden) {

//		System.out.println("addUrlPage2, modelMap=" + modelMap);
//		System.out.println("addUrlPage2, url=" + url);
		System.out.println("addUrlPage2, urlhidden=" + urlhidden);

		return "redirect:testurl";
	}

	@GetMapping("first")
	public String firstPage( @RequestParam(name = "def", defaultValue = "0") int roleId, ModelMap modelMap) throws IOException {

		Role defRole = null;
		UserRole defUserRole = new UserRole();
		String url = "first";
		
		int userId = 2;
		List<Role> roleList = roleService.findRoleByUserId(userId);

		if (!ListUtils.isEmpty(roleList)) {

			if (roleId > 0) {
				for (Role role : roleList) {
					if (role.getId() == roleId) {
						defRole = role;
						defUserRole.setUserId(userId);
						defUserRole.setRoleId(defRole.getId());
						break;
					}
				}
			} else {
				defRole = roleList.get(0);
				defUserRole.setUserId(userId);
				defUserRole.setRoleId(defRole.getId());
			}
			
			
		}
		
		if(defRole == null) {
//			writerPrint(response, "没有该角色", null);
//			url = "first?def=0";
			
//			defUserRole.setUserId(userId);
//			defUserRole.setRoleId(0);
			
			defUserRole.setRoleId(-999);
			
		}
		
		modelMap.addAttribute("defrole", defRole);
		modelMap.addAttribute("defuserrole", defUserRole);
		modelMap.addAttribute("rolelist", roleList);
		
		
//		return "first";
		return url;
	}

	private void writerPrint(HttpServletResponse response, String msgStr, String urlStr) throws IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script language='javascript'>");

		if (StringUtils.isEmpty(urlStr)) {
			writer.println("history.go(-1)"); // 返回（历史）上一页
		} else {
			writer.println("window.location.href = '" + urlStr + "';"); // 直接跳转url
		}
		writer.println("alert('" + msgStr + "');");
		writer.println("</script>");
	}

	@PostMapping("second")
	public String secondPage(ModelMap modelMap, UserRole userRole) {

		UserRole ur1 = (UserRole) modelMap.getAttribute("userrole");
		UserRole ur2 = (UserRole) modelMap.get("userrole");

		System.out.println("second --> ur1=" + ur1);
		System.out.println("second --> ur2=" + ur2);
		System.out.println("second --> userRole=" + userRole);

		modelMap.addAttribute("userrole", userRole);

//		return "first";
		return "redirect:first";
	}

}
