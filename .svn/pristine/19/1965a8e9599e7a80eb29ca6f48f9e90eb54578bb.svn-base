package com.itany.netClass.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.constant.DictConstant;
import com.itany.netClass.constant.ResponseCodeConstant;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.CourseSet;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.ParameterUtil;
import com.itany.netClass.util.ResponseResult;

@RequestMapping("/comment")
public class CommentController {

	CommentService commentService = (CommentService) ObjectFactory
			.getObject("commentService");
	
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo1 = (String) request.getSession().getAttribute("pageNo");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		System.out.println("-----------" + pageNo1);
		if (!ParameterUtil.isNull(pageNo1) && ParameterUtil.isNull(pageNo)) {
			pageNo = pageNo1;
			request.getSession().removeAttribute("pageNo");
		}
		if (ParameterUtil.isNull(pageNo)) {
			pageNo = DictConstant.PAGE_NO_DEFAULT;
		}
		if (ParameterUtil.isNull(pageSize)) {
			pageSize = DictConstant.PAGE_SIZE_DEFAULT;
		}
		// 开始使用分页插件
		// 1.设置分页属性,当前第几页，一页多少条
		PageHelper.startPage(Integer.parseInt(pageNo),
				Integer.parseInt(pageSize));
		// 2.获取业务数据
		List<Comment> comments = commentService.findAll();
		// 3.对业务数据进行加工
		// 将其封装成分页对象
		PageInfo<Comment> pageTypes = new PageInfo<Comment>(comments);
		String uploadPath = CommonUtil.getUploadPath();
		request.getSession().setAttribute("pageTypes", pageTypes);
		request.getSession().setAttribute("comments", comments);
		request.getSession().setAttribute("COMMENT_STATUS_WAITING",
				Constant.COMMENT_STATUS_WAITING);
		request.getSession().setAttribute("STATUS_ENABLE",
				Constant.STATUS_ENABLE);
		request.getSession().setAttribute("STATUS_DISABLE",
				Constant.STATUS_DISABLE);
		request.getSession().setAttribute("uploadPath", uploadPath);

		return "redirect:/backcommentManageSet.do";

	}
	@RequestMapping("/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		commentService.update(id,status);
		request.getSession().setAttribute("pageNo", pageNo);
		
		return "redirect:/comment/findAll.do";
	}
	@ResponseBody
	@RequestMapping("/check")
	public ResponseResult check(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String usercomment = request.getParameter("usercomment");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		try {
			commentService.searchResource(username,usercomment, beginTime, endTime,
					 session);
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
		PageInfo<Comment> pageInfo = commentService.showSelect(pageNoStr,
				session);
		if (pageInfo.getSize() == 0) {
			pageNoStr = (Integer.parseInt(pageNoStr)-1)+"";
			pageInfo = commentService.showSelect(pageNoStr, session);
		}
		request.getSession().setAttribute("pageTypes", pageInfo);
		String src = "/comment/showSelect.do?pageNo=";
		request.getSession().setAttribute("commentsrc", src);
		return "redirect:/backcommentManageSet.do";
	}
}
