package com.itany.netClass.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.constant.DictConstant;
import com.itany.netClass.constant.ResponseCodeConstant;
import com.itany.netClass.entity.*;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.ParameterUtil;
import com.itany.netClass.util.ResponseResult;



@RequestMapping("/chapter")
public class ChapterController {
	ChapterService chapterService = (ChapterService) ObjectFactory.getObject("ChapterService");
	
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		
		String id = request.getParameter("id"); 
		if(id==null){
			id=(String) request.getSession().getAttribute("id11");
		}
		System.out.println(id);
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		if (ParameterUtil.isNull(pageNo)) {
			pageNo = DictConstant.PAGE_NO_DEFAULT;
		}
		if (ParameterUtil.isNull(pageSize)) {
			pageSize = DictConstant.PAGE_SIZE_DEFAULT;
		}

		//开始使用分页插件
		//1.设置分页属性,当前页与一页显示多少条
		PageHelper.startPage(Integer.parseInt(pageNo),
				Integer.parseInt(pageSize));

		//2.查询业务处理的数据
		List<Chapter> chapters=chapterService.findChaptersByCourseId(Integer.parseInt(id));
		
		//3.对业务数据进行加工，封装成分页对象
		PageInfo<Chapter> chapterPages = new PageInfo<Chapter>(chapters);
		
		//request.setAttribute("chapters", chapters);
		request.setAttribute("chapterPages", chapterPages);  
		request.setAttribute("id", id);

		return "backend/courseResourseSet";
	}
	
	@ResponseBody
	@RequestMapping("/modifyStatus")
	public ResponseResult modifyStatus(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();

		String id = request.getParameter("id");
		String status = request.getParameter("status");
		
		try {
			chapterService.modifyStatus(id, status);
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS); //0
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping("/addChapterAndResource")
	public String addChapter(HttpServletRequest request,
			HttpServletResponse response,List<CommonsMultipartFile> files) throws ParseException {
		Resource resource=new Resource();
		String courseId=request.getParameter("courseId");
		String title=request.getParameter("title");
		String info=request.getParameter("info");
		
		String ResourseTitle = request.getParameter("ResourseTitle");
		String  file_cost_type_id=request.getParameter("file_cost_type_id");
		String costNumber=request.getParameter("costNumber");
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println(date);
		//对file文件的处理
		if (!files.get(0).isEmpty()) {// 判断文件是否不存在
			String uploadPath = CommonUtil.getUploadPath();
			String contextPath = CommonUtil.getContextPath();
			System.out.println("uploadPath=" + uploadPath);
			String fullname=files.get(0).getOriginalFilename();
			System.out.println("uploadPath:"+uploadPath);
			System.out.println("fullname:"+fullname);
			
			String file_type=fullname.substring(fullname.lastIndexOf(".")+1);
		
			
			resource.setCreateDate(date);
			resource.setPath(uploadPath);
			resource.setOriginalName(fullname);
			resource.setFileType(file_type);
			resource.setTitle(ResourseTitle);
			resource.setTitle(title);
			resource.setCostType(Integer.parseInt(file_cost_type_id));
			resource.setCostNumber(Integer.parseInt(costNumber));
			resource.setUserId(1);
			String newFileName = System.currentTimeMillis() + files.get(0).getOriginalFilename();
			request.setAttribute("imageUrl", "/images/" + newFileName);
			try {
				File parent = new File(uploadPath, "images");
				System.out.println("parent:"+ parent);
				System.err.println("mkdir=" + parent.mkdirs());
				files.get(0).transferTo(new File(parent, newFileName));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 插入章节信息
		chapterService.insertChapter(Integer.parseInt(courseId), title, info, date, Constant.STATUS_ENABLE);
		request.getSession().setAttribute("id11", courseId);
	
		
		//查询章节标题和资源标题
		User user=(User) request.getSession().getAttribute("userBack");
		Integer ChapterId=chapterService.selectChapter(title);
		resource.setChapterId(ChapterId);
		resource.setUserId(user.getId());// 先固定住,之后改
		resource.setStatus(Constant.STATUS_ENABLE);
		System.out.println(resource);
		//插入资源信息
		chapterService.insertResource(resource);
		
		return "redirect:/chapter/findAll.do";
	}
	
	@RequestMapping("/selectByFuzzy")
	public String selectByFuzzy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String title=request.getParameter("title");
		String info=request.getParameter("info");
		String beginDate=request.getParameter("beginDate");
		String endDate=request.getParameter("endDate");
		String status=request.getParameter("course-resource-stauts-search");
		
		
	   ChapterMessage chap=new ChapterMessage();
	   chap.setTitle(title);
	   chap.setInfo(info);
	   chap.setBeginDate(beginDate);
	   chap.setEndDate(endDate);
	   chap.setStatus(Integer.parseInt(status));
	   String id = request.getParameter("id"); 
		
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		if (ParameterUtil.isNull(pageNo)) {
			pageNo = DictConstant.PAGE_NO_DEFAULT;
		}
		if (ParameterUtil.isNull(pageSize)) {
			pageSize = DictConstant.PAGE_SIZE_DEFAULT;
		}

		//开始使用分页插件
		//1.设置分页属性,当前页与一页显示多少条
		PageHelper.startPage(Integer.parseInt(pageNo),
				Integer.parseInt(pageSize));
		//2.查询业务处理的数据
	   List<Chapter> chapters=chapterService.selectByFuzzy(chap);
				
	   //3.对业务数据进行加工，封装成分页对象
	  PageInfo<Chapter> chapterPages = new PageInfo<Chapter>(chapters);
				
	  request.setAttribute("chapterPages", chapterPages);  
	  request.setAttribute("chap", chap);  
//	  request.setAttribute("id", id);
	 return "backend/courseResourseSet";
	}

}
