package com.itany.netClass.entity;

import java.io.Serializable;
import java.util.Date;

public class Resource implements Serializable {
	private Integer id;
	private String  title;
	private String  path;
	private String  coverImageUrl;
	private String  originalName;
	private Integer fileSize;
	private String  fileType;
	private Integer clickCount;
	private Date createDateD;
	private Date beginTime;
	private Date endTime;
	private String nickname;
	private String createDateYear;
	private String createDateDay;
	
	public String getCreateDateYear() {
		return createDateYear;
	}
	public void setCreateDateYear(String updateDateYear) {
		this.createDateYear = updateDateYear;
	}
	public String getCreateDateDay() {
		return createDateDay;
	}
	public void setCreateDateDay(String updateDateDay) {
		this.createDateDay = updateDateDay;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateDateD() {
		return createDateD;
	}
	public void setCreateDateD(Date createDateD) {
		this.createDateD = createDateD;
	}
	private String createDate;
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	private Integer costType; 
	private Integer costNumber;
	private Integer userId;
	private Integer chapterId;
	private User user;
	private Chapter chapter;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	private Integer status;
	 
	
	public Integer getCostType() {
		return costType;
	}
	public void setCostType(Integer costType) {
		this.costType = costType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCoverImageUrl() {
		return coverImageUrl;
	}
	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Integer getClickCount() {
		return clickCount;
	}
	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getCostNumber() {
		return costNumber;
	}
	public void setCostNumber(Integer costNumber) {
		this.costNumber = costNumber;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Resource [id=" + id + ", title=" + title + ", path=" + path
				+ ", coverImageUrl=" + coverImageUrl + ", originalName="
				+ originalName + ", fileSize=" + fileSize + ", fileType="
				+ fileType + ", clickCount=" + clickCount + ", createDate="
				+ createDate + ", costType=" + costType + ", costNumber="
				+ costNumber + ", userId=" + userId + ", chapterId="
				+ chapterId + ", status=" + status + "]";
	}
	
	
}