package com.itany.netClass.dao;

import java.util.List;
import java.util.Map;

import com.itany.netClass.entity.Comment;

public interface CommentDao {

	List<Comment> selectAll();

	void update(int id, int status);

	List<Comment> selectCommentsByCourseId(List<Integer> list);

	List<Comment> selectCommentsByResourceId(int id);

	void add(String text, int id, Integer uid);


	List<Comment> selectSearch(Comment c);

}
