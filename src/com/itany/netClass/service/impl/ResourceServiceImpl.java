package com.itany.netClass.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.ChapterDao;
import com.itany.netClass.dao.ResourceDao;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.util.ParameterUtil;


public class ResourceServiceImpl  implements ResourceService{

	@Override
	public void updateResourceByChapterId(Resource resource) {
		ResourceDao resourceDao= (ResourceDao) ObjectFactory.getObject("resourceDao");
		resourceDao.updateResource(resource);
	}
	
	private ResourceDao resourceDao = (ResourceDao) ObjectFactory
			.getObject("resourceDao");

	@Override
	public PageInfo<Resource> findAll(int pageNo, HttpSession session) {
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		List<Resource> list = new ArrayList<Resource>();
		list = resourceDao.selectAll2();
		Set<String> fileType = new HashSet<String>();
		if (list.size() > 0) {
			for (Resource rs : list) {
				String ft = rs.getFileType();
				if (fileType.size() <= 0) {
					fileType.add(ft);
				}
				for (String string : fileType) {
					if (!string.equals(ft)) {
						fileType.add(ft);
					}
				}
			}
		}
		session.setAttribute("ft", fileType);
		PageInfo<Resource> pageInfo = new PageInfo<Resource>(list);
		return pageInfo;

	}

	@Override
	public void modifyStatus(String id) {
		int ids = Integer.parseInt(id);
		Resource rs = resourceDao.selectById(ids);
		int status;
		if (rs.getStatus() == Constant.STATUS_ENABLE) {
			status = Constant.STATUS_DISABLE;
		} else {
			status = Constant.STATUS_ENABLE;
		}
		resourceDao.updateStatus(status, ids);
	}

	@Override
	public void searchResource(String title, String nickname, String beginTime,
			String endTime, String status, String filetype, HttpSession session)
			throws DateErrorException, DateMistakeException, ParseException {
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
			session.setAttribute("rsbeginTime", new SimpleDateFormat(
					"yyyy-MM-dd").parse(beginTime));
		} else {
			session.removeAttribute("rsbeginTime");
		}
		if (!ParameterUtil.isNull(endTime)) {
			session.setAttribute("rsendTime",
					new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
		} else {
			session.removeAttribute("rsendTime");
		}
		session.setAttribute("rstitle", ParameterUtil.escape(title));
		session.setAttribute("rsnickname", ParameterUtil.escape(nickname));
		session.setAttribute("rsfiletype", filetype);
		session.setAttribute("rsstatus", status);
	}

	@Override
	public PageInfo<Resource> showSelect(String pageNoStr, HttpSession session) {
		Resource rs = new Resource();
		String title = (String) session.getAttribute("rstitle");
		if (!ParameterUtil.isNull(title)) {
			rs.setTitle(title);
		}
		String nickname = (String) session.getAttribute("rsnickname");
		if (!ParameterUtil.isNull(nickname)) {
			rs.setTitle(nickname);
		}
		Date beginTime = (Date) session.getAttribute("rsbeginTime");
		rs.setBeginTime((Date) session.getAttribute("rsbeginTime"));
		rs.setEndTime((Date) session.getAttribute("rsendTime"));
		String status = (String) session.getAttribute("rsstatus");
		if (status!=null&&!"-1".equals(status)) {
			rs.setStatus(Integer.parseInt((String) status));
		}
		String filetype = (String) session.getAttribute("rsfiletype");
		if (!ParameterUtil.isNull(filetype)) {
			rs.setFileType(filetype);
		}
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		List<Resource> list = new ArrayList<Resource>();
		System.out.println(rs.getStatus()+"status");
		list = resourceDao.selectSearch(rs);
		PageInfo<Resource> pageInfo = new PageInfo<Resource>(list);
		return pageInfo;
	}

	@Override
	public Resource selectById(String resourceId) {
		try {
			int id = Integer.parseInt(resourceId);
			Resource r = resourceDao.selectById(id);
			return r;
		} catch (NumberFormatException e) {
			throw e;
		}
	}

}
