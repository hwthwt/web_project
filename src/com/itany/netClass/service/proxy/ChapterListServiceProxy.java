package com.itany.netClass.service.proxy;

import java.util.List;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Course;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterListService;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.transaction.TransactionManager;

public class ChapterListServiceProxy implements ChapterListService {
	private TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
	private ChapterListService	chapterListService=(ChapterListService) ObjectFactory.getObject("chapterListServiceTarget");
	

	@Override
	public List<Chapter> findChaptersByCourseId(String courseId) {
		tran.beginTransaction();
		List<Chapter> chapters;
		try {
			chapters = chapterListService.findChaptersByCourseId(courseId);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			throw e;
		}
		
		return chapters;
	}


	@Override
	public List<Course> findCourses() {
		tran.beginTransaction();
		List<Course> courses;
			courses = chapterListService.findCourses();
			tran.commit();
			return courses;
	}

}
