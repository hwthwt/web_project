package com.itany.netClass.service.proxy;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Record;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.PointEmptyException;
import com.itany.netClass.exception.PointErrorException;
import com.itany.netClass.exception.PointMaxException;
import com.itany.netClass.exception.PointNotNumException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.RecordService;
import com.itany.netClass.transaction.TransactionManager;

public class RecordServiceProxy implements RecordService {

	private TransactionManager tran = (TransactionManager) ObjectFactory
			.getObject("transaction");
	private RecordService recordServiceTarget = (RecordService) ObjectFactory
			.getObject("recordServiceTarget");

	@Override
	public PageInfo<Record> findAll(String pageNoStr, User u) {
		tran.beginTransaction();
		PageInfo<Record> pageInfo = recordServiceTarget.findAll(pageNoStr, u);
		tran.commit();
		return pageInfo;
	}

	@Override
	public void check(String point, HttpSession session)
			throws PointEmptyException, PointNotNumException,
			PointErrorException, PointMaxException {
		tran.beginTransaction();
		try {
			recordServiceTarget.check(point, session);
			tran.commit();
		} catch (PointEmptyException e) {
			tran.rollback();
			throw e;
		} catch (PointNotNumException e) {
			tran.rollback();
			throw e;
		} catch (PointErrorException e) {
			tran.rollback();
			throw e;
		} catch (PointMaxException e) {
			tran.rollback();
			throw e;
		}
	}

	@Override
	public PageInfo<Record> change(String pageNoStr, HttpSession session) {
		tran.beginTransaction();
		PageInfo<Record> pageInfo = recordServiceTarget.change(pageNoStr,
				session);
		tran.commit();
		return pageInfo;
	}

	@Override
	public List<Record> findByUserId(User u) {
		tran.beginTransaction();
		List<Record> list = recordServiceTarget.findByUserId(u);
		return list;
	}

}
