package com.itany.netClass.service.proxy;

import java.util.Date;
import java.util.List;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.ChapterMessage;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.ChapterNotFoundException;
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.service.UserService;
import com.itany.netClass.transaction.TransactionManager;

public class ChapterServiceProxy implements ChapterService {
	private TransactionManager tran;
	private ChapterService	chapterService;
	
	@Override
	public List<Chapter> findChaptersByCourseId(Integer id) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		chapterService=(ChapterService) ObjectFactory.getObject("ChapterServiceTarget");
			tran.beginTransaction();
			List<Chapter>  chapters=chapterService.findChaptersByCourseId(id);
			tran.commit();
			return chapters;
	}

	@Override
	public void modifyStatus(String id, String status) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		chapterService=(ChapterService) ObjectFactory.getObject("ChapterServiceTarget");
		tran.beginTransaction();
		chapterService.modifyStatus(id, status);
		tran.commit();
	}

	@Override
	public void insertChapter(Integer course_id, String title, String info,
			String createDate, Integer status) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		chapterService=(ChapterService) ObjectFactory.getObject("ChapterServiceTarget");
		tran.beginTransaction();
		chapterService.insertChapter(course_id, title, info, createDate, status);
		tran.commit();
	}

	@Override
	public void insertResource(Resource resource) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		chapterService=(ChapterService) ObjectFactory.getObject("ChapterServiceTarget");
		tran.beginTransaction();
		chapterService.insertResource(resource);
		tran.commit();
		
	}

	@Override
	public Integer selectChapter(String name) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		chapterService=(ChapterService) ObjectFactory.getObject("ChapterServiceTarget");
		tran.beginTransaction();
		Integer id=chapterService.selectChapter(name);
		tran.commit();
		return id;
	}

	@Override
	public void updateTitleById(Integer id, String name) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		chapterService=(ChapterService) ObjectFactory.getObject("ChapterServiceTarget");
		tran.beginTransaction();
		chapterService.updateTitleById(id, name);
		tran.commit();
	}

	@Override
	public List<Chapter> selectByFuzzy(ChapterMessage chapterMessage) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		chapterService=(ChapterService) ObjectFactory.getObject("ChapterServiceTarget");
		tran.beginTransaction();
		List<Chapter> chapters=chapterService.selectByFuzzy(chapterMessage);
		tran.commit();
		return chapters;
	}
}
