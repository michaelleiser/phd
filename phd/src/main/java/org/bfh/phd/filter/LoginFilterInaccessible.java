package org.bfh.phd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bfh.phd.LoginController;

/**
 * This filter class let pass no messages.
 * 
 * @author leism3, koblt1
 */
public class LoginFilterInaccessible implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		LoginController lc = (LoginController) req.getSession().getAttribute("loginController");
		if ((lc == null) || !lc.getLoggedin()) {
			String contextPath = req.getContextPath();
			res.sendRedirect(contextPath + "/home.xhtml");
		} else {
			String contextPath = req.getContextPath();
			res.sendRedirect(contextPath + "/restricted/loggedin.xhtml");
		}
	}

	public void init(FilterConfig config) throws ServletException {

	}

	public void destroy() {

	}

}