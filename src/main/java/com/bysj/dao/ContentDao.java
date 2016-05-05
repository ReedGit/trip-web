package com.bysj.dao;

import java.util.List;

import com.bysj.model.Content;

public interface ContentDao extends BaseDao<Content> {

	public boolean deleteContentById(List<Long> contentIds);

	public boolean deleteContentImageById(List<Long> contentIds);

	public List<Content> findByTravelId(long travelId);

}
