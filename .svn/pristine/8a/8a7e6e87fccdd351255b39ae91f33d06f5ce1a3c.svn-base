package com.itany.netClass.service.proxy;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.UserMessage;
import com.itany.netClass.exception.CodeEmptyException;
import com.itany.netClass.exception.CodeErrorException;
import com.itany.netClass.exception.DataAccessException;
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
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.transaction.TransactionManager;

public class UserServiceProxy implements UserService {

	private TransactionManager tran;
	private UserService userService;

	public void regist(User user) throws DuplicateUsernameException {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		try {
			tran.beginTransaction();
			userService.regist(user);
			tran.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			tran.rollback();
			throw new ServiceException(e);
		} catch (DuplicateUsernameException e) {
			tran.rollback();
			throw e;
		}

	}

	public User login(String username, String password)
			throws UserNotFoundException {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		User user = null;

		try {
			tran.beginTransaction();
			user = userService.login(username, password);
			tran.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			tran.rollback();
			throw new ServiceException(e);
		} catch (UserNotFoundException e) {
			tran.rollback();
			throw e;
		}
		return user;
	}

	public PageInfo<User> findAllUser(int pageNo, User user) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		PageInfo<User> page = null;
		try {
			tran.beginTransaction();
			page = userService.findAllUser(pageNo, user);
			tran.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			tran.rollback();
			throw new ServiceException(e);
		}
		return page;
	}

	@Override
	public User findUserById(Integer id) {
		try {
			tran.beginTransaction();
			User user = userService.findUserById(id);
			tran.commit();
			return user;
		} catch (DataAccessException e) {
			e.printStackTrace();
			tran.rollback();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> findUserByFuzzy(User user) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		try {
			tran.beginTransaction();
			List<User> list = userService.findUserByFuzzy(user);
			tran.commit();
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
			tran.rollback();
			throw new ServiceException(e);
		}
	}

	@Override
	public User backLogin(String loginName, String password, String code,
			HttpSession session) throws CodeErrorException,
			LoginNameEmptyException, PasswordEmptyException,
			CodeEmptyException, UserNoExistException, PasswordErrorException,
			RoleErrorException, StatusErrorException {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		tran.beginTransaction();
		try {
			User u = userService.backLogin(loginName, password, code, session);
			tran.commit();
			return u;
		} catch (CodeErrorException e) {
			tran.rollback();
			throw e;
		} catch (LoginNameEmptyException e) {
			tran.rollback();
			throw e;
		} catch (PasswordEmptyException e) {
			tran.rollback();
			throw e;
		} catch (CodeEmptyException e) {
			tran.rollback();
			throw e;
		} catch (UserNoExistException e) {
			tran.rollback();
			throw e;
		} catch (PasswordErrorException e) {
			tran.rollback();
			throw e;
		} catch (RoleErrorException e) {
			tran.rollback();
			throw e;
		} catch (StatusErrorException e) {
			tran.rollback();
			throw e;
		}
	}

	@Override
	public PageInfo<User> findAll(int pageNo) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		PageInfo<User> page = null;
		try {
			tran.beginTransaction();
			page = userService.findAll(pageNo);
			tran.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			tran.rollback();
			throw new ServiceException(e);
		}
		return page;
	}

	@Override
	public void modifyStatus(String id) throws RequestParameterException {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		tran.beginTransaction();
		try {
			userService.modifyStatus(id);
			tran.commit();
		} catch (RequestParameterException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		}
	}

	@Override
	public User findById(int id) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		tran.beginTransaction();
		User u = userService.findById(id);
		tran.commit();
		return u;
	}

	@Override
	public void modifyUser(String id, String nickname, String role,
			String password, String email) throws NicknameEmptyException,
			RoleEmptyException, PasswordEmptyException, EmailEmptyException,
			EmailErrorException, NicknameExistsException,NicenameException {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		tran.beginTransaction();
		try {
			userService.modifyUser(id, nickname, role, password, email);
			tran.commit();
		} catch (NicknameEmptyException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} catch (RoleEmptyException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} catch (PasswordEmptyException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} catch (EmailEmptyException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} catch (EmailErrorException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} catch (NicknameExistsException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} catch (NicenameException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		}

	}

	@Override
	public void searchUser(String email, String createBegin, String createEnd,
			String loginBegin, String loginEnd,HttpSession session) throws EmailErrorException,
			DateErrorException, ParseException, DateMistakeException {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		tran.beginTransaction();
		try {
			userService.searchUser(email, createBegin, createEnd, loginBegin,
					loginEnd,session);
			tran.commit();
		} catch (EmailErrorException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} catch (DateErrorException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} catch (DateMistakeException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		}

	}

	@Override
	public PageInfo<User> showSelect(int pageNo, UserMessage um) {
		tran = (TransactionManager) ObjectFactory.getObject("transaction");
		userService = (UserService) ObjectFactory
				.getObject("userServiceTarget");
		tran.beginTransaction();
		PageInfo<User> page = userService.showSelect(pageNo, um);
		tran.commit();
		return page;
	}

}
