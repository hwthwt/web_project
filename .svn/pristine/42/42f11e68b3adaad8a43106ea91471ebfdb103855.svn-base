package com.itany.netClass.service.proxy;

import java.util.List;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.service.SelectFont;
import com.itany.netClass.transaction.TransactionManager;

public class SelectFontServiceProxy implements SelectFont{
	private TransactionManager tran;
	private SelectFont selectFont;
	
	@Override
	public List<CourseType> selectFirst() {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		selectFont=(SelectFont) ObjectFactory.getObject("selectFontServiceTarget");
		tran.beginTransaction();
		List<CourseType> lists=selectFont.selectFirst();	
		tran.commit();
		return lists;
	}

	@Override
	public List<CourseType> selectThird(String firstName) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		selectFont=(SelectFont) ObjectFactory.getObject("selectFontServiceTarget");
		tran.beginTransaction();
		List lists=selectFont.selectThird(firstName);
		tran.commit();
		return lists;
	}

	@Override
	public List<Course> selectAll(Integer id, String searchContext) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		selectFont=(SelectFont) ObjectFactory.getObject("selectFontServiceTarget");
		tran.beginTransaction();
		List<Course> lists=selectFont.selectAll(id, searchContext);
		tran.commit();
		return lists;
	}

	@Override
	public List<Course> selectQuanBu(String searchContext) {
		tran=(TransactionManager) ObjectFactory.getObject("transaction");
		selectFont=(SelectFont) ObjectFactory.getObject("selectFontServiceTarget");
		tran.beginTransaction();
		List<Course> lists=selectFont.selectQuanBu(searchContext);
		tran.commit();
		return lists;
	}

}
