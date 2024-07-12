package edu.nau.epower_auth.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import edu.nau.epower_auth.dao.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 资源访问的鉴权拦截器
 * 
 * @ClassName: AuthInterceptor
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-08 05:06:45
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("preHandle...鉴权开始");
//		response.sendError(HttpStatus.FORBIDDEN.value(), "权限不足");

		String reqUrl = "";
//		reqUrl = request.getRequestURL().toString();
		reqUrl = request.getRequestURI().toString();
		System.out.println(">>> reqUrl=" + reqUrl);

		String msgStr = "";
		String urlStr = "";
		boolean isPermit = false;

		// 1，检查用户是否登录
		User loginUser = (User) request.getSession().getAttribute("loginuser");
		System.out.println("*** loginUser=" + loginUser);
		if (loginUser == null) {
			msgStr = "登录超时，请重新登录。";
			urlStr = "/login";

			writerPrint(response, msgStr, urlStr);
			return false;
		}

		// 2，检查用户是否有权限

		/*
		 * String url = "/index"; String alert = "没有权限";
		 * response.setContentType("text/html; charset=utf-8"); PrintWriter writer =
		 * response.getWriter(); writer.println("<script language='javascript'>"); //
		 * writer.println("window.location.href = '" + url + "';"); // 直接跳转url
		 * writer.println("history.go(-1)"); // 返回（历史）上一页 writer.println("alert('" +
		 * alert + "');"); writer.println("</script>");
		 */
		System.out.println("preHandle...鉴权结束");

		return true; // 放行
	}

	private void writerPrint(HttpServletResponse response, String alertStr, String urlStr) throws IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script language='javascript'>");
		writer.println("window.location.href = '" + urlStr + "';"); // 直接跳转url
//		writer.println("history.go(-1)"); // 返回（历史）上一页
		writer.println("alert('" + alertStr + "');");
		writer.println("</script>");
	}

}
