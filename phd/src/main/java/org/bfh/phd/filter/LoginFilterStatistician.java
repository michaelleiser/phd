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

public class LoginFilterStatistician implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LoginController lc = (LoginController) ((HttpServletRequest) request).getSession().getAttribute("loginController");
		if ((lc == null) || !lc.getLoggedin()) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/home.xhtml");
		} else if (lc.getStaff().getRole() != 2) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/loggedin.xhtml");
		} else {
			chain.doFilter(request, response);			
		}
	}

	public void init(FilterConfig config) throws ServletException {

	}

	public void destroy() {

	}

}