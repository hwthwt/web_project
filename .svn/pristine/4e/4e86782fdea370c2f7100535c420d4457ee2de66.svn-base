package com.itany.netClass.entity;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5193848930832456976L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 角色
	 */
	private String role;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 最近登录日期
	 */
	private Date loginDate;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 状态
	 * @see Constant
	 */
	private Integer flag;  //判断是否签到成功 1为成功,0为不成功
	
	private String createDateStr;
	private String loginDateStr;
	
	private int allPoint;
	private int allGold;
	
	public int getAllPoint() {
		return allPoint;
	}

	public void setAllPoint(int allPoint) {
		this.allPoint = allPoint;
	}

	public int getAllGold() {
		return allGold;
	}

	public void setAllGold(int allGold) {
		this.allGold = allGold;
	}

	public User() {
		super();
	}

	public User(Integer id, String username, String nickname, String password, String role, String email,
			Date loginDate, Date createDate, Integer flag) {
		super();
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.role = role;
		this.email = email;
		this.loginDate = loginDate;
		this.createDate = createDate;
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getLoginDateStr() {
		return loginDateStr;
	}

	public void setLoginDateStr(String loginDateStr) {
		this.loginDateStr = loginDateStr;
	}
	
	private CourseType type = new CourseType();
	
	public CourseType getType() {
		return type;
	}

	public void setType(CourseType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nickname=" + nickname + ", password=" + password
				+ ", role=" + role + ", email=" + email + ", loginDate=" + loginDate + ", createDate=" + createDate
				+ ", flag=" + flag + "]";
	}
	
}
