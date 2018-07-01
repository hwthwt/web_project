package com.itany.netClass.service;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;

public interface ResourceService {
	/**
	 *	根据课程chapter_id修改资源详情
	 */
	public void updateResourceByChapterId(Resource resource);
	
	
	public PageInfo<Resource> findAll(int pageNo,HttpSession session);

	public  void modifyStatus(String id);

	public void searchResource(String title, String nickname, String beginTime,
			String endTime, String status, String filetype,HttpSession session) throws DateErrorException, DateMistakeException, ParseException;

	public PageInfo<Resource> showSelect(String pageNoStr, HttpSession session);


	public Resource selectById(String resourceId);

	
}
