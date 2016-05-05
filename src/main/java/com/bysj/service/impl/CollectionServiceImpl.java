package com.bysj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bysj.dao.CollectionDao;
import com.bysj.dao.TravelDao;
import com.bysj.dao.UserDao;
import com.bysj.dto.TravelDto;
import com.bysj.model.Collection;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;
import com.bysj.model.User;
import com.bysj.service.CollectionService;

@Service(value = "collectionService")
@Transactional
public class CollectionServiceImpl implements CollectionService {

	@Resource(name = "collectionDao")
	private CollectionDao collectionDao;

	@Resource(name = "travelDao")
	private TravelDao travelDao;

	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public boolean isCollectedByUser(long travelId, long userId) {
		return collectionDao.isCollectedByUser(userId, travelId);
	}

	@Override
	public PageBean<TravelDto> findCollectionByUser(long userId, int page,
			int size) {
		List<Collection> collections = collectionDao.findTravelByUser(userId);
		List<Long> ids = new ArrayList<>();
		PageBean<TravelDto> pageBean = new PageBean<>(page, size);
		List<TravelDto> travelDtos = new ArrayList<TravelDto>();
		if (collections != null && collections.size() > 0) {
			for (Collection collection : collections) {
				ids.add(collection.getTravelId());
			}
			PageBean<Travel> travels = travelDao.findByPage(page, size, ids);
			for (Travel travel : travels.getList()) {
				User user = userDao.findById(travel.getUserId());
				TravelDto travelDto = new TravelDto(travel);
				travelDto.setUser(user);
				travelDtos.add(travelDto);
			}
		}
		pageBean.setTotal(collections.size());
		pageBean.setList(travelDtos);
		return pageBean;
	}

	@Override
	public int getTotalByTravel(long travelId) {
		return collectionDao.getTotalByTravel(travelId);
	}

	@Override
	public void saveCollection(Collection collection) {
		collectionDao.save(collection);
	}

	@Override
	public boolean deleteCollection(Collection collection) {
		return collectionDao.deleteCollection(collection.getTravelId(),
				collection.getUserId());
	}

}
