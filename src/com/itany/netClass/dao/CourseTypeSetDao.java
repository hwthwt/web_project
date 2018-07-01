package com.itany.netClass.dao;

import java.util.List;

import com.itany.netClass.entity.CourseType;

public interface CourseTypeSetDao {

	public List<CourseType> selectAll();

	public CourseType selectById(int id);

	public void updateStatus(int status,int id);

	public CourseType selectBytypeName(String typeName,int id);

	public void updateType(String typeName, int ids);

	public CourseType selectByName(String typeName);

	public void insertType(String typeName, String id);

	public List<CourseType> selectfindSon(int parentid);

	public void updateSonStatus(int status, int ids);

	public List<CourseType> selectfindFather(String id);

	public List<CourseType> selectNotNull();

}