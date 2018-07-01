package com.itany.netClass.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.exception.TextIsEmptyException;
import com.itany.netClass.exception.TextIsErrorException;

public interface CommentService {

	List<Comment> findAll();

	void update(String id, String status);

	List<Comment> findCommentsByCourseId(List<Integer> list);

	List<Comment> findCommentsByResourceId(String resourceId);

	void add(String text, String resourceId, Integer uid) throws TextIsEmptyException,TextIsErrorException, Exception;


	void searchResource(String username, String usercomment, String beginTime,
			String endTime, HttpSession session) throws ParseException, DateErrorException, DateMistakeException;

	PageInfo<Comment> showSelect(String pageNoStr, HttpSession session);

}
