package com.itany.netClass.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.CourseSet;
import com.itany.netClass.exception.DataParseException;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.exception.EmptyChooseException;
import com.itany.netClass.exception.NameIsEmptyException;
import com.itany.netClass.exception.NameIsUsedException;
import com.itany.netClass.exception.infoIsEmptyException;

public interface CourseSetService {

	List<CourseSet> findAll();

	void updateStatusById(String id,  String status) throws DataParseException;

	boolean check(String id, String name, String info, String author,
			String recommendationGrade, String courseTypeId) throws NameIsUsedException,NameIsEmptyException, EmptyChooseException,infoIsEmptyException, Exception;

	void insert(String name, String info, String author,
			String recommendationGrade, String courseTypeId, String imagePath);

	CourseSet findById(String id);

	void update(String id, String name, String info, String author,
			String recommendationGrade, String courseTypeId, String imagePath);

	void searchResource(String authorname, String coursename, String beginTime,
			String endTime, String status, String type, HttpSession session) throws DateErrorException, ParseException, DateMistakeException;

	PageInfo<CourseSet> showSelect(String pageNoStr, HttpSession session);

}
