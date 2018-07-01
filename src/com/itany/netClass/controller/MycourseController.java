package com.itany.netClass.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.ResponseCodeConstant;
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
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.util.ParameterUtil;
import com.itany.netClass.util.ResponseResult;

@RequestMapping("/mycourse")
public class MycourseController {

	private MycourseService mycourseService = (MycourseService) ObjectFactory
			.getObject("mycourseService");

	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 假的User
		String no = (String) request.getParameter("nos");
		System.out.println(no);
		int nos = 0;
		if (no == null) {
			nos = 0;
		} else {
			nos = Integer.parseInt(no) + 1;
		}
		/*User user = new User();
		user.setId(17);
		session.setAttribute("user", user);*/
		User u = (User) session.getAttribute("user");
		List<UserResource> list = mycourseService.findAll(u);
		List<Resource> list2 = mycourseService.findAllResource(u);
		request.setAttribute("nos", nos);
		if (nos == list.size() - 1) {
			request.setAttribute("nos", list.size() - 1);
		}
		session.setAttribute("resourceall", list2);
		session.setAttribute("userresource", list);
		return "/frontmycourse.do";
	}

	@ResponseBody
	@RequestMapping("/delete")
	public ResponseResult delete(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		String id = request.getParameter("id");
		try {
			mycourseService.delete(id);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage("服务器内部异常");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/selectById")
	public ResponseResult selectById(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		String id = request.getParameter("id");
		ResourceService resourceService = (ResourceService) ObjectFactory
				.getObject("resourceService");
		try {
			Resource r = resourceService.selectById(id);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
			result.setReturnObject(r);
		} catch (Exception e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage("服务器内部异常");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/update")
	public ResponseResult update(HttpServletRequest request,
			HttpServletResponse response, List<CommonsMultipartFile> files) {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		User u = (User) request.getSession().getAttribute("user");
		int userid = u.getId();
		System.out.println(id+title+type+userid+"aa");
		String number = request.getParameter("number");
		ResponseResult result = new ResponseResult();
		if (ParameterUtil.isNull(id)) {
			try {
				mycourseService.insertResource(userid,title,type,number,files);
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
				result.setMessage("成功");
			} catch (TitleEmptyException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (TypeEmptyException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (NumberEmptyException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (NumberErrorException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (FileEmptyException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (TitleErrorException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (OtherException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (TitleException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (FileErrorException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			}
			return result;
		}else{
			try {
				mycourseService.updateResource(userid,id,title,type,number,files);
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
				result.setMessage("成功");
			} catch (TitleEmptyException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (TypeEmptyException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (NumberEmptyException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (NumberErrorException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (FileEmptyException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (TitleErrorException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (OtherException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (TitleException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			} catch (FileErrorException e) {
				result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
				result.setMessage(e.getMessage());
			}
			return result;
		}
		
	}
}
