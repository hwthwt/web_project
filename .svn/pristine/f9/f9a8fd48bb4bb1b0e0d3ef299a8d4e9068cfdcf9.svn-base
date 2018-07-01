package com.itany.netClass.transaction.impl;

import org.apache.ibatis.session.SqlSession;

import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.transaction.TransactionManager;
import com.itany.netClass.util.MyBatisUtil;

public class MyBatisTransactionManager implements TransactionManager {

	public void beginTransaction() {
//		SqlSession session = null;
		try {
//			session = MyBatisUtil.getSession();
			MyBatisUtil.getSession();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("开启事务失败", e);
		}
	}

	public void commit() {
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSession();
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("提交事务失败", e);
		} finally {
			MyBatisUtil.closeSession();
		}
	}

	public void rollback() {
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSession();
			session.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("回滚事务失败", e);
		} finally {
			MyBatisUtil.closeSession();
		}
	}

}
