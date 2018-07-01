package com.itany.netClass.dao;

import java.util.List;

import com.itany.netClass.entity.Resource;

public interface ResourceDao {
	public void updateResource(Resource resource);
	
	
	public List<Resource> selectAll2();

	public Resource selectById(int ids);

	public void updateStatus(int status, int ids);

	public List<Resource> selectSearch(Resource rs);
	
}
