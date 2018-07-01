package com.itany.netClass.service;

import java.util.List;

import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;

public interface SelectFont {
		/**
		 *  查找所有父级元素(方向)
		 * */
	public List<CourseType> selectFirst();
	
	/** 
	 * 查找所有课程 
	 **/
	public List<Course>  selectQuanBu(String searchContext);
	
		/** 
		 * 根据一级名称找三级名称 
		 **/
	public List<CourseType> selectThird(String firstName);
	
	/**
	 *  根据三级名称找全部课程
	 * */
	public List<Course> selectAll(Integer id,String searchContext);
}
