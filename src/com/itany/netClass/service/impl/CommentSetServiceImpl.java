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
import com.itany.netClass.dao.CommentDao;
import com.itany.netClass.dao.CommentSetDao;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.service.CommentSetService;
import com.itany.netClass.util.ParameterUtil;

public class CommentSetServiceImpl implements CommentSetService {
	
	private CommentSetDao commentSetDao = (CommentSetDao) ObjectFactory.getObject("commentSetDao");

	@Override
	public List<Comment> findAll(String ReId) {
		int id = Integer.parseInt(ReId);
		List<Comment> list = commentSetDao.selectAll(id);
		return list;
	}

	@Override
	public void update(String id, String status) {
		int Id = Integer.parseInt(id);
		int Status = Integer.parseInt(status);
		commentSetDao.update(Id,Status);
	}

	@Override
	public void searchResource(String rId ,String username, String usercomment,
			String beginTime, String endTime, String option, HttpSession session) throws DateErrorException, ParseException, DateMistakeException {
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
			session.setAttribute("csbeginTime", new SimpleDateFormat(
					"yyyy-MM-dd").parse(beginTime));
		} else {
			session.removeAttribute("csbeginTime");
		}
		if (!ParameterUtil.isNull(endTime)) {
			session.setAttribute("csendTime",
					new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
		} else {
			session.removeAttribute("csendTime");
		}
		session.setAttribute("csrId", Integer.parseInt(rId));
		session.setAttribute("csusername", ParameterUtil.escape(username));
		session.setAttribute("csusercomment", ParameterUtil.escape(usercomment));
		//if(!option.equals("-1")){
			session.setAttribute("csstatus", Integer.parseInt(option));
		//}
	}

	@Override
	public PageInfo<Comment> showSelect(String pageNoStr, HttpSession session) {
		Comment c = new Comment();
		String username = (String) session.getAttribute("csusername");
		if (!ParameterUtil.isNull(username)) {
			User u = new User();
			u.setUsername(username);
			c.setUser(u);
		}
		int rID = (int) session.getAttribute("csrId");
		System.out.println("resource ID="+rID);
			Resource r = new Resource();
			r.setId(rID);
			c.setResource(r);
		String usercomment = (String) session.getAttribute("csusercomment");
		System.out.println("输入的内容"+usercomment);
		if (!ParameterUtil.isNull(usercomment)) {
			c.setContext(usercomment);
		}
		Date beginTime = (Date) session.getAttribute("csbeginTime");
		c.setBeginTime((Date) session.getAttribute("csbeginTime"));
		c.setEndTime((Date) session.getAttribute("csendTime"));
		int status = (Integer) session.getAttribute("csstatus");
		c.setStatus(status);
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		List<Comment> list = new ArrayList<Comment>();
		list = commentSetDao.selectSearch(c);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
		return pageInfo;
	}

}
