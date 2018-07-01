package com.itany.netClass.service.proxy;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.CourseSet;
import com.itany.netClass.exception.DataParseException;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.exception.EmptyChooseException;
import com.itany.netClass.exception.NameIsEmptyException;
import com.itany.netClass.exception.NameIsUsedException;
import com.itany.netClass.exception.infoIsEmptyException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseSetService;
import com.itany.netClass.service.UserService;
import com.itany.netClass.transaction.TransactionManager;

public class CourseSetServiceProxy implements CourseSetService {

	private TransactionManager tran = (TransactionManager) ObjectFactory
			.getObject("transaction");
	private CourseSetService courseSetService = (CourseSetService) ObjectFactory
			.getObject("courseSetServiceTarget");

	@Override
	public List<CourseSet> findAll() {
		tran.beginTransaction();
		List<CourseSet> courses = courseSetService.findAll();
		tran.commit();
		return courses;
	}

	@Override
	public void updateStatusById(String id, String status)
			throws DataParseException {
		tran.beginTransaction();
		try {
			courseSetService.updateStatusById(id, status);
			tran.commit();
		} catch (DataParseException e) {
			tran.rollback();
			throw e;
		}
	}

	@Override
	public boolean check(String id, String name, String info, String author,
			String recommendationGrade, String courseTypeId)
			throws NameIsUsedException, EmptyChooseException,
			NameIsEmptyException, infoIsEmptyException, Exception {
		tran.beginTransaction();
		try {
			courseSetService.check(id, name, info, author, recommendationGrade,
					courseTypeId);
			tran.commit();
			return true;
		} catch (NameIsEmptyException e) {
			tran.rollback();
			throw e;
		} catch (infoIsEmptyException e) {
			tran.rollback();
			throw e;
		} catch (NameIsUsedException e) {
			tran.rollback();
			throw e;
		} catch (Exception e) {
			tran.rollback();
			throw e;
		}

	}

	@Override
	public void insert(String name, String info, String author,
			String recommendationGrade, String courseTypeId, String imagePath) {
		tran.beginTransaction();
		try {
			courseSetService.insert(name, info, author, recommendationGrade,
					courseTypeId, imagePath);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			throw e;
		}
	}

	@Override
	public CourseSet findById(String id) {
		tran.beginTransaction();
		CourseSet c = courseSetService.findById(id);
		tran.commit();
		return c;
	}

	@Override
	public void update(String id, String name, String info, String author,
			String recommendationGrade, String courseTypeId, String imagePath) {
		tran.beginTransaction();
		try {
			courseSetService.update(id, name, info, author, recommendationGrade, courseTypeId, imagePath);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			throw e;
		}
	}

	@Override
	public void searchResource(String authorname, String coursename,
			String beginTime, String endTime, String status, String type,
			HttpSession session) throws DateErrorException, DateMistakeException, ParseException {
		tran.beginTransaction();
		try {
			courseSetService.searchResource(authorname, coursename, beginTime, endTime, status, type, session);
			tran.commit();
		} catch (DateErrorException e) {
			tran.rollback();
			throw e;
		} catch (DateMistakeException e) {
			tran.rollback();
			throw e;
		} catch (ParseException e) {
			tran.rollback();
			throw e;
		}
	}

	@Override
	public PageInfo<CourseSet> showSelect(String pageNoStr, HttpSession session) {
		tran.beginTransaction();
		PageInfo<CourseSet> pageInfo = courseSetService.showSelect(
				pageNoStr, session);
		tran.commit();
		return pageInfo;
	}

}
