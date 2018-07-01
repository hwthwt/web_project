package com.itany.netClass.filters;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.itany.netClass.util.CommonUtil;

@WebListener
public class ApplicationLisenter implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent context) {
		CommonUtil.setContext(null);
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		ServletContext application = context.getServletContext();
		CommonUtil.setContext(application);
		String contextPath = CommonUtil.getContextPath();
		application.setAttribute("fileDir", contextPath);
		System.out.println("getContextPath=" + contextPath);
	}

}
