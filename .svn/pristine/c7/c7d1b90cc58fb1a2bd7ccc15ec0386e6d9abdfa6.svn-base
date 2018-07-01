package com.itany.netClass.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Record;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.PointEmptyException;
import com.itany.netClass.exception.PointErrorException;
import com.itany.netClass.exception.PointMaxException;
import com.itany.netClass.exception.PointNotNumException;

public interface RecordService {

	public PageInfo<Record> findAll(String pageNoStr, User u);

	public void check(String point, HttpSession session) throws PointEmptyException, PointNotNumException, PointErrorException, PointMaxException;

	public PageInfo<Record> change(String pageNoStr, HttpSession session);
	public List<Record>  findByUserId(User u);

}
