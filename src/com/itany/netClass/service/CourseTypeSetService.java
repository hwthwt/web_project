package com.itany.netClass.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeExistsException;
import com.itany.netClass.exception.CourseTypeNameEmptyException;
import com.itany.netClass.exception.CourseTypeNameErrorException;

public interface CourseTypeSetService {

	public PageInfo<CourseType> findAll(int pageNo);

	public void modifyStatus(String id);

	public CourseType findById(String id);

	public void modifyType(String id, String typeName) throws CourseTypeNameEmptyException, CourseTypeNameErrorException, CourseTypeExistsException;

	public void addType(String id, String typeName) throws CourseTypeNameEmptyException, CourseTypeNameErrorException, CourseTypeExistsException;

	public PageInfo<CourseType> findSon(int pageNo2, String parentId, HttpSession session);

	public String findFather(String id,
			HttpSession session);

	public List<CourseType> findSonName();

}