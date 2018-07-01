package com.itany.netClass.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.dao.MycourseDao;
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
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.ParameterUtil;

public class MycourseServiceImpl implements MycourseService {

	private MycourseDao mycourseDao = (MycourseDao) ObjectFactory
			.getObject("mycourseDao");

	@Override
	public List<UserResource> findAll(User u) {
		int id = u.getId();
		List<UserResource> list = new ArrayList<UserResource>();
		list = mycourseDao.selectByUserId(id);
		return list;
	}

	@Override
	public List<Resource> findAllResource(User u) {
		int id = u.getId();
		List<Resource> list = new ArrayList<Resource>();
		list=mycourseDao.selectAllResource(id);
		return list;
	}

	@Override
	public void delete(String id) {
		mycourseDao.updateStatus(id);
	}

	@Override
	public void insertResource(int userid,String title, String type, String number,
			List<CommonsMultipartFile> files) throws TitleEmptyException, TypeEmptyException, NumberEmptyException, NumberErrorException, FileEmptyException, TitleErrorException, OtherException, TitleException, FileErrorException {
		if (ParameterUtil.isNull(title)) {
			throw new TitleEmptyException("标题为空");
		}
		if ("-1".equals(type)) {
			throw new TypeEmptyException("花费资源为空");
		}
		if (ParameterUtil.isNull(number)) {
			throw new NumberEmptyException("花费值为空");
		}
		String reg = "[0-9]+";
		if (!number.matches(reg)) {
			throw new NumberEmptyException("花费值输入有误");
		}
		int num = Integer.parseInt(number);
		if (num<=0) {
			throw new NumberErrorException("花费值要为正数");
		}
		if (files.isEmpty()) {
			throw new FileEmptyException("文件为空");
		}
		String nn = "[0-9a-zA-Z\u4e00-\u9fa5]+";
		if (!title.matches(nn)) {
			throw new TitleErrorException("标题格式不对");
		}
		Resource re = new Resource();
		re=mycourseDao.selectByTitle(title);
		if (re!=null) {
			throw new TitleException("标题已经存在");
		}
		String uploadPath = CommonUtil.getUploadPath();
		System.out.println("uploadPath=" + uploadPath);
		String newFileName = System.currentTimeMillis()
				+ files.get(0).getOriginalFilename();
		System.out.println("新文件名" + newFileName);
		File parent = new File(uploadPath, "resource");
		System.err.println("mkdir=" + parent.mkdirs());
		try {
			files.get(0).transferTo(new File(parent, newFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String resourcePath = "/resource/" + newFileName;
		System.out.println("resourcePath:" + resourcePath);
		Resource r = new Resource();
		r.setTitle(title);
		r.setPath(resourcePath);
		r.setOriginalName(newFileName);
		r.setFileSize((int)files.get(0).getSize());
		String name = newFileName.substring(newFileName.lastIndexOf(".")+1);
		if ("jsp".equals(name)) {
			throw new FileErrorException("不能上传JSP");
		}
		r.setFileType(name);
		r.setCostType(Integer.parseInt(type));
		r.setCostNumber(num);
		r.setUserId(userid);
		r.setStatus(0);
		r.setCreateDateD(new Date());
		mycourseDao.insertResource(r);
		
	}

	@Override
	public void updateResource(int userid, String id, String title,
			String type, String number, List<CommonsMultipartFile> files) throws TitleEmptyException, TypeEmptyException, NumberEmptyException, NumberErrorException, FileEmptyException, TitleErrorException, OtherException, TitleException, FileErrorException {
		if (ParameterUtil.isNull(title)) {
			throw new TitleEmptyException("标题为空");
		}
		if ("-1".equals(type)) {
			throw new TypeEmptyException("花费资源为空");
		}
		if (ParameterUtil.isNull(number)) {
			throw new NumberEmptyException("花费值为空");
		}
		String reg = "[0-9]+";
		if (!number.matches(reg)) {
			throw new NumberEmptyException("花费值输入有误");
		}
		int num = Integer.parseInt(number);
		if (num<=0) {
			throw new NumberErrorException("花费值要为正数");
		}
		if (files.isEmpty()) {
			throw new FileEmptyException("文件为空");
		}
		String nn = "[0-9a-zA-Z\u4e00-\u9fa5]+";
		if (!title.matches(nn)) {
			throw new TitleErrorException("标题格式不对");
		}
		Resource re = new Resource();
		re=mycourseDao.selectByTitleAndId(title,id);
		if (re!=null) {
			throw new TitleException("用户名重复");
		}
		String uploadPath = CommonUtil.getUploadPath();
		String newFileName = System.currentTimeMillis()
				+ files.get(0).getOriginalFilename();
		File parent = new File(uploadPath, "resource");
		try {
			files.get(0).transferTo(new File(parent, newFileName));
		} catch (Exception e) {
			throw new OtherException("文件异常");
		}
		String resourcePath = "/resource/" + newFileName;
		Resource r = new Resource();
		r.setTitle(title);
		r.setPath(resourcePath);
		r.setOriginalName(newFileName);
		r.setFileSize((int)files.get(0).getSize());
		String name = newFileName.substring(newFileName.lastIndexOf(".")+1);
		if ("jsp".equals(name)) {
			throw new FileErrorException("不能上传JSP");
		}
		r.setFileType(name);
		r.setCostType(Integer.parseInt(type));
		r.setCostNumber(num);
		r.setStatus(0);
		r.setCreateDateD(new Date());
		r.setId(Integer.parseInt(id));
		mycourseDao.updateResource(r);
	}

}
