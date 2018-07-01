package com.itany.netClass.service.proxy;

import java.util.List;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.UserResource;
import com.itany.netClass.exception.FileEmptyException;
import com.itany.netClass.exception.FileErrorException;
import com.itany.netClass.exception.NumberEmptyException;
import com.itany.netClass.exception.NumberErrorException;
import com.itany.netClass.exception.OtherException;
import com.itany.netClass.exception.TitleEmptyException;
import com.itany.netClass.exception.TitleErrorException;
import com.itany.netClass.exception.TitleException;
import com.itany.netClass.exception.TypeEmptyException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.MycourseService;
import com.itany.netClass.transaction.TransactionManager;

public class MycourseServiceProxy implements MycourseService {
	private MycourseService mycourseServiceTarget = (MycourseService) ObjectFactory
			.getObject("mycourseServiceTarget");
	private TransactionManager tran = (TransactionManager) ObjectFactory
			.getObject("transaction");

	@Override
	public List<UserResource> findAll(User u) {
		tran.beginTransaction();
		List<UserResource> list = mycourseServiceTarget.findAll(u);
		tran.commit();
		return list;
	}

	@Override
	public List<Resource> findAllResource(User u) {
		tran.beginTransaction();
		List<Resource> list = mycourseServiceTarget.findAllResource(u);
		tran.commit();
		return list;

	}

	@Override
	public void delete(String id) {
		tran.beginTransaction();
		mycourseServiceTarget.delete(id);
		tran.commit();

	}

	@Override
	public void insertResource(int userid, String title, String type,
			String number, List<CommonsMultipartFile> files)
			throws TitleEmptyException, TypeEmptyException,
			NumberEmptyException, NumberErrorException, FileEmptyException,
			TitleErrorException, OtherException ,TitleException,FileErrorException{
		tran.beginTransaction();
		try {
			mycourseServiceTarget.insertResource(userid, title, type, number,
					files);
			tran.commit();
		} catch (TitleEmptyException e) {
			tran.rollback();
			throw e;
		} catch (TypeEmptyException e) {
			tran.rollback();
			throw e;
		} catch (NumberEmptyException e) {
			tran.rollback();
			throw e;
		} catch (NumberErrorException e) {
			tran.rollback();
			throw e;
		} catch (FileEmptyException e) {
			tran.rollback();
			throw e;
		} catch (TitleErrorException e) {
			tran.rollback();
			throw e;
		} catch (OtherException e) {
			tran.rollback();
			throw e;
		} catch (TitleException e) {
			tran.rollback();
			throw e;
		} catch (FileErrorException e) {
			tran.rollback();
			throw e;
		}

	}

	@Override
	public void updateResource(int userid, String id, String title,
			String type, String number, List<CommonsMultipartFile> files)
			throws TitleEmptyException, TypeEmptyException,
			NumberEmptyException, NumberErrorException, FileEmptyException,
			TitleErrorException, OtherException,TitleException,FileErrorException {
		tran.beginTransaction();
		try {
			mycourseServiceTarget.updateResource(userid, id, title, type,
					number, files);
			tran.commit();
		} catch (TitleEmptyException e) {
			tran.rollback();
			throw e;
		} catch (TypeEmptyException e) {
			tran.rollback();
			throw e;
		} catch (NumberEmptyException e) {
			tran.rollback();
			throw e;
		} catch (NumberErrorException e) {
			tran.rollback();
			throw e;
		} catch (FileEmptyException e) {
			tran.rollback();
			throw e;
		} catch (TitleErrorException e) {
			tran.rollback();
			throw e;
		} catch (OtherException e) {
			tran.rollback();
			throw e;
		} catch (TitleException e) {
			tran.rollback();
			throw e;
		} catch (FileErrorException e) {
			tran.rollback();
			throw e;
		}

	}
}
