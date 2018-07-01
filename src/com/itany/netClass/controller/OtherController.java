package com.itany.netClass.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.annotation.ResponseEntity;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.mvc.util.HttpHeaders;
import com.itany.mvc.util.MediaType;

@RequestMapping("/other")
public class OtherController {

	// ==========================请求转发重定向==========================
	@RequestMapping("/f3")
	public String f3(HttpServletRequest request, HttpServletResponse response) {
		// 转发请求
		return "abc.do";
	}

	@RequestMapping("/f4")
	public String f4(HttpServletRequest request, HttpServletResponse response) {
		// 重定向请求
		return "redirect:/abc.do";
	}

	@RequestMapping("/f5")
	public String f5(HttpServletRequest request, HttpServletResponse response) {
		// 重定向到jsp --> 不允许 页面在web-inf中
		return "redirect:abc";
	}

	@RequestMapping("/f6")
	public String f6(HttpServletRequest request, HttpServletResponse response) {
		// 转发到页面
		return "abc";
	}
	// ==========================请求转发重定向==========================

	// start........................ ajax请求返回JSON
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, String> test(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("abc", "123");
		map.put("aaa", "222");
		map.put("ddd", "dsfadsfg");

		return map;
	}

	@RequestMapping("/test1")
	@ResponseBody
	public String test1(HttpServletRequest request, HttpServletResponse response) {
		return "asdads";
	}
	// end........................ ajax请求返回JSON

	// =============文件上传下载====================
	// 文件上传，参数必须是这三个
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request, HttpServletResponse response, CommonsMultipartFile file)
			throws Exception {
		String username = request.getParameter("username");
		String[] hobs = request.getParameterValues("hob");

		System.out.println(username);
		System.out.println(Arrays.toString(hobs));

		System.out.println(file.getOriginalFilename());
		file.transferTo(new File("E:/ax.png"));
		return null;
	}

	// 文件下载，返回值必须是ResponseEntity
	@RequestMapping("/down")
	public ResponseEntity download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FileInputStream fis = new FileInputStream("e:/ax.png");
		byte[] data = new byte[fis.available()];
		fis.read(data);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "哈哈.png");
		return new ResponseEntity(data, headers);
	}
	// =============文件上传下载====================
}
