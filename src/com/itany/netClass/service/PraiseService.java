package com.itany.netClass.service;

import java.util.List;

import com.itany.netClass.entity.Praise;

public interface PraiseService {

	List<Praise> selectByUserIdAndCommentId(int parseInt, int parseInt2);

	void addByUserIdAndCommentId(int parseInt, int parseInt2);

}
