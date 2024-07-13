package edu.nau.epower_auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.ListUtils;

import edu.nau.epower_auth.common.ConstantUtils;
import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.service.RoleService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private RoleService roleService;

	@GetMapping("index")
	public String indexPage(HttpServletRequest req, @RequestParam(name = "def", defaultValue = "0") int roleId,
			ModelMap modelMap) {

		Role defRole = null;
//		UserRole defUserRole = new UserRole();

		// 读取session中信息
		User loginUser = this.getLoginUserSession(req);
		List<Role> roleList = this.getRoleListSession(req);

		if (!ListUtils.isEmpty(roleList)) {

			if (roleId > 0) {
				for (Role role : roleList) {
					if (role.getId() == roleId) {
						defRole = role;
//						defUserRole.setUserId(loginUser.getId());
//						defUserRole.setRoleId(defRole.getId());
						break;
					}
				}
			} else {
				defRole = roleList.get(0);
//				defUserRole.setUserId(loginUser.getId());
//				defUserRole.setRoleId(defRole.getId());
			}
		}

		// 通过人工非法输入def，试图返回不存在的role
		if (defRole == null) {
//			defUserRole.setUserId(loginUser.getId());
//			defUserRole.setRoleId(-999); // 设置一个非法数值，并传递到前端做处理

			defRole = new Role();
			defRole.setId(-999);
		}

		// 更新defrole的session
		this.setDefRoleSession(req, defRole);

//		modelMap.addAttribute("defrole", defRole);
//		modelMap.addAttribute("defuserrole", defUserRole);
//		modelMap.addAttribute("rolelist", roleList);
//		modelMap.addAttribute("defuserrole", userRole);

		return "system/index";
	}

	private User getLoginUserSession(HttpServletRequest req) {
		return (User) req.getSession().getAttribute(ConstantUtils.SESSION_LOGIN_USER);
	}

	private List<Role> getRoleListSession(HttpServletRequest req) {
		return (List<Role>) req.getSession().getAttribute(ConstantUtils.SESSION_USER_ROLES);
	}

	private void setDefRoleSession(HttpServletRequest req, Role defRole) {
		req.getSession().setAttribute(ConstantUtils.SESSION_DEF_ROLE, defRole);
	}

}
