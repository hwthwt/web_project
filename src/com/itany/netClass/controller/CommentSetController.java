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
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.service.CommentSetService;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.ParameterUtil;
import com.itany.netClass.util.ResponseResult;
@RequestMapping("/commentSet")
public class CommentSetController {

	CommentSetService commentSetService = (CommentSetService) ObjectFactory
			.getObject("commentSetService");
	
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		//接收对应的资源的id
		String reId =request.getParameter("id");
		System.out.println("接受到的id为:"+reId);
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
		List<Comment> comments = commentSetService.findAll(reId);
		// 3.对业务数据进行加工
		// 将其封装成分页对象
		PageInfo<Comment> pageTypes = new PageInfo<Comment>(comments);
		String uploadPath = CommonUtil.getUploadPath();
		request.getSession().setAttribute("pageTypes", pageTypes);
		request.getSession().setAttribute("comments", comments);
		request.getSession().setAttribute("STATUS_ENABLE",
				Constant.STATUS_ENABLE);
		request.getSession().setAttribute("STATUS_DISABLE",
				Constant.STATUS_DISABLE);
		request.getSession().setAttribute("uploadPath", uploadPath);

		return "redirect:/backcommentSet.do";

	}
	@RequestMapping("/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		commentSetService.update(id,status);
		return "redirect:/commentSet/findAll.do";
	}
	
	@ResponseBody
	@RequestMapping("/check")
	public ResponseResult check(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		HttpSession session = request.getSession();
		String reId = "1";
		String username = request.getParameter("username");
		String usercomment = request.getParameter("usercomment");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String option = request.getParameter("option");
		System.out.println(username+usercomment+beginTime+endTime+option);
		try {
			commentSetService.searchResource(reId,username,usercomment, beginTime, endTime,
					option,	session);
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
		PageInfo<Comment> pageInfo = commentSetService.showSelect(pageNoStr,
				session);
		if (pageInfo.getSize() == 0) {
			pageNoStr = (Integer.parseInt(pageNoStr)-1)+"";
			pageInfo = commentSetService.showSelect(pageNoStr, session);
		}
		request.getSession().setAttribute("pageTypes", pageInfo);
		String src = "/commentSet/showSelect.do?pageNo=";
		request.getSession().setAttribute("commentSetsrc", src);
		return "redirect:/backcommentSet.do";
	}
}
