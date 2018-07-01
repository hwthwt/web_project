package com.itany.netClass.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.RecordDao;
import com.itany.netClass.entity.Record;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.PointEmptyException;
import com.itany.netClass.exception.PointErrorException;
import com.itany.netClass.exception.PointMaxException;
import com.itany.netClass.exception.PointNotNumException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.RecordService;
import com.itany.netClass.util.ParameterUtil;

public class RecordServiceImpl implements RecordService {
	private RecordDao recordDao = (RecordDao) ObjectFactory
			.getObject("recordDao");

	@Override
	public PageInfo<Record> findAll(String pageNoStr, User u) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		List<Record> list = new ArrayList<Record>();
		list = recordDao.selectByUser(u);
		PageInfo<Record> pageInfo = new PageInfo<Record>(list);
		return pageInfo;
	}

	@Override
	public void check(String point, HttpSession session) throws PointEmptyException, PointNotNumException, PointErrorException, PointMaxException {
		if (ParameterUtil.isNull(point)) {
			throw new PointEmptyException("输入的积分为空");
		}
		User u = (User) session.getAttribute("user");
		int up = u.getAllPoint();
		String reg = "[0-9]+";
		if (!point.matches(reg)) {
			throw new PointNotNumException("输入的不是数字");
		}
		int p = Integer.parseInt(point);
		if (p<10) {
			throw new PointErrorException("输入数字至少为10");
		}
		if (p>up) {
			throw new PointMaxException("积分不足");
		}
		int pp = p/10;
		session.setAttribute("repg", pp);
	}

	@Override
	public PageInfo<Record> change(String pageNoStr, HttpSession session) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		int gold = (int) session.getAttribute("repg");
		int point = gold*10;
		User u = (User) session.getAttribute("user");
		int id = u.getId();
		String info1 = "使用了"+point+"积分兑换了金币";
		String info2 = "使用积分兑换了"+gold+"金币";	
		Date date = new Date();
		recordDao.insertPoint(-point,id,info1,date);
		recordDao.insertGolden(gold,id,info2,date);
		int userp = u.getAllPoint();
		int userg = u.getAllGold();
		u.setAllPoint(userp-point);
		u.setAllGold(userg+gold);
		session.setAttribute("user", u);
		List<Record> list = new ArrayList<Record>();
		list = recordDao.selectByUser(u);
		PageInfo<Record> pageInfo = new PageInfo<Record>(list);
		return pageInfo;
	}

	@Override
	public List<Record> findByUserId(User u) {
		List<Record> list = new ArrayList<Record>();
		list = recordDao.selectByUser(u);
		return list;
	}

}
