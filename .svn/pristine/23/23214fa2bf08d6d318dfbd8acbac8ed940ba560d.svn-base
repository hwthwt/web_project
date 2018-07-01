package com.itany.netClass.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.itany.netClass.entity.CourseType;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.UserMessage;
import com.itany.netClass.exception.DataAccessException;

/**
 * 用户数据访问对象
 */
public interface UserDao {
	/**
	 * 根据用户名查询一个用户
	 * @param username 用户名
	 * @return 封装了用户的信息的用户对象，如果没找到记录，返回null
	 * @throws DataAccessException 数据访问失败异常，当此持久化操作未成功时，抛出此异常
	 */
	public User selectByUsername(String username);
	
	/**
	 * 保存一个用户
	 * @param user 封装了用户的信息的用户对象
	 */
	public void insertOracle(User user);
	
	/**
	 * 保存一个用户
	 * @param user 封装了用户的信息的用户对象
	 */
	public void insertMysql(User user);
	
	public User selectByUsernameAndPassword(User user);
	
	public List<User> selectAll(User user);
	
	public List<User> selectByFuzzy(User user);
	
	public List<CourseType> selectTypes();

	public List<User> selectUserByName(String username);

	public List<User> selectAllUser();

	public User selectByUserId(int id);

	public void updateStatus(int parseInt, int status);

	public User selectByUserNickname(String id, String nickname);

	public void updateUser(String id, String nickname, String role,
			String password, String email);

	public List<User> selectSearch(UserMessage um);

	
//	List<User> selectByCondition(@Param("name") String a, @Param("password") String b, @Param("nickname") String c, @Param("loginName") String d);
}
