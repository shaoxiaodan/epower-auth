package edu.nau.epower_auth.common;

import java.io.IOException;
import java.io.PrintWriter;

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

}
