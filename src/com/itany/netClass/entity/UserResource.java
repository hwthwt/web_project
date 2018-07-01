package com.itany.netClass.entity;

import java.io.Serializable;
import java.util.Date;

public class UserResource implements Serializable {

	private int id;
	private User user;
	private Resource resource;
	private Date createDate;
	private Date updateDate;
	private String createDateStr;
	private String updateDateYear;
	private String updateDateDay;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getUpdateDateYear() {
		return updateDateYear;
	}

	public void setUpdateDateYear(String updateDateYear) {
		this.updateDateYear = updateDateYear;
	}

	public String getUpdateDateDay() {
		return updateDateDay;
	}

	public void setUpdateDateDay(String updateDateDay) {
		this.updateDateDay = updateDateDay;
	}


}
