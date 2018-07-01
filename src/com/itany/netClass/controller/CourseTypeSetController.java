package com.itany.netClass.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.constant.ResponseCodeConstant;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeExistsException;
import com.itany.netClass.exception.CourseTypeNameEmptyException;
import com.itany.netClass.exception.CourseTypeNameErrorException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeSetService;
import com.itany.netClass.util.ResponseResult;

@RequestMapping("/courseTypeSet")
public class CourseTypeSetController {
	private CourseTypeSetService courseTypeSetService = (CourseTypeSetService) ObjectFactory
			.getObject("courseTypeSetService");

	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageInfo<CourseType> pageInfo = courseTypeSetService.findAll(pageNo);
		request.getSession().setAttribute("findcoursetype", pageInfo);
		String src = "/courseTypeSet/findAll.do?pageNo=";
		request.getSession().setAttribute("coursesrc", src);
		return "redirect:/backcourseTypeSet.do";

	}

	@ResponseBody
	@RequestMapping("/modifyStatus")
	public ResponseResult modifyStatus(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		String id = request.getParameter("id");
		try {
			courseTypeSetService.modifyStatus(id);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage("服务器内部异常");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/findById")
	public ResponseResult findById(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		String id = request.getParameter("id");
		try {
			CourseType ct = courseTypeSetService.findById(id);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
			result.setReturnObject(ct);
		} catch (Exception e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage("服务器内部异常");
		}

		return result;
	}

	@ResponseBody
	@RequestMapping("/modifyType")
	public ResponseResult modifyType(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		String id = request.getParameter("id");
		String typeName = request.getParameter("typeName");
		try {
			courseTypeSetService.modifyType(id, typeName);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
		} catch (CourseTypeNameEmptyException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		} catch (CourseTypeNameErrorException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		} catch (CourseTypeExistsException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/addType")
	public ResponseResult addType(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		String id = (String) request.getSession().getAttribute("pd");
		String typeName = request.getParameter("typeName");
		System.out.println(id + " " + typeName);
		try {
			courseTypeSetService.addType(id, typeName);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
		} catch (CourseTypeNameEmptyException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		} catch (CourseTypeNameErrorException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		} catch (CourseTypeExistsException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@RequestMapping("/findSon")
	public String findSon(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<Integer> notnull = (List<Integer>) session.getAttribute("notnull");
		System.out.println(notnull);
		String parentId = request.getParameter("parentId");

		if (parentId == null) {
			parentId = (String) request.getSession().getAttribute("pd");
		}
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		if (notnull != null) {
			for (Integer inte : notnull) {
				if (parentId.equals(inte + "")) {
					parentId = (String) request.getSession().getAttribute("pd");
					// pageNo =
					// Integer.parseInt(request.getParameter("pageNo"));
				}
			}
		}
		PageInfo<CourseType> pageInfo = courseTypeSetService.findSon(pageNo,
				parentId, session);
		request.getSession().setAttribute("findcoursetype", pageInfo);
		String src = "/courseTypeSet/findSon.do?parentId=" + parentId
				+ "&&pageNo=";
		request.getSession().setAttribute("coursesrc", src);
		request.getSession().setAttribute("pd", parentId);
		return "redirect:/backcourseTypeSet.do";
	}

	@RequestMapping("/findFather")
	public String findFather(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) request.getSession().getAttribute("pd");
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		String re = courseTypeSetService.findFather(id, session);
		return re;
	}
	
	/**
	 * 获取第三级别的种类名称
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findP3Name")
	public List<CourseType> findP3Name(HttpServletRequest request,
			HttpServletResponse response){
		List<CourseType> list = courseTypeSetService.findSonName();
		return list;
	}
}