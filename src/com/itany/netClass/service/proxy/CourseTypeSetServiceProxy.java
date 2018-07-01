package com.itany.netClass.service.proxy;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeExistsException;
import com.itany.netClass.exception.CourseTypeNameEmptyException;
import com.itany.netClass.exception.CourseTypeNameErrorException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeSetService;
import com.itany.netClass.transaction.TransactionManager;

public class CourseTypeSetServiceProxy implements CourseTypeSetService {
	private TransactionManager tran;
	private CourseTypeSetService courseTypeSetServiceTarget;

	@Override
	public PageInfo<CourseType> findAll(int pageNo) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		courseTypeSetServiceTarget = (CourseTypeSetService) ObjectFactory
				.getObject("courseTypeSetServiceTarget");
		tran.beginTransaction();
		PageInfo<CourseType> pageInfo = courseTypeSetServiceTarget
				.findAll(pageNo);
		tran.commit();
		return pageInfo;
	}

	@Override
	public void modifyStatus(String id) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		courseTypeSetServiceTarget = (CourseTypeSetService) ObjectFactory
				.getObject("courseTypeSetServiceTarget");
		tran.beginTransaction();
		courseTypeSetServiceTarget.modifyStatus(id);
		tran.commit();
	}

	@Override
	public CourseType findById(String id) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		courseTypeSetServiceTarget = (CourseTypeSetService) ObjectFactory
				.getObject("courseTypeSetServiceTarget");
		tran.beginTransaction();
		CourseType ct = courseTypeSetServiceTarget.findById(id);
		tran.commit();
		return ct;
	}

	@Override
	public void modifyType(String id, String typeName)
			throws CourseTypeNameEmptyException, CourseTypeNameErrorException,
			CourseTypeExistsException {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		courseTypeSetServiceTarget = (CourseTypeSetService) ObjectFactory
				.getObject("courseTypeSetServiceTarget");
		tran.beginTransaction();
		try {
			courseTypeSetServiceTarget.modifyType(id, typeName);
			tran.commit();
		} catch (CourseTypeNameEmptyException e) {
			tran.rollback();
			throw e;
		} catch (CourseTypeNameErrorException e) {
			tran.rollback();
			throw e;
		} catch (CourseTypeExistsException e) {
			tran.rollback();
			throw e;
		}
	}

	@Override
	public void addType(String id, String typeName)
			throws CourseTypeNameEmptyException, CourseTypeNameErrorException,
			CourseTypeExistsException {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		courseTypeSetServiceTarget = (CourseTypeSetService) ObjectFactory
				.getObject("courseTypeSetServiceTarget");
		tran.beginTransaction();
		try {
			courseTypeSetServiceTarget.addType(id, typeName);
			tran.commit();
		} catch (CourseTypeNameEmptyException e) {
			tran.rollback();
			throw e;
		} catch (CourseTypeNameErrorException e) {
			tran.rollback();
			throw e;
		} catch (CourseTypeExistsException e) {
			tran.rollback();
			throw e;
		}
	}

	@Override
	public PageInfo<CourseType> findSon(int pageNo2, String parentId,HttpSession session) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		courseTypeSetServiceTarget = (CourseTypeSetService) ObjectFactory
				.getObject("courseTypeSetServiceTarget");
		tran.beginTransaction();
		PageInfo<CourseType> pageInfo = courseTypeSetServiceTarget.findSon(
				pageNo2, parentId,session);
		tran.commit();
		return pageInfo;
	}

	@Override
	public String findFather( String id,
			HttpSession session) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		courseTypeSetServiceTarget = (CourseTypeSetService) ObjectFactory
				.getObject("courseTypeSetServiceTarget");
		tran.beginTransaction();
		String re= courseTypeSetServiceTarget.findFather(
				 id, session);
		tran.commit();
		return re;

	}

	@Override
	public List<CourseType> findSonName() {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		courseTypeSetServiceTarget = (CourseTypeSetService) ObjectFactory
				.getObject("courseTypeSetServiceTarget");
		tran.beginTransaction();
		List<CourseType> list = courseTypeSetServiceTarget.findSonName();
		tran.commit();
		return  list;
	}

}