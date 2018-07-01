package com.itany.netClass.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.ResponseCodeConstant;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.ResponseResult;

@RequestMapping("/resource")
public class ResourceController {
	
	ChapterService chapterService = (ChapterService) ObjectFactory.getObject("ChapterService");
	ResourceService resourceService = (ResourceService) ObjectFactory.getObject("resourceService");
	
	@RequestMapping("/modifyResouce")
	public String modifyResource(HttpServletRequest request,
			HttpServletResponse response,List<CommonsMultipartFile> files) throws ParseException {
		String chapterid=request.getParameter("chapterid"); //章节id
		String chaptertitle=request.getParameter("chaptertitle");  //章节标题
		String resourcename=request.getParameter("resourcename"); //资源标题
		String file_cost_type_id=request.getParameter("file_cost_type_id");  //积分或者金币
		String costNum=request.getParameter("costNum"); //花费的数量
		String courseId=request.getParameter("courseId");//课程的ID
		
		chapterService.updateTitleById(Integer.parseInt(chapterid),chaptertitle);
		Resource resource=new Resource();
		resource.setChapterId(Integer.parseInt(chapterid));
		resource.setTitle(resourcename);
		resource.setCostType(Integer.parseInt(file_cost_type_id));
		resource.setCostNumber(Integer.parseInt(costNum));
		//修改资源
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//创建时间
		resource.setCreateDate(date);
		if (!files.get(0).isEmpty()) {// 判断文件是否不存在
			String uploadPath = CommonUtil.getUploadPath();  //文件路径
			String contextPath = CommonUtil.getContextPath();
			System.out.println("uploadPath=" + uploadPath);
			String fullname=files.get(0).getOriginalFilename(); //文件名称
			System.out.println("uploadPath:"+uploadPath);
			System.out.println("fullname:"+fullname);
			

			
			resource.setPath(uploadPath);
			resource.setOriginalName(fullname);

			String file_type=fullname.substring(fullname.lastIndexOf(".")+1);  //后缀名
	
			resource.setFileType(file_type);
			
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
		resourceService.updateResourceByChapterId(resource);
		request.getSession().setAttribute("id11", courseId);
		return "redirect:/chapter/findAll.do";
	}
	
	
	
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageInfo<Resource> pageInfo = resourceService.findAll(pageNo, session);
		request.getSession().setAttribute("findresource", pageInfo);
		String src = "/resource/findAll.do?pageNo=";
		request.getSession().setAttribute("resourcesrc", src);
		return "redirect:/backresourceSet.do";
	}

	@ResponseBody
	@RequestMapping("/modifyStatus")
	public ResponseResult modifyStatus(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		String id = request.getParameter("id");
		try {
			resourceService.modifyStatus(id);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage("服务器内部异常");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/searchResource")
	public ResponseResult searchResource(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		String nickname = request.getParameter("nickname");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		String filetype = request.getParameter("filetype");
		try {
			resourceService.searchResource(title, nickname, beginTime, endTime,
					status, filetype, session);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
		} catch (DateErrorException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		} catch (DateMistakeException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		} catch (ParseException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@RequestMapping("/showSelect")
	public String showSelect(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNoStr = request.getParameter("pageNo");
		HttpSession session = request.getSession();
		PageInfo<Resource> pageInfo = resourceService.showSelect(pageNoStr,
				session);
		if (pageInfo.getSize() == 0) {
			pageNoStr = (Integer.parseInt(pageNoStr)-1)+"";
			pageInfo = resourceService.showSelect(pageNoStr, session);
		}
		request.getSession().setAttribute("findresource", pageInfo);
		String src = "/resource/showSelect.do?pageNo=";
		request.getSession().setAttribute("resourcesrc", src);
		return "redirect:/backresourceSet.do";
	}

	
}
