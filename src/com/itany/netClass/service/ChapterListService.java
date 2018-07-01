package com.itany.netClass.service;

import java.util.List;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Course;

public interface ChapterListService {

	List<Chapter> findChaptersByCourseId(String courseId);

	List<Course> findCourses();

}
