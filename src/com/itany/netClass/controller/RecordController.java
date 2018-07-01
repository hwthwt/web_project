package com.itany.netClass.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.constant.ResponseCodeConstant;
import com.itany.netClass.entity.Record;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.PointEmptyException;
import com.itany.netClass.exception.PointErrorException;
import com.itany.netClass.exception.PointMaxException;
import com.itany.netClass.exception.PointNotNumException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.RecordService;
import com.itany.netClass.util.ResponseResult;

@RequestMapping("/record")
public class RecordController {
	private RecordService recordService = (RecordService) ObjectFactory
			.getObject("recordService");

	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		/*
		 * User a = new User(); a.setId(20); a.setAllPoint(30);
		 * a.setAllGold(50); session.setAttribute("user", a);
		 */
		User u = (User) session.getAttribute("user");
		String pageNoStr = request.getParameter("pageNo");
		PageInfo<Record> pageInfo = recordService.findAll(pageNoStr, u);
		session.setAttribute("findrecord", pageInfo);
		return "redirect:/frontrecord.do";
	}

	@ResponseBody
	@RequestMapping("/check")
	public ResponseResult check(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		String point = request.getParameter("point");
		HttpSession session = request.getSession();
		try {
			recordService.check(point, session);
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
		} catch (PointEmptyException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		} catch (PointNotNumException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		} catch (PointErrorException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		} catch (PointMaxException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@RequestMapping("/change")
	public String change(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNoStr = request.getParameter("pageNo");
		HttpSession session = request.getSession();
		PageInfo<Record> pageInfo = recordService.change(pageNoStr, session);
		session.setAttribute("findrecord", pageInfo);
		return "redirect:/frontrecord.do";
	}

}
