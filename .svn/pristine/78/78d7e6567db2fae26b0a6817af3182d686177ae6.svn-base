package com.itany.netClass.service.impl;

import java.util.Date;
import java.util.List;

import com.itany.netClass.constant.StatusConstant;
import com.itany.netClass.dao.ChapterDao;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.ChapterMessage;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;


public class ChapterServiceImpl implements ChapterService{

	@Override
	public List<Chapter> findChaptersByCourseId(Integer id) {
		ChapterDao chapterDao= (ChapterDao) ObjectFactory.getObject("ChapterDao");
		return chapterDao.selectAllById(id);
	}

	@Override
	public void modifyStatus(String id, String status) {
		ChapterDao chapterDao= (ChapterDao) ObjectFactory.getObject("ChapterDao");
		int typeStatus = Integer.parseInt(status);
		if(typeStatus == StatusConstant.PRODUCT_TYPE_STATUS_DISABLE){
			typeStatus = StatusConstant.PRODUCT_TYPE_STATUS_ENABLE;
		}else{
			typeStatus = StatusConstant.PRODUCT_TYPE_STATUS_DISABLE;
		}
		chapterDao.updateStatus(Integer.parseInt(id),typeStatus);
	}

	@Override
	public void insertChapter(Integer course_id, String title, String info,
			String createDate, Integer status) {
		ChapterDao chapterDao= (ChapterDao) ObjectFactory.getObject("ChapterDao");
		chapterDao.insertChapter(course_id, title, info, createDate, status);
	}

	@Override
	public void insertResource(Resource resource) {
		ChapterDao chapterDao= (ChapterDao) ObjectFactory.getObject("ChapterDao");
		chapterDao.insertResource(resource);
	}

	@Override
	public Integer selectChapter(String name) {
		ChapterDao chapterDao= (ChapterDao) ObjectFactory.getObject("ChapterDao");
		Integer id=chapterDao.selectByName(name);
		return id;
	}

	@Override
	public void updateTitleById(Integer id, String name) {
		ChapterDao chapterDao= (ChapterDao) ObjectFactory.getObject("ChapterDao");
		chapterDao.updateNameById(id, name);
	}

	@Override
	public List<Chapter> selectByFuzzy(ChapterMessage chapterMessage) {
		ChapterDao chapterDao= (ChapterDao) ObjectFactory.getObject("ChapterDao");
		List<Chapter> chapters = chapterDao.selectByFuzzy(chapterMessage);
		return chapters;
	}
}
