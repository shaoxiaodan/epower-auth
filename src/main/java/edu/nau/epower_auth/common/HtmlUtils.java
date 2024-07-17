package edu.nau.epower_auth.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.util.ListUtils;

import edu.nau.epower_auth.dao.Menu;
import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.dao.Url;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;

public class HtmlUtils {

	/*
	 * 渲染前端HTML页面的提示窗口
	 */
	public static void getWebPageAlert(HttpServletResponse response, String msgStr, String urlStr) throws IOException {
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

	/*
	 * 根据当前用户的def role来重新装配角色下的url，并用户前端按钮是否显示。
	 */
	public static List<Url> getUrlListByDefRole(Role defRole) {

		List<Menu> menuList = null;
		List<Url> newUrlList = new ArrayList<Url>();

		if (defRole != null) {

			if (!ListUtils.isEmpty(defRole.getMenuList())) {
				menuList = defRole.getMenuList();
				for (Menu menu : menuList) {
					if (!ListUtils.isEmpty(menu.getUrlList())) {
						newUrlList.addAll(menu.getUrlList());
					}
				}
			}
		}

		return newUrlList;
	}

}
