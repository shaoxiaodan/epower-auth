package edu.nau.epower_auth.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.ListUtils;

import edu.nau.epower_auth.dao.Url;
import edu.nau.epower_auth.dao.User;
import io.micrometer.common.util.StringUtils;
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
		System.out.println(" >>> uri = " + request.getRequestURI());

		String msgStr = "";
		String urlStr = "";
		boolean isPermitted = false;

		// 1，读取用户登录信息 & 检查用户是否登录
		User loginUser = (User) request.getSession().getAttribute("loginuser");
		if (loginUser == null) {
			msgStr = "登录超时，请重新登录。";
			urlStr = "/login";
			writerPrint(response, msgStr, urlStr);
			return false;
		}

		// 2，放行index
		String currUri = "";
		currUri = request.getRequestURI();
		urlStr = "/index";
		if (currUri.equals(urlStr)) {
			System.out.println("index 放行...");
			return true;
		}

		// 3，读取用户可以访问的URL & 是否有URL访问权限
		List<Url> userRoleList = (List<Url>) request.getSession().getAttribute("userurls");
		if (ListUtils.isEmpty(userRoleList)) {
			msgStr = "当前角色无任何可无执行权限，请与管理员确认。";
			writerPrint(response, msgStr, null);
			return false;
		} else {
			String reqUri = request.getRequestURI().toString();
			for (Url url : userRoleList) {
				if (reqUri.equals(url.getPath())) {
					isPermitted = true;
					break;
				}
			}

			// 4，判断用户操作权限
			if (!isPermitted) {
				msgStr = "当前用户无[" + reqUri + "]执行权限。";
				writerPrint(response, msgStr, null);
				return false;
			}
		}

		System.out.println("preHandle...鉴权结束");
		return true; // 默认放行
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

}
