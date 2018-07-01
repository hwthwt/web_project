package com.itany.netClass.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;

public interface CommentSetService {

	List<Comment> findAll(String ReId);

	void update(String id, String status);

	void searchResource(String rId ,String username, String usercomment, String beginTime,
			String endTime, String option, HttpSession session) throws DateErrorException, ParseException, DateMistakeException;

	PageInfo<Comment> showSelect(String pageNoStr, HttpSession session);

}
