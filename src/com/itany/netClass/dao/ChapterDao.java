package com.itany.netClass.dao;

import java.util.Date;
import java.util.List;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.ChapterMessage;
import com.itany.netClass.entity.Resource;

public interface ChapterDao {
	public List<Chapter> selectAllById(Integer id);
	
	/**
	 * 根据id修改状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(Integer id,Integer status);
	


	/**
	 * 插入resource信息 
	 * 默认为启用
	 **/
	public void insertResource(Resource resource);
	
	public Integer selectByName(String name);
	/**
	 * 插入chapter信息 
	 * 默认为启用
	 **/
	public void insertChapter(Integer course_id, String title, String info,
			String createDate, Integer status);

	public void updateNameById(Integer id,String name);
	
	/** 
	 *  模糊查询  
	 **/
	public List<Chapter> selectByFuzzy(ChapterMessage chapterMessage);
	
}
