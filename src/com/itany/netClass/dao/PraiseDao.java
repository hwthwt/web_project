package com.itany.netClass.dao;

import java.util.List;

import com.itany.netClass.entity.Praise;

public interface PraiseDao {

	List<Praise> selectByUserIdAndCommentId(int parseInt, int parseInt2);

	void addByUserIdAndCommentId(int parseInt, int parseInt2);

}
