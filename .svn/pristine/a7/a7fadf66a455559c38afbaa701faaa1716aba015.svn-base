package com.itany.netClass.service.impl;

import java.util.List;

import com.itany.netClass.dao.ChapterDao;
import com.itany.netClass.dao.ChapterListDao;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Course;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterListService;

public class ChapterListServiceImpl implements ChapterListService {
	ChapterListDao chapterListDao= (ChapterListDao) ObjectFactory.getObject("chapterListDao");
	@Override
	public List<Chapter> findChaptersByCourseId(String courseId) {
		List<Chapter> chapters;
		try {
			int id = Integer.parseInt(courseId);
			chapters = chapterListDao.selectAllByCourseId(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("数据转换异常");
		}
		return chapters;
	}
	@Override
	public List<Course> findCourses() {
		List<Course> courses = chapterListDao.findCourses();
		return courses;
	}

}
