package com.itany.netClass.service.proxy;

import java.util.List;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Praise;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterListService;
import com.itany.netClass.service.PraiseService;
import com.itany.netClass.transaction.TransactionManager;

public class PraiseServiceProxy implements PraiseService {

	private TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
	private PraiseService	praiseService=(PraiseService) ObjectFactory.getObject("praiseServiceTarget");
	
	@Override
	public List<Praise> selectByUserIdAndCommentId(int parseInt, int parseInt2) {
		tran.beginTransaction();
		List<Praise> praises;
		try {
			praises = praiseService.selectByUserIdAndCommentId(parseInt, parseInt2);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			throw e;
		}
		
		return praises;
	}

	@Override
	public void addByUserIdAndCommentId(int parseInt, int parseInt2) {
		tran.beginTransaction();
		try {
			praiseService.addByUserIdAndCommentId(parseInt, parseInt2);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			throw e;
		}
	}

}
