package com.itany.netClass.service.proxy;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.service.CommentSetService;
import com.itany.netClass.transaction.TransactionManager;

public class CommentSetServiceProxy implements CommentSetService {
	
	private TransactionManager tran = (TransactionManager) ObjectFactory
			.getObject("transaction");
	private CommentSetService commentSetService = (CommentSetService) ObjectFactory
			.getObject("commentSetServiceTarget");


	@Override
	public List<Comment> findAll(String reId) {
		tran.beginTransaction();
		List<Comment> list = commentSetService.findAll(reId);
		tran.commit();
		return list;
	}


	@Override
	public void update(String id, String status) {
		tran.beginTransaction();
		try {
			commentSetService.update(id, status);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}


	@Override
	public void searchResource(String rId , String username, String usercomment,
			String beginTime, String endTime, String option, HttpSession session) throws DateErrorException, DateMistakeException, ParseException {
		tran.beginTransaction();
		try {
			commentSetService.searchResource(rId ,username, usercomment, beginTime,endTime, option , session);
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
	public PageInfo<Comment> showSelect(String pageNoStr, HttpSession session) {
		tran.beginTransaction();
		PageInfo<Comment> pageInfo = commentSetService.showSelect(
				pageNoStr, session);
		tran.commit();
		return pageInfo;
	}



}
