package com.itany.netClass.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.UserMessage;
import com.itany.netClass.exception.CodeEmptyException;
import com.itany.netClass.exception.CodeErrorException;
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
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.exception.StatusErrorException;
import com.itany.netClass.exception.UserNoExistException;
import com.itany.netClass.exception.UserNotFoundException;

/**
 * 用户服务对象
 */
public interface UserService {
	/**
	 * 用户注册
	 * @param user 封装了注册用户信息的用户对象
	 * @throws DuplicateUsernameException 重复的用户名异常，当用户注册的用户名在本系统中存在时，抛出此异常
	 * @throws ServiceException 服务失败异常，当此业务处理未成功时，抛出此异常
	 */
	public void regist(User user)throws DuplicateUsernameException;
	
	public User login(String username,String password)throws UserNotFoundException;
	
	public User findUserById(Integer id);
	
	public PageInfo<User> findAllUser(int pageNo, User user);
	
	public List<User> findUserByFuzzy(User user);

	public User backLogin(String loginName, String password, String code,
			HttpSession session) throws CodeErrorException, LoginNameEmptyException, PasswordEmptyException, CodeEmptyException, UserNoExistException, PasswordErrorException, RoleErrorException, StatusErrorException;

	public PageInfo<User> findAll(int pageNo);

	public void modifyStatus(String id) throws RequestParameterException;

	public User findById(int id);

	public void modifyUser(String id, String nickname, String role,
			String password, String email) throws NicknameEmptyException, RoleEmptyException, PasswordEmptyException, EmailEmptyException, EmailErrorException, NicknameExistsException, NicenameException;

	public void searchUser(String email, String createBegin, String createEnd,
			String loginBegin, String loginEnd, HttpSession session) throws EmailErrorException, DateErrorException, ParseException, DateMistakeException;

	public PageInfo<User> showSelect(int pageNo, UserMessage um);


	
}
