package com.itany.netClass.controller;

import com.itany.mvc.annotation.Configuration;
import com.itany.mvc.config.ResourceHandlerRegistry;
import com.itany.mvc.config.WebConfigurer;

@Configuration
public class MyConfig extends WebConfigurer {

	@Override
	public void addViewControllers(ResourceHandlerRegistry registry) {
		// 注册mvc-controller  即请求直接指向页面
		// 参数1 viewName
		// 参数2 url
		registry.addViewController("regist", "/showRegist")
				.addViewController("login", "/showLogin")
				.addViewController("home", "/home")
				.addViewController("index", "/index")
				.addViewController("select", "/select")
				.addViewController("backend/back_login", "/backLogin")
				.addViewController("backend/back_Home", "/back_home")
				.addViewController("backend/back_userSet", "/back_userSet")
				.addViewController("backend/back_courseSet", "/backcourseSet")
				.addViewController("backend/back_courseTypeSet", "/backcourseTypeSet")
				.addViewController("backend/back_commentManageSet", "/backcommentManageSet")
				.addViewController("backend/back_commentSet", "/backcommentSet")
				.addViewController("backend/back_resourceSet", "/backresourceSet")
				.addViewController("front_record", "/frontrecord")
				.addViewController("front_course", "/frontcourse")
				.addViewController("front_courseDetail", "/frontcourseDetail")
				.addViewController("front_mycourse", "/frontmycourse");
	}

}
