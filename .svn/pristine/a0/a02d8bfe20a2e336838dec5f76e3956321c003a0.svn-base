package com.itany.netClass.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseType implements Serializable {

	private static final long serialVersionUID = 5626612381965666185L;
	private Integer id;
	private String typeName;
	private Integer parentId;
	private CourseType parent;
	private Integer status;
	private List<CourseType> children = new ArrayList<CourseType>();

	public CourseType(Integer id, String typeName, Integer parentId,
			CourseType parent, Integer status, List<CourseType> children) {
		this.id = id;
		this.typeName = typeName;
		this.parentId = parentId;
		this.parent = parent;
		this.status = status;
		this.children = children;
	}

	public CourseType() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public CourseType getParent() {
		return parent;
	}

	public void setParent(CourseType parent) {
		this.parent = parent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<CourseType> getChildren() {
		return children;
	}

	public void setChildren(List<CourseType> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "CourseType [id=" + id + ", typeName=" + typeName
				+ ", parentId=" + parentId + ", parent=" + parent + ", status="
				+ status + ", children=" + children + "]";
	}

}
