package com.itany.netClass.service.proxy;

import java.util.Date;
import java.util.List;

import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserFontService;
import com.itany.netClass.transaction.TransactionManager;

public class UserFontServiceProxy implements UserFontService{
	private TransactionManager tran;
	private UserFontService	userFontService;
	@Override
	public void register(User user) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		userFontService=(UserFontService) ObjectFactory.getObject("userFontServiceTarget");
		tran.beginTransaction();
		userFontService.register(user);
		tran.commit();
	}
	@Override
	public User selectByUsername(String username) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		userFontService=(UserFontService) ObjectFactory.getObject("userFontServiceTarget");
		tran.beginTransaction();
		User user=userFontService.selectByUsername(username);
		tran.commit();
		return user;
	}
	@Override
	public User login(String username, String password) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		userFontService=(UserFontService) ObjectFactory.getObject("userFontServiceTarget");
		tran.beginTransaction();
		User user=userFontService.login(username, password);
		tran.commit();
		return user;
	}
	@Override
	public User selectByUsernameandPwd(String username, String password) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		userFontService=(UserFontService) ObjectFactory.getObject("userFontServiceTarget");
		tran.beginTransaction();
		User user=userFontService.selectByUsernameandPwd(username, password);
		tran.commit();
		return user;
	}
	@Override
	public void modifyUserMsg(String username, String password,
			String nickname, String email) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		userFontService=(UserFontService) ObjectFactory.getObject("userFontServiceTarget");
		tran.beginTransaction();
		userFontService.modifyUserMsg(username, password, nickname, email);
		tran.commit();
	}
	@Override
	public void qiandao(String userId, Date functiondate) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		userFontService=(UserFontService) ObjectFactory.getObject("userFontServiceTarget");
		tran.beginTransaction();
		userFontService.qiandao(userId,functiondate);
		tran.commit();
		
	}
	@Override
	public void insertLoginDate(Integer userid, String date) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		userFontService=(UserFontService) ObjectFactory.getObject("userFontServiceTarget");
		tran.beginTransaction();
		userFontService.insertLoginDate(userid, date);
		tran.commit();
	}
	@Override
	public List<CourseType> selectTypes() {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		userFontService=(UserFontService) ObjectFactory.getObject("userFontServiceTarget");
		tran.beginTransaction();
		List<CourseType> courseType=userFontService.selectTypes();
		tran.commit();
		return courseType;
	}
	@Override
	public List<Course> findFourCourse() {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		userFontService=(UserFontService) ObjectFactory.getObject("userFontServiceTarget");
		tran.beginTransaction();
		List<Course> courses=userFontService.findFourCourse();
		tran.commit();
		return courses;
	}
	@Override
	public List<Course> selectCourseByClickNumber() {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		userFontService=(UserFontService) ObjectFactory.getObject("userFontServiceTarget");
		tran.beginTransaction();
		List<Course> courses=userFontService.selectCourseByClickNumber();
		tran.commit();
		return courses;
	}
}
