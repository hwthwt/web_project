package com.itany.netClass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.CourseTypeSetDao;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeExistsException;
import com.itany.netClass.exception.CourseTypeNameEmptyException;
import com.itany.netClass.exception.CourseTypeNameErrorException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeSetService;
import com.itany.netClass.util.ParameterUtil;

public class CourseTypeSetServiceImpl implements CourseTypeSetService {

	private CourseTypeSetDao courseTypeSetDao = (CourseTypeSetDao) ObjectFactory
			.getObject("courseTypeSetDao");

	@Override
	public PageInfo<CourseType> findAll(int pageNo) {
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		List<CourseType> list = new ArrayList<CourseType>();
		list = courseTypeSetDao.selectAll();
		PageInfo<CourseType> pageInfo = new PageInfo<CourseType>(list);
		return pageInfo;
	}

	@Override
	public void modifyStatus(String id) {
		int ids = Integer.parseInt(id);
		CourseType ct = courseTypeSetDao.selectById(ids);
		int status;
		if (ct.getStatus() == Constant.STATUS_ENABLE) {
			status = Constant.STATUS_DISABLE;
		} else {
			status = Constant.STATUS_ENABLE;
		}
		if (status == Constant.STATUS_DISABLE) {
			courseTypeSetDao.updateSonStatus(status, ids);
			List<CourseType> cts = ct.getChildren();
			for (CourseType courseType : cts) {
				int ider = courseType.getId();
				courseTypeSetDao.updateSonStatus(status, ider);
			}
		}
		courseTypeSetDao.updateStatus(status, ids);

	}

	@Override
	public CourseType findById(String id) {
		int ids = Integer.parseInt(id);
		CourseType ct = courseTypeSetDao.selectById(ids);
		return ct;
	}

	@Override
	public void modifyType(String id, String typeName)
			throws CourseTypeNameEmptyException, CourseTypeNameErrorException,
			CourseTypeExistsException {
		if (ParameterUtil.isNull(typeName)) {
			throw new CourseTypeNameEmptyException("类别为空");
		}
		String nn = "[0-9a-zA-Z\u4e00-\u9fa5]+";
		if (!typeName.matches(nn)) {
			throw new CourseTypeNameErrorException("类别格式错误");
		}
		int ids = Integer.parseInt(id);
		CourseType ct = courseTypeSetDao.selectBytypeName(typeName, ids);
		if (ct != null) {
			throw new CourseTypeExistsException("类别名字已存在");
		}

		courseTypeSetDao.updateType(typeName, ids);
	}

	@Override
	public void addType(String id, String typeName)
			throws CourseTypeNameEmptyException, CourseTypeNameErrorException,
			CourseTypeExistsException {
		if (ParameterUtil.isNull(typeName)) {
			throw new CourseTypeNameEmptyException("类别为空");
		}
		String nn = "[0-9a-zA-Z\u4e00-\u9fa5]+";
		if (!typeName.matches(nn)) {
			throw new CourseTypeNameErrorException("类别格式错误");
		}
		CourseType ct = courseTypeSetDao.selectByName(typeName);
		if (ct != null) {
			throw new CourseTypeExistsException("类别名字已存在");
		}
		courseTypeSetDao.insertType(typeName, id);
	}

	@Override
	public PageInfo<CourseType> findSon(int pageNo2, String parentId,HttpSession session) {
		PageHelper.startPage(pageNo2, Constant.PAGE_SIZE);
		List<CourseType> list = new ArrayList<CourseType>();
		int parentid = Integer.parseInt(parentId);
		list = courseTypeSetDao.selectfindSon(parentid);
		List<CourseType> list2 = new ArrayList<CourseType>();
		list2=courseTypeSetDao.selectNotNull();
		List<Integer> notNull = new ArrayList<Integer>();
		for (CourseType ct : list2) {
			for (CourseType ct2 : ct.getChildren()) {
				for (CourseType ct3 : ct2.getChildren()) {
					notNull.add(ct3.getId());
				}
			}
		}
		session.setAttribute("notnull", notNull);
		PageInfo<CourseType> pageInfo = new PageInfo<CourseType>(list);
		return pageInfo;
	}

	@Override
	public String findFather(String id,
			HttpSession session) {
		List<CourseType> list = new ArrayList<CourseType>();
		list = courseTypeSetDao.selectfindFather(id);
		if (list.size() > 0) {
			int fatherId = list.get(0).getId();
			String fd = fatherId+"";
			session.setAttribute("pd", fd);
			return "redirect:/courseTypeSet/findSon.do";
		}else{
			session.setAttribute("pd", null);
			return "redirect:/courseTypeSet/findAll.do";
		}
	}

	@Override
	public List<CourseType> findSonName() {
		List<CourseType> list2 = new ArrayList<CourseType>();
		list2=courseTypeSetDao.selectNotNull();
		List<CourseType> notNull = new ArrayList<CourseType>();
		for (CourseType ct : list2) {
			for (CourseType ct2 : ct.getChildren()) {
				for (CourseType ct3 : ct2.getChildren()) {
					if(ct3.getStatus()==0){
						notNull.add(ct3);
					}
				}
			}
		}
		return notNull;
	}

}