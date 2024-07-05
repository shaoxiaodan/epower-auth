package edu.nau.epower_auth.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/role/*", filterName = "roleFilter")
public class RoleFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		Filter.super.init(filterConfig);
		System.out.println("filter init...");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("filter doFilter...");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String name = req.getParameter("name");
		System.out.println("** filter::name=" + name);
		System.out.println("** filter::URL=" + req.getRequestURL());
		
		if("aaa".equals(name)) {
			chain.doFilter(request, response);
		}else {
			resp.sendRedirect("/login.html");
			return;
		}
	}

	@Override
	public void destroy() {
//		Filter.super.destroy();
		System.out.println("filter destroy...");
	}

}
