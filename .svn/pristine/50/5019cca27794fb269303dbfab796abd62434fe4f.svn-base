package com.itany.netClass.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itany.netClass.entity.User;

public class BackLoginFilter implements Filter {
	private FilterConfig config;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		User user = (User) request.getSession().getAttribute("userBack");
		String path = request.getServletPath();
		String only = config.getInitParameter("only");
		String[] arr = only.split(",");
		if (user == null) {
			for (String pattern : arr) {
				if (pattern.equals(path)) {
					request.getRequestDispatcher("/backLogin.do").forward(request,
							response);
				}
			}

		}
		chain.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;

	}

}
