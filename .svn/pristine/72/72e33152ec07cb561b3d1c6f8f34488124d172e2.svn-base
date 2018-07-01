package com.itany.netClass.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.UserDao;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.UserMessage;
import com.itany.netClass.exception.CodeEmptyException;
import com.itany.netClass.exception.DateErrorException;
import com.itany.netClass.exception.DateMistakeException;
import com.itany.netClass.exception.DuplicateUsernameException;
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
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.ParameterUtil;
import com.itany.netClass.exception.CodeErrorException;

public class UserServiceImpl implements UserService {

	private UserDao userDao = (UserDao) ObjectFactory.getObject("userDao");

	public User login(String username, String password)
			throws UserNotFoundException {
		// userDao=(UserDao) ObjectFactory.getObject("userDao");
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		// u.setPassword(u.getPassword());
		User user = userDao.selectByUsernameAndPassword(u);
		if (user == null) {
			throw new UserNotFoundException("用户名或密码错误");
		}
		return user;
	}

	public void regist(User user) throws DuplicateUsernameException {
		// userDao=(UserDao) ObjectFactory.getObject("userDao");
		User u = userDao.selectByUsername(user.getUsername());
		if (u != null) {
			throw new DuplicateUsernameException("用户名已存在");
		}
		UUID.randomUUID();
		user.setPassword(user.getPassword());
		user.setCreateDate(new Date());
		user.setFlag(Constant.STATUS_ENABLE);
		userDao.insertOracle(user);
		// userDao.insertMysql(user);
	}

	public void validateUsername(String username)
			throws DuplicateUsernameException {
		// userDao=(UserDao) ObjectFactory.getObject("userDao");
		User user = userDao.selectByUsername(username);
		if (user != null) {
			throw new DuplicateUsernameException("用户名已存在");
		}
	}

