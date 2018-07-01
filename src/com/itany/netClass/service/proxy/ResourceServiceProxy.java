package com.itany.netClass.service.proxy;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.CourseSet;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseSetService;
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.transaction.TransactionManager;

public class ResourceServiceProxy implements ResourceService{

	private TransactionManager tran = (TransactionManager) ObjectFactory
			.getObject("transaction");
	private ResourceService resourceService = (ResourceService) ObjectFactory
			.getObject("resourceServiceTarget");
	
	@Override
	public void updateResourceByChapterId(Resource resource) {
		tran.beginTransaction();
		resourceService.updateResourceByChapterId(resource);
		tran.commit();
	}
	
	@Override
	public PageInfo<Resource> findAll(int pageNo, HttpSession session) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		resourceService = (ResourceService) ObjectFactory
				.getObject("resourceServiceTarget");
		tran.beginTransaction();
		PageInfo<Resource> pageInfo = resourceService.findAll(pageNo,
				session);
		tran.commit();
		return pageInfo;
	}

	@Override
	public void modifyStatus(String id) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		resourceService = (ResourceService) ObjectFactory
				.getObject("resourceServiceTarget");
		tran.beginTransaction();
		resourceService.modifyStatus(id);
		tran.commit();

	}

	@Override
	public void searchResource(String title, String nickname, String beginTime,
			String endTime, String status, String filetype, HttpSession session)
			throws DateErrorException, DateMistakeException, ParseException {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		resourceService = (ResourceService) ObjectFactory
				.getObject("resourceServiceTarget");
		tran.beginTransaction();
		try {
			resourceService.searchResource(title, nickname, beginTime,
					endTime, status, filetype, session);
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
	public PageInfo<Resource> showSelect(String pageNoStr, HttpSession session) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		resourceService = (ResourceService) ObjectFactory
				.getObject("resourceServiceTarget");
		tran.beginTransaction();
		PageInfo<Resource> pageInfo = resourceService.showSelect(
				pageNoStr, session);
		tran.commit();
		return pageInfo;
	}

	@Override
	public Resource selectById(String resourceId) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		resourceService = (ResourceService) ObjectFactory
				.getObject("resourceServiceTarget");
		tran.beginTransaction();
		try {
			Resource r = resourceService.selectById(resourceId);
			tran.commit();
			return r;
		} catch (Exception e) {
			tran.rollback();
			throw e;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
