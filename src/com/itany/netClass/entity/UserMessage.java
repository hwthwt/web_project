package com.itany.netClass.entity;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 角色
	 */
	private String role;
	/**
	 * 邮箱
	 */
	private String email;
	private Date createBegin;
	private Date createEnd;
	private Date loginBegin;
	private Date loginEnd;
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
	public Date getCreateBegin() {
		return createBegin;
	}
	public void setCreateBegin(Date createBegin) {
		this.createBegin = createBegin;
	}
	public Date getCreateEnd() {
		return createEnd;
	}
	public void setCreateEnd(Date createEnd) {
		this.createEnd = createEnd;
	}
	public Date getLoginBegin() {
		return loginBegin;
	}
	public void setLoginBegin(Date loginBegin) {
		this.loginBegin = loginBegin;
	}
	public Date getLoginEnd() {
		return loginEnd;
	}
	public void setLoginEnd(Date loginEnd) {
		this.loginEnd = loginEnd;
	}
	
}
