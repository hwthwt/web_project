package com.itany.netClass.entity;

public class Course {
	private int id ;
	private String course_name ;
	private  String course_info ;
	private   String author ;
	private  String cover_image_url;
	private  String create_date ;
	private   int click_number ;
	private  int status ;
	private  int recommendation_grade ;
	private int course_type_id;
	@Override
	public String toString() {
		return "Course [id=" + id + ", course_name=" + course_name
				+ ", course_info=" + course_info + ", author=" + author
				+ ", cover_image_url=" + cover_image_url + ", create_date="
				+ create_date + ", click_number=" + click_number + ", status="
				+ status + ", recommendation_grade=" + recommendation_grade
				+ ", course_type_id=" + course_type_id + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_info() {
		return course_info;
	}
	public void setCourse_info(String course_info) {
		this.course_info = course_info;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCover_image_url() {
		return cover_image_url;
	}
	public void setCover_image_url(String cover_image_url) {
		this.cover_image_url = cover_image_url;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public int getClick_number() {
		return click_number;
	}
	public void setClick_number(int click_number) {
		this.click_number = click_number;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRecommendation_grade() {
		return recommendation_grade;
	}
	public void setRecommendation_grade(int recommendation_grade) {
		this.recommendation_grade = recommendation_grade;
	}
	public int getCourse_type_id() {
		return course_type_id;
	}
	public void setCourse_type_id(int course_type_id) {
		this.course_type_id = course_type_id;
	}

}
