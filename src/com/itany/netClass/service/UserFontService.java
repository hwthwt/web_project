package com.itany.netClass.service;

import java.util.Date;
import java.util.List;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.entity.User;

public interface UserFontService {

	/**
	 *	用户注册
	 */
	public void  register(User user);
	
	/**
	 * 根据用户名查找用户
	 * */
	public User selectByUsername(String username);

	/**
	 * 根据用户名和密码查找用户
	 * */
	public User selectByUsernameandPwd(String username,String password);
	
	/**
	 * 用户登录
	 *  */
	public User  login(String username,String password);
	
	/**
	 *  修改用户的密码,昵称,邮箱
	 * */
	public void modifyUserMsg(String username,String password,String nickname,String email);
	
	/**
	 *  用户签到后给用户增加积分,以及设置操作时间
	 * */
	public void qiandao(String userId,Date nowdate);
	
	/**
	 * 把签到的年月日插入到user的数据库中 
	 **/
	public void insertLoginDate(Integer userid,String date);
	
	/**
	 * 查找导航菜单的内容 
	 **/
	public List<CourseType>  selectTypes();
	
	/**
	 * 根据一级id查找点击量前四的课程 
	 **/
	public List<Course> findFourCourse();
	
	/** 
	 * 按点击量从小到大排序排序课程
	 * */
	public List<Course> selectCourseByClickNumber();
}