	public PageInfo<User> findAllUser(int pageNo, User user) {
		// userDao=(UserDao) ObjectFactory.getObject("userDao");
		// 开启分页插件的分页功能
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		// 调用service的查询数据方法
		List<User> list = userDao.selectAll(user);
		// 把获得的数据集合放入PageInfo(分页插件工具类)中,有插件进行分页等操作,并返回分页后数据的对象
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	@Override
	public User findUserById(Integer id) {
		// userDao=(UserDao) ObjectFactory.getObject("userDao");
		User user = new User();
		user.setId(id);
		user.setUsername("");
		user.setRole("");
		List<User> list = userDao.selectByFuzzy(user);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<User> findUserByFuzzy(User user) {
		// userDao=(UserDao) ObjectFactory.getObject("userDao");
		List<User> list = userDao.selectByFuzzy(user);
		return list;
	}

	public static void main(String[] args) {
		System.out.println("UUID=" + UUID.randomUUID());
	}

	@Override
	public User backLogin(String loginName, String password, String code,
			HttpSession session) throws CodeErrorException,
			LoginNameEmptyException, PasswordEmptyException,
			CodeEmptyException, UserNoExistException, PasswordErrorException,
			RoleErrorException, StatusErrorException {
		String image = (String) session.getAttribute("code");
		if (loginName == null || "".equals(loginName)) {
			throw new LoginNameEmptyException("用户名为空");
		}
		if (password == null || "".equals(password)) {
			throw new PasswordEmptyException("密码为空");
		}
		if (code == null || "".equals(code)) {
			throw new CodeEmptyException("验证码为空");
		}
		if (!image.equals(code)) {
			throw new CodeErrorException("验证码不正确");
		}
		List<User> list = new ArrayList<User>();
		list = userDao.selectUserByName(loginName);
		if (list.size() <= 0) {
			throw new UserNoExistException("用户不存在");
		}
		User u = list.get(0);
		// 密码加密后的 先取消
		//password=CommonUtil.md5(password);

		if (!u.getPassword().equals(password)) {
			throw new PasswordErrorException("密码错误");
		}
		if (!u.getRole().equals(Constant.USER_ROLE_ADMIN)) {
			throw new RoleErrorException("权限不足");
		}
		if (!u.getFlag().equals(Constant.STATUS_ENABLE)) {
			throw new StatusErrorException("该用户被禁用");
		}
		return u;
	}

	@Override
	public PageInfo<User> findAll(int pageNo) {
		// userDao=(UserDao) ObjectFactory.getObject("userDao");
		// 开启分页插件的分页功能
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		// 调用service的查询数据方法
		List<User> list = new ArrayList<User>();
		list = userDao.selectAllUser();
		// 把获得的数据集合放入PageInfo(分页插件工具类)中,有插件进行分页等操作,并返回分页后数据的对象
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	@Override
	public void modifyStatus(String id) throws RequestParameterException {
		if (ParameterUtil.isNull(id)) {
			throw new RequestParameterException("id不能为空");
		}
		int status;
		try {
			User u = userDao.selectByUserId(Integer.parseInt(id));
			if (u.getFlag() == Constant.STATUS_ENABLE) {
				status = Constant.STATUS_DISABLE;
			} else {
				status = Constant.STATUS_ENABLE;
			}
			userDao.updateStatus(Integer.parseInt(id), status);
		} catch (NumberFormatException e) {
			throw new RequestParameterException("id必须为数字");
		}

	}

	@Override
	public User findById(int id) {
		User u = new User();
		u = userDao.selectByUserId(id);
		return u;
	}

	@Override
	public void modifyUser(String id, String nickname, String role,
			String password, String email) throws NicknameEmptyException,
			RoleEmptyException, PasswordEmptyException, EmailEmptyException,
			EmailErrorException, NicknameExistsException, NicenameException {
		if (nickname == null || "".equals(nickname)) {
			throw new NicknameEmptyException("昵称不能为空");
		}
		String nn = "[0-9a-zA-Z\u4e00-\u9fa5]+";
		if (!(nickname.matches(nn))) {
			throw new NicenameException("昵称格式不对");
		}
		if ("-1".equals(role)) {
			throw new RoleEmptyException("角色不能为空");
		}
		if (password == null || "".equals(password)) {
			throw new PasswordEmptyException("密码不能为空");
		}
		if (email == null || "".equals(email)) {
			throw new EmailEmptyException("邮箱不能为空");
		}
		String reg = "[a-zA-Z]\\w*@[a-zA-Z0-9]+(\\.com)(\\.cn)?";
		if (!email.matches(reg)) {
			throw new EmailErrorException("邮箱格式有误");
		}
		User u = userDao.selectByUserNickname(id, nickname);
		if (u != null) {
			throw new NicknameExistsException("该昵称已经存在");
		}
		userDao.updateUser(id, nickname, role, password, email);
	}

	@Override
	public void searchUser(String email, String createBegin, String createEnd,
			String loginBegin, String loginEnd,HttpSession session) throws EmailErrorException,
			DateErrorException, ParseException, DateMistakeException {
		String date = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		if (!ParameterUtil.isNull(createBegin) && !createBegin.matches(date)) {
			throw new DateErrorException("日期格式有误");
		}
		if (!ParameterUtil.isNull(createEnd) && !createEnd.matches(date)) {
			throw new DateErrorException("日期格式有误");
		}
		if (!ParameterUtil.isNull(loginBegin) && !loginBegin.matches(date)) {
			throw new DateErrorException("日期格式有误");
		}
		if (!ParameterUtil.isNull(loginEnd) && !loginEnd.matches(loginEnd)) {
			throw new DateErrorException("日期格式有误");
		}
		if (!ParameterUtil.isNull(createBegin)
				&& !ParameterUtil.isNull(createEnd)
				&& new SimpleDateFormat("yyyy-MM-dd").parse(createBegin)
						.getTime() > new SimpleDateFormat("yyyy-MM-dd").parse(
						createEnd).getTime()) {
			throw new DateMistakeException("开始结束日期错误");
		}
		if (!ParameterUtil.isNull(loginBegin)
				&& !ParameterUtil.isNull(loginEnd)
				&& new SimpleDateFormat("yyyy-MM-dd").parse(loginBegin)
						.getTime() > new SimpleDateFormat("yyyy-MM-dd").parse(
						loginEnd).getTime()) {
			throw new DateMistakeException("开始结束日期错误");
		}
		if (!ParameterUtil.isNull(createBegin)) {
			session.setAttribute("umcreateBegin",
					new SimpleDateFormat("yyyy-MM-dd").parse(createBegin));
		}else{
			session.removeAttribute("umcreateBegin");
		}
		if (!ParameterUtil.isNull(createEnd)) {
			session.setAttribute("umcreateEnd",
					new SimpleDateFormat("yyyy-MM-dd").parse(createEnd));
		}else{
			session.removeAttribute("umcreateEnd");
		}
		if (!ParameterUtil.isNull(loginBegin)) {
			session.setAttribute("umloginBegin",
					new SimpleDateFormat("yyyy-MM-dd").parse(loginBegin));
		}else{
			session.removeAttribute("umloginBegin");
		}
		if (!ParameterUtil.isNull(loginEnd)) {
			session.setAttribute("umloginEnd",
					new SimpleDateFormat("yyyy-MM-dd").parse(loginEnd));
		}else{
			session.removeAttribute("umloginEnd");
		}

	}

	@Override
	public PageInfo<User> showSelect(int pageNo, UserMessage um) {
		// userDao=(UserDao) ObjectFactory.getObject("userDao");
		// 开启分页插件的分页功能
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		// 调用service的查询数据方法
		List<User> list = new ArrayList<User>();
		if ("-1".equals(um.getRole())) {
			um.setRole(null);
		}
		list = userDao.selectSearch(um);
		// 把获得的数据集合放入PageInfo(分页插件工具类)中,有插件进行分页等操作,并返回分页后数据的对象
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

}
