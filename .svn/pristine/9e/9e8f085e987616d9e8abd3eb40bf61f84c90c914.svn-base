package com.itany.netClass.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.CourseSetDao;
import com.itany.netClass.dao.UserDao;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.CourseSet;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.DataParseException;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.exception.EmptyChooseException;
import com.itany.netClass.exception.NameIsEmptyException;
import com.itany.netClass.exception.NameIsUsedException;
import com.itany.netClass.exception.infoIsEmptyException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseSetService;
import com.itany.netClass.util.ParameterUtil;

public class CourseSetServiceImpl implements CourseSetService {
	
	private CourseSetDao courseSetDao = (CourseSetDao) ObjectFactory.getObject("courseSetDao");

	@Override
	public List<CourseSet> findAll() {
		List<CourseSet> courses = courseSetDao.selectAll();
		return courses;
	}

	@Override
	public void updateStatusById(String id, String status) throws DataParseException {
		int Id = Integer.parseInt(id);
		int Status = Integer.parseInt(status);
		
		courseSetDao.updateStatusById(Id,Status);
	}

	@Override
	public boolean check(String id, String name, String info, String author,
			String recommendationGrade, String courseTypeId) throws NameIsUsedException,EmptyChooseException,infoIsEmptyException,NameIsEmptyException,Exception {
		if(ParameterUtil.isNull(id)){
			if(ParameterUtil.isNull(name)){
				throw new NameIsEmptyException("名字不能为空");
			}
			if(ParameterUtil.isNull(info)){
				throw new infoIsEmptyException("信息不能为空");
			}
			if(ParameterUtil.isNull(author)){
				throw new NameIsEmptyException("作者名字不能为空");
			}
				try {
					int grade = Integer.parseInt(recommendationGrade);
					int type = Integer.parseInt(courseTypeId);
					if(grade==-1){
						throw new EmptyChooseException("推荐等级必须选择");
					}
					if(type==-1){
						throw new EmptyChooseException("课程类别必须选择");
					}
					List<CourseSet> courses = courseSetDao.selectByNameAndAuthor(name,author);
					if(!courses.isEmpty()){
						throw new NameIsUsedException("该名字已有作者使用");
					}
					return true;
				} catch (Exception e) {
					throw e;
				}
		}
		if(ParameterUtil.isNull(name)){
			throw new NameIsEmptyException("名字不能为空");
		}
		if(ParameterUtil.isNull(info)){
			throw new infoIsEmptyException("信息不能为空");
		}
		if(ParameterUtil.isNull(author)){
			throw new NameIsEmptyException("作者名字不能为空");
		}
			try {
				int Id = Integer.parseInt(id);
				int grade = Integer.parseInt(recommendationGrade);
				int type = Integer.parseInt(courseTypeId);
				if(grade==-1){
					throw new EmptyChooseException("推荐等级必须选择");
				}
				if(type==-1){
					throw new EmptyChooseException("课程类别必须选择");
				}
				List<CourseSet> courses = courseSetDao.selectByIdAndAuthor(Id,author);
				if(courses.isEmpty()){
					List<CourseSet> courses1 = courseSetDao.selectByNameAndAuthor(name,author);
					if(!courses1.isEmpty()){
						throw new NameIsUsedException("该名字已有作者使用");
					}
				}
				List<CourseSet> courses2 = courseSetDao.selectByName(name);
				if(!courses2.isEmpty()){
					if(courses2.get(0).getId()!=Id){
						throw new NameIsUsedException("该名字已有作者使用");
					}
				}
				return true;
			} catch (Exception e) {
				throw e;
			}
	}

	@Override
	public void insert(String name, String info, String author,
			String recommendationGrade, String courseTypeId, String imagePath) {
		int grade = Integer.parseInt(recommendationGrade);
		int type = Integer.parseInt(courseTypeId);
		courseSetDao.insert(name,info,author,imagePath,grade,type);
	}

	@Override
	public CourseSet findById(String id) {
		CourseSet c = courseSetDao.selectById(Integer.parseInt(id));
		return c;
	}

	@Override
	public void update(String id, String name, String info, String author,
			String recommendationGrade, String courseTypeId, String imagePath) {
		int Id = Integer.parseInt(id);
		int grade = Integer.parseInt(recommendationGrade);
		int type = Integer.parseInt(courseTypeId);
		courseSetDao.update(Id, name, info, author, grade, type, imagePath);
	}

	@Override
	public void searchResource(String authorname, String coursename,
			String beginTime, String endTime, String status, String type,
			HttpSession session) throws DateErrorException, ParseException, DateMistakeException {
		String date = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		System.out.println("开始日期："+beginTime+"结束日期："+endTime);
		if (!ParameterUtil.isNull(beginTime) && !beginTime.matches(date)) {
			throw new DateErrorException("日期格式有误");
		}
		if (!ParameterUtil.isNull(endTime) && !endTime.matches(date)) {
			throw new DateErrorException("日期格式有误");
		}
		if (!ParameterUtil.isNull(beginTime)
				&& !ParameterUtil.isNull(endTime)
				&& new SimpleDateFormat("yyyy-MM-dd").parse(beginTime)
						.getTime() > new SimpleDateFormat("yyyy-MM-dd").parse(
						endTime).getTime()) {
			throw new DateMistakeException("开始结束日期错误");
		}
		if (!ParameterUtil.isNull(beginTime)) {
			session.setAttribute("crsbeginTime", new SimpleDateFormat(
					"yyyy-MM-dd").parse(beginTime));
		} else {
			session.removeAttribute("crsbeginTime");
		}
		if (!ParameterUtil.isNull(endTime)) {
			session.setAttribute("crsendTime",
					new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
		} else {
			session.removeAttribute("crsendTime");
		}
		session.setAttribute("crsauthorname", ParameterUtil.escape(authorname));
		session.setAttribute("crscoursename", ParameterUtil.escape(coursename));
		session.setAttribute("crsstatus", Integer.parseInt(status));
		session.setAttribute("crstype", Integer.parseInt(type));
	}

	@Override
	public PageInfo<CourseSet> showSelect(String pageNoStr, HttpSession session) {
		CourseSet c = new CourseSet();
		String authorname = (String) session.getAttribute("crsauthorname");
		if (!ParameterUtil.isNull(authorname)) {
			c.setAuthor(authorname);
		}
		String coursename = (String) session.getAttribute("crscoursename");
		if (!ParameterUtil.isNull(coursename)) {
			c.setCourse_name(coursename);
		}
		Date beginTime = (Date) session.getAttribute("crsbeginTime");
		c.setBeginTime((Date) session.getAttribute("crsbeginTime"));
		c.setEndTime((Date) session.getAttribute("crsendTime"));
		int status = (Integer) session.getAttribute("crsstatus");
		c.setStatus(status);
		int type = (Integer) session.getAttribute("crstype");
		if(type!=-1){
		CourseType t = new CourseType(); 
		t.setId(type);
		c.setCourseType(t);
		}
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		List<CourseSet> list = new ArrayList<CourseSet>();
		list = courseSetDao.selectSearch(c);
		PageInfo<CourseSet> pageInfo = new PageInfo<CourseSet>(list);
		return pageInfo;
	}

}
