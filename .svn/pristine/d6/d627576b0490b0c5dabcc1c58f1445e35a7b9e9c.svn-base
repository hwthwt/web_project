package com.itany.netClass.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.CommentDao;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.exception.TextIsEmptyException;
import com.itany.netClass.exception.TextIsErrorException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.util.ParameterUtil;

public class CommentServiceImpl implements CommentService {
	
	private CommentDao commentDao = (CommentDao) ObjectFactory.getObject("commentDao");

	@Override
	public List<Comment> findAll() {
		List<Comment> list = commentDao.selectAll();
		return list;
	}

	@Override
	public void update(String id, String status) {
		int Id = Integer.parseInt(id);
		int Status = Integer.parseInt(status);
		commentDao.update(Id,Status);
	}

	@Override
	public List<Comment> findCommentsByCourseId(List<Integer> list) {
		try {
			List<Comment> comments = commentDao.selectCommentsByCourseId(list);
			return comments;
		} catch (NumberFormatException e) {
			throw e;
		}
	}

	@Override
	public List<Comment> findCommentsByResourceId(String resourceId) {
		try {
			int Id = Integer.parseInt(resourceId);
			List<Comment> comments = commentDao.selectCommentsByResourceId(Id);
			return comments;
		} catch (NumberFormatException e) {
			throw e;
		}
	}

	@Override
	public void add(String text, String resourceId, Integer uid) throws TextIsEmptyException, TextIsErrorException {
		try {
			int Id = Integer.parseInt(resourceId);
			if(text==null||text==""){
				throw new TextIsEmptyException("评论不能为空");
			}
			String reg = "[0-9a-zA-Z\u4e00-\u9fa5.,。?“”.,?]+";
			if(!text.matches(reg)){
				throw new TextIsErrorException("评论格式不正确");
			}
			commentDao.add(text,Id,uid);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void searchResource(String username, String usercomment,
			String beginTime, String endTime, HttpSession session) throws ParseException, DateErrorException, DateMistakeException {
		String date = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
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
			session.setAttribute("cbeginTime", new SimpleDateFormat(
					"yyyy-MM-dd").parse(beginTime));
		} else {
			session.removeAttribute("cbeginTime");
		}
		if (!ParameterUtil.isNull(endTime)) {
			session.setAttribute("cendTime",
					new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
		} else {
			session.removeAttribute("cendTime");
		}
		session.setAttribute("cusername", ParameterUtil.escape(username));
		session.setAttribute("cusercomment", ParameterUtil.escape(usercomment));
	}

	@Override
	public PageInfo<Comment> showSelect(String pageNoStr, HttpSession session) {
		Comment c = new Comment();
		String username = (String) session.getAttribute("cusername");
		if (!ParameterUtil.isNull(username)) {
			User u = new User();
			u.setUsername(username);
			c.setUser(u);
		}
		String usercomment = (String) session.getAttribute("cusercomment");
		System.out.println("输入的内容"+usercomment);
		if (!ParameterUtil.isNull(usercomment)) {
			c.setContext(usercomment);
		}
		Date beginTime = (Date) session.getAttribute("cbeginTime");
		c.setBeginTime((Date) session.getAttribute("cbeginTime"));
		c.setEndTime((Date) session.getAttribute("cendTime"));
		String status = (String) session.getAttribute("rsstatus");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		List<Comment> list = new ArrayList<Comment>();
		list = commentDao.selectSearch(c);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
		return pageInfo;
	}



}
