package com.itany.netClass.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.ResponseCodeConstant;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.UserMessage;
import com.itany.netClass.exception.CodeEmptyException;
import com.itany.netClass.exception.CodeErrorException;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.exception.EmailEmptyException;
import com.itany.netClass.exception.EmailErrorException;
import com.itany.netClass.exception.LoginNameEmptyException;
import com.itany.netClass.exception.NicenameException;
import com.itany.netClass.exception.NicknameEmptyException;
import com.itany.netClass.exception.NicknameExistsException;
import com.itany.netClass.exception.PasswordEmptyException;
import com.itany.netClass.exception.PasswordErrorException;
import com.itany.netClass.exception.RequestParameterException;
import com.itany.netClass.exception.RoleEmptyException;
import com.itany.netClass.exception.RoleErrorException;
import com.itany.netClass.exception.StatusErrorException;
import com.itany.netClass.exception.UserNoExistException;
import com.itany.netClass.exception.UserNotFoundException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.CommonUtil.MyImage;
import com.itany.netClass.util.ParameterUtil;
import com.itany.netClass.util.ResponseResult;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@RequestMapping("/user")
public class UserController {

	private UserService userService = (UserService) ObjectFactory
			.getObject("userService");

	@RequestMapping("/showLogin")
	public String showLogin(HttpServletRequest request,
			HttpServletResponse response) {
		return "login";// 转发至WEB-INF/pages/login.jsp
	}
	

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			User user = userService.login(username, password);
			request.getSession().setAttribute("user", user);
			return "redirect:/user/home.do";// redirect:重定向home.do请求(绝对请求路径)
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		return "/user/showLogin.do";// 绝对请求路径-转发
	}

	@RequestMapping("/cancel")
	public String cancel(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/backLogin.do";
	}

	@RequestMapping("/backLogin")
	public String backLogin(HttpServletRequest request,
			HttpServletResponse response) {

		// 获取请求中的数据
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password2");
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		try {
			User u = userService.backLogin(loginName, password, code, session);
			request.getSession().setAttribute("userBack", u);
			return "redirect:/back_home.do";
		} catch (CodeErrorException e) {
			request.setAttribute("backLogin", e.getMessage());
			return "/backLogin.do";
		} catch (LoginNameEmptyException e) {
			request.setAttribute("backLogin", e.getMessage());
			return "/backLogin.do";
		} catch (PasswordEmptyException e) {
			request.setAttribute("backLogin", e.getMessage());
			return "/backLogin.do";
		} catch (CodeEmptyException e) {
			request.setAttribute("backLogin", e.getMessage());
			return "/backLogin.do";
		} catch (UserNoExistException e) {
			request.setAttribute("backLogin", e.getMessage());
			return "/backLogin.do";
		} catch (PasswordErrorException e) {
			request.setAttribute("backLogin", e.getMessage());
			return "/backLogin.do";
		} catch (RoleErrorException e) {
			request.setAttribute("backLogin", e.getMessage());
			return "/backLogin.do";
		} catch (StatusErrorException e) {
			request.setAttribute("backLogin", e.getMessage());
			return "/backLogin.do";
		}
	}

	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (null != user) {
			session.removeAttribute("user");
		}
		session.invalidate();// 使session无效
		return "redirect:showLogin.do";// (相对路径重定向)
	}

	@RequestMapping("/regist")
	public String regist(HttpServletRequest request,
			HttpServletResponse response, CommonsMultipartFile file) {
		UserService userService = (UserService) ObjectFactory
				.getObject("userService");
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		System.out.println("file:" + file);
		if (!file.isEmpty()) {// 判断文件是否不存在
			String uploadPath = CommonUtil.getUploadPath();
			System.out.println("uploadPath=" + uploadPath);

			String newFileName = System.currentTimeMillis()
					+ file.getOriginalFilename();

			request.setAttribute("imageUrl", "/images/" + newFileName);
			try {
				File parent = new File(uploadPath, "images");
				System.err.println("mkdir=" + parent.mkdirs());
				file.transferTo(new File(parent, newFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		User user = new User(null, username, nickname, password, role, email,
				null, new Date(), 0);
		// try {
		// userService.regist(user);
		// request.setAttribute("msg", "注册成功");
		// } catch (Exception e) {
		// e.printStackTrace();
		// request.setAttribute("msg", e.getMessage());
		// }
		return "showRegist.do";
	}

	// @RequestMapping("/ajaxRegist")
	// @ResponseBody
	// public String ajaxRegist(HttpServletRequest request, HttpServletResponse
	// response, CommonsMultipartFile file) {
	// UserService userService = (UserService)
	// ObjectFactory.getObject("userService");
	// String username = request.getParameter("username");
	// String nickname = request.getParameter("nickname");
	// String password = request.getParameter("password");
	// String role = request.getParameter("role");
	// String email = request.getParameter("email");
	// String msg = null;
	// User user = new User(null, username, nickname, password, role, email,
	// null, new Date(), 0);
	// System.out.println(user);
	// System.out.println("file:" + file);
	// if(!file.isEmpty()){//判断文件是否不存在
	// String uploadPath = CommonUtil.getUploadPath();
	// String contextPath = CommonUtil.getContextPath();
	// System.out.println("uploadPath=" + uploadPath);
	// String newFileName = System.currentTimeMillis() +
	// file.getOriginalFilename();
	// request.setAttribute("imageUrl", "/images/" + newFileName);
	// try {
	// File parent = new File(uploadPath, "images");
	// System.err.println("mkdir=" + parent.mkdirs());
	// file.transferTo(new File(parent, newFileName));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// try {
	// userService.regist(user);
	// msg = "注册成功";
	// } catch (Exception e) {
	// e.printStackTrace();
	// msg = e.getMessage();
	// }
	// return msg;
	// }
	//

	@RequestMapping("/ajaxRegist")
	@ResponseBody
	public String ajaxRegist(HttpServletRequest request,
			HttpServletResponse response, CommonsMultipartFile file) {
		UserService userService = (UserService) ObjectFactory
				.getObject("userService");
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String msg = null;
		User user = new User(null, username, nickname, password, role, email,
				null, new Date(), 0);
		System.out.println(user);
		System.out.println("file:" + file);
		if (!file.isEmpty()) {// 判断文件是否不存在
			String uploadPath = CommonUtil.getUploadPath();
			String contextPath = CommonUtil.getContextPath();
			System.out.println("uploadPath=" + uploadPath);
			String newFileName = System.currentTimeMillis()
					+ file.getOriginalFilename();
			request.setAttribute("imageUrl", "/images/" + newFileName);
			try {
				File parent = new File(uploadPath, "images");
				System.err.println("mkdir=" + parent.mkdirs());
				file.transferTo(new File(parent, newFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			userService.regist(user);
			msg = "注册成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	@RequestMapping("/findUser")
	@ResponseBody
	public Object findUserById(HttpServletRequest request,
			HttpServletResponse response) {
		UserService userService = (UserService) ObjectFactory
				.getObject("userService");
		String idStr = request.getParameter("id");
		try {
			// 有id
			int id = Integer.parseInt(idStr);
			User user = userService.findUserById(id);
			return user;
		} catch (NumberFormatException e) {

		}
		// 无id
		String username = request.getParameter("username");
		if (null == username) {
			username = "";
		}
		String nickname = request.getParameter("nickname");
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String flagStr = request.getParameter("flag");
		Integer flag = null;
		try {
			flag = Integer.parseInt(flagStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user = new User(null, username, nickname, null, role, email, null,
				new Date(), flag);
		List<User> list = userService.findUserByFuzzy(user);
		return list;
	}

	@RequestMapping("/findAllUserByAjax")
	@ResponseBody
	public PageInfo<User> findAllUserByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		UserService userService = (UserService) ObjectFactory
				.getObject("userService");
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		User user = new User();
		String idStr = request.getParameter("userId");
		Integer id = null;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
		}
		String createDateStr = request.getParameter("createDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(createDateStr);
		} catch (Exception e) {
		}
		user.setId(id);
		user.setCreateDate(date);
		PageInfo<User> pageInfo = userService.findAllUser(pageNo, user);
		List<User> list = pageInfo.getList();
		for (User u : list) {
			System.out.println("id=" + u.getId() + ",createDate="
					+ sdf.format(u.getCreateDate()));
		}
		return pageInfo;
	}

	@ResponseBody
	@RequestMapping("/modifyUser")
	public ResponseResult modifyUser(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		try {
			userService.modifyUser(id, nickname, role, password, email);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
		} catch (NicknameEmptyException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		} catch (RoleEmptyException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		} catch (PasswordEmptyException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		} catch (EmailEmptyException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		} catch (EmailErrorException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		} catch (NicknameExistsException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		} catch (NicenameException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
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
			User u = userService.findById(Integer.parseInt(id));
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
			result.setReturnObject(u);
		} catch (NumberFormatException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage("服务器内部异常");
		}

		return result;
	}

	@ResponseBody
	@RequestMapping("/modifyStatus")
	public ResponseResult modifyStatus(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		String id = request.getParameter("id");
		try {
			userService.modifyStatus(id);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
		} catch (RequestParameterException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/searchUser")
	public ResponseResult searchUser(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String createBegin = request.getParameter("createBegin");
		String createEnd = request.getParameter("createEnd");
		String loginBegin = request.getParameter("loginBegin");
		String loginEnd = request.getParameter("loginEnd");
		try {
			userService.searchUser(email, createBegin, createEnd, loginBegin,
					loginEnd, session);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
			request.getSession().setAttribute("umusername",
					ParameterUtil.escape(request.getParameter("username")));
			request.getSession().setAttribute("umnickname",
					ParameterUtil.escape(request.getParameter("nickname")));
			request.getSession().setAttribute("umemail",
					ParameterUtil.escape(request.getParameter("email")));
			request.getSession().setAttribute("umrole",
					request.getParameter("role"));
		} catch (EmailErrorException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		} catch (DateErrorException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		} catch (ParseException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		} catch (DateMistakeException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			result.setMessage(e.getMessage());
		}
		return result;

	}

	@RequestMapping("/findAllUser")
	public String findAllUser(HttpServletRequest request,
			HttpServletResponse response) {
		UserService userService = (UserService) ObjectFactory
				.getObject("userService");
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageInfo<User> pageInfo = userService.findAllUser(pageNo, new User());
		request.setAttribute("userList", pageInfo);
		return "home";
	}

	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		UserService userService = (UserService) ObjectFactory
				.getObject("userService");
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageInfo<User> pageInfo = userService.findAll(pageNo);
		request.getSession().setAttribute("findusers", pageInfo);
		String src = "/user/findAll.do?pageNo=";
		request.getSession().setAttribute("usersrc", src);
		return "redirect:/back_userSet.do";
	}

	@RequestMapping("/showSelect")
	public String showSelect(HttpServletRequest request,
			HttpServletResponse response) {
		UserService userService = (UserService) ObjectFactory
				.getObject("userService");
		String pageNoStr = request.getParameter("pageNo");
		UserMessage um = new UserMessage();
		um.setUsername((String) request.getSession().getAttribute("umusername"));
		um.setNickname((String) request.getSession().getAttribute("umnickname"));
		um.setEmail((String) request.getSession().getAttribute("umemail"));
		um.setRole((String) request.getSession().getAttribute("umrole"));
		um.setCreateBegin((Date) request.getSession().getAttribute(
				"umcreateBegin"));
		um.setCreateEnd((Date) request.getSession().getAttribute("umcreateEnd"));
		um.setLoginBegin((Date) request.getSession().getAttribute(
				"umloginBegin"));
		um.setLoginEnd((Date) request.getSession().getAttribute("umloginEnd"));
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		PageInfo<User> pageInfo = userService.showSelect(pageNo, um);
		if (pageInfo.getSize() == 0) {
			pageInfo = userService.showSelect(pageNo - 1, um);
		}
		request.getSession().setAttribute("findusers", pageInfo);
		String src = "/user/showSelect.do?pageNo=";
		request.getSession().setAttribute("usersrc", src);
		return "redirect:/back_userSet.do";
	}

	@RequestMapping("/getCodeImage")
	public void getCodeImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String numStr = request.getParameter("num");
		MyImage m = null;
		if (null == numStr) {
			numStr = "0";
		}
		int num = 0;
		try {
			num = Integer.parseInt(numStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (num) {
		case 0:
			String codeStr = request.getParameter("codeStr");
			if (null == codeStr) {
				codeStr = CommonUtil.randomCode();
			}
			// 用自己的字符串来生成图片
			m = CommonUtil.getImage(null, codeStr, true);
			break;
		case 1:
			// 使用中文验证码,有干扰线
			m = CommonUtil.getImage(null, 4, true, true);
			break;
		case 2:
			// 使用中文验证码,没有干扰线
			m = CommonUtil.getImage(null, 4, true, false);
			break;
		case 3:
			// 使用数字和字母(4个)验证码,有干扰线
			m = CommonUtil.getImage(null, 4, false, true);
			break;
		case 4:
			// 使用数字和字母(4个)验证码,没有干扰线
			m = CommonUtil.getImage(null, 4, false, false);
			break;
		default:
			// 使用数字和字母验证码,有干扰线
			m = CommonUtil.getImage(null, 4, false, true);
			break;
		}
		System.out.println("code=" + m.getCode());
		request.getSession().setAttribute("code", m.getCode());
		ServletOutputStream out = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(m.getImage());
	}

	@RequestMapping("/getCodeStr")
	@ResponseBody
	public AjaxResult getCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AjaxResult ar = new AjaxResult();

		String code = (String) request.getSession().getAttribute("code");
		ar.setSuccess(true);
		ar.setMsg("获取code成功");
		ar.setObj(code);
		return ar;
	}

	public static void main(String[] args) {
		User bean = new User();
		Map<Object, Object> properties = new HashMap<Object, Object>();
		// properties.put("username", "xxxxx");
		// properties.put("nickname", "hhhhhhhhhhhh");
		// properties.put("createDate", "2018-05-21 10:10:10");
		// properties.put("id", "222222");
		// properties.put("type.id", "2466666");

		try {
			DateConverter dc = new DateConverter();
			dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
			String[] patterns = dc.getPatterns();
			System.out.println(Arrays.toString(patterns));
			ConvertUtils.register(dc, Date.class);

			// ConvertUtils.register(new Converter() {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// @Override
			// public Object convert(Class c, Object obj) {
			// System.out.println("Class=" + c);
			// Date d = null;
			// if(null != obj){
			// try {
			// System.out.println(obj);
			// d = sdf.parse(obj.toString());
			// } catch (ParseException e) {
			// e.printStackTrace();
			// }
			// }
			// return d;
			// }
			// }, Date.class);

			BeanUtils.populate(bean, properties);
			System.out.println(bean);
			// System.out.println(bean.getType().getId());

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
