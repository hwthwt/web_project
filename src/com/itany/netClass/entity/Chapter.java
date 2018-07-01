package com.itany.netClass.entity;

public class Chapter {
	private Integer  id;
	private Integer course_id;
	private String title;
	private String info;
	private String create_date;
	private Integer status;
	private CourseSet course;
	private Resource resource;
	
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public CourseSet getCourse() {
		return course;
	}
	public void setCourse(CourseSet course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "Chapter [id=" + id + ", course_id=" + course_id + ", title="
				+ title + ", info=" + info + ", create_date=" + create_date
				+ ", status=" + status + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
