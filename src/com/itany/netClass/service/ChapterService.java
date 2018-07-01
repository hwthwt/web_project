package com.itany.netClass.service;

import java.util.Date;
import java.util.List;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.ChapterMessage;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.Resource;

public interface ChapterService {
	/**
	 *	根据课程id查询课程章节详情
	 */
	public List<Chapter> findChaptersByCourseId(Integer id);
	
	/**
	 *  修改章节的状态
	 * @param id
	 * @param status
	 */
	public void modifyStatus(String id,String status);
	
	/**
	 *  插入章节数据,默认为启用
	 *  
	 **/
	public void insertChapter(Integer course_id,String title,String info, String createDate,Integer status);
	
	/**
	 *  插入资源数据,默认为启用
	 *  
	 **/
	public void insertResource(Resource resource);
	
	/** 
	 * 根据章节名称查找章节id,可用于判断名称是否重复和返回id
	 **/
	public Integer selectChapter(String name);
	
	/**
	 * 根据id修改章节标题
	 *  */
	public void updateTitleById(Integer id,String name);
	
	/** 
	 * 模糊查询chapter信息
	 **/
	public List<Chapter> selectByFuzzy(ChapterMessage chapterMessage);
}
