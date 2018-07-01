package com.itany.netClass.service;

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

public interface MycourseService {

	public List<UserResource> findAll(User u);

	public List<Resource> findAllResource(User u);

	public void delete(String id);

	public void insertResource(int userid,String title, String type, String number,
			List<CommonsMultipartFile> files) throws TitleEmptyException, TypeEmptyException, NumberEmptyException, NumberErrorException, FileEmptyException, TitleErrorException, OtherException, TitleException, FileErrorException;

	public void updateResource(int userid, String id, String title,
			String type, String number, List<CommonsMultipartFile> files) throws TitleEmptyException, TypeEmptyException, NumberEmptyException, NumberErrorException, FileEmptyException, TitleErrorException, OtherException, TitleException, FileErrorException;

}
