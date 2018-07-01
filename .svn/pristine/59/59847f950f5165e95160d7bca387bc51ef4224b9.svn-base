package com.itany.netClass.service.impl;

import java.util.List;

import com.itany.netClass.dao.ChapterListDao;
import com.itany.netClass.dao.PraiseDao;
import com.itany.netClass.entity.Praise;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.PraiseService;

public class PraiseServiceImpl implements PraiseService {
	PraiseDao praiseDao = (PraiseDao) ObjectFactory.getObject("praiseDao");
	@Override
	public List<Praise> selectByUserIdAndCommentId(int parseInt, int parseInt2) {
		List<Praise> praises = praiseDao.selectByUserIdAndCommentId(parseInt, parseInt2);
		return praises;
	}

	@Override
	public void addByUserIdAndCommentId(int parseInt, int parseInt2) {
		praiseDao.addByUserIdAndCommentId(parseInt, parseInt2);
	}

}
