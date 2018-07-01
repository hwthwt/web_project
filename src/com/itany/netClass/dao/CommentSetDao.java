package com.itany.netClass.dao;

import java.util.List;

import com.itany.netClass.entity.Comment;

public interface CommentSetDao {

	List<Comment> selectAll(int id);

	void update(int id, int status);

	List<Comment> selectSearch(Comment c);

}
