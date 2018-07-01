package com.itany.netClass.service.proxy;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.exception.TextIsEmptyException;
import com.itany.netClass.exception.TextIsErrorException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.transaction.TransactionManager;

public class CommentServiceProxy implements CommentService {
	
	private TransactionManager tran = (TransactionManager) ObjectFactory
			.getObject("transaction");
	private CommentService commentService = (CommentService) ObjectFactory
			.getObject("commentServiceTarget");


	@Override
	public List<Comment> findAll() {
		tran.beginTransaction();
		List<Comment> list = commentService.findAll();
		tran.commit();
		return list;
	}


	@Override
	public void update(String id, String status) {
		tran.beginTransaction();
		try {
			commentService.update(id, status);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}


	@Override
	public List<Comment> findCommentsByCourseId(List<Integer> list) {
		tran.beginTransaction();
		try {
			List<Comment> comments = commentService.findCommentsByCourseId(list);
			tran.commit();
			return comments;
		} catch (Exception e) {
			tran.rollback();
			throw e;
		}
	}


	@Override
	public List<Comment> findCommentsByResourceId(String resourceId) {
		tran.beginTransaction();
		try {
			List<Comment> comments = commentService.findCommentsByResourceId(resourceId);
			tran.commit();
			return comments;
		} catch (Exception e) {
			tran.rollback();
			throw e;
		}
	}


	@Override
	public void add(String text, String resourceId, Integer uid) throws TextIsEmptyException, TextIsErrorException,Exception {
		tran.beginTransaction();
				try {
					commentService.add(text, resourceId, uid);
					tran.commit();
				} catch (TextIsEmptyException e) {
					tran.rollback();
					throw e;
				} catch (TextIsErrorException e) {
					tran.rollback();
					throw e;
				} catch (Exception e) {
					tran.rollback();
					throw e;
				}
			
	}




	@Override
	public void searchResource(String username, String usercomment,
			String beginTime, String endTime, HttpSession session) throws DateErrorException, DateMistakeException, ParseException {
		tran.beginTransaction();
		try {
			commentService.searchResource(username, usercomment, beginTime, endTime, session);
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
		PageInfo<Comment> pageInfo = commentService.showSelect(
				pageNoStr, session);
		tran.commit();
		return pageInfo;
	}

}
