package com.bysj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bysj.dao.ContentDao;
import com.bysj.dao.ContentImageDao;
import com.bysj.dao.TravelDao;
import com.bysj.dao.UserDao;
import com.bysj.dto.ContentDto;
import com.bysj.dto.TravelDto;
import com.bysj.model.Content;
import com.bysj.model.ContentImage;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;
import com.bysj.model.User;
import com.bysj.service.TravelService;

@Service(value = "travelService")
@Transactional
public class TravelServiceImpl implements TravelService {

	@Resource(name = "travelDao")
	private TravelDao travelDao;

	@Resource(name = "contentDao")
	private ContentDao contentDao;

	@Resource(name = "contentImageDao")
	private ContentImageDao contentImageDao;

	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public PageBean<TravelDto> findAllTravel(int page, int size) {
		PageBean<Travel> travels = travelDao.findAllByPage(page, size);
		PageBean<TravelDto> pageBean = new PageBean<>(page, size);
		List<TravelDto> travelDtos = new ArrayList<TravelDto>();
		for (Travel travel : travels.getList()) {
			User user = userDao.findById(travel.getUserId());
			TravelDto travelDto = new TravelDto(travel);
			travelDto.setUser(user);
			travelDtos.add(travelDto);
		}
		pageBean.setTotal(travels.getTotal());
		pageBean.setList(travelDtos);
		return pageBean;
	}

	@Override
	public PageBean<TravelDto> findByArea(int page, int size, String... objects) {
		PageBean<Travel> travels = travelDao.findByArea(page, size, objects);
		PageBean<TravelDto> pageBean = new PageBean<>(page, size);
		List<TravelDto> travelDtos = new ArrayList<TravelDto>();
		for (Travel travel : travels.getList()) {
			User user = userDao.findById(travel.getUserId());
			TravelDto travelDto = new TravelDto(travel);
			travelDto.setUser(user);
			travelDtos.add(travelDto);
		}
		pageBean.setTotal(travels.getTotal());
		pageBean.setList(travelDtos);
		return pageBean;
	}

	@Override
	public PageBean<TravelDto> findByTitle(int page, int size,
			String... objects) {
		PageBean<Travel> travels = travelDao.findByTitle(page, size, objects);
		PageBean<TravelDto> pageBean = new PageBean<>(page, size);
		List<TravelDto> travelDtos = new ArrayList<TravelDto>();
		for (Travel travel : travels.getList()) {
			User user = userDao.findById(travel.getUserId());
			TravelDto travelDto = new TravelDto(travel);
			travelDto.setUser(user);
			travelDtos.add(travelDto);
		}
		pageBean.setTotal(travels.getTotal());
		pageBean.setList(travelDtos);
		return pageBean;
	}

	@Override
	public Travel saveTravel(Travel travel) {
		return travelDao.save(travel);
	}

	@Override
	public Travel findByTitle(String title) {
		return travelDao.findByTitle(title);
	}

	@Override
	public boolean deleteTravel(long id) {
		return travelDao.deleteTravel(id);
	}

	@Override
	public List<ContentDto> detail(long travelId) {
		List<ContentDto> contentDtos = new ArrayList<>();
		List<Content> contents = contentDao.findByTravelId(travelId);
		if (contents != null) {
			for (Content content : contents) {
				ContentDto contentDto = new ContentDto();
				contentDto.setContent(content);
				List<ContentImage> contentImages = contentImageDao
						.findByContentId(content.getContentId());
				if (contentImages != null) {
					List<String> images = new ArrayList<>();
					for (ContentImage contentImage : contentImages) {
						images.add(contentImage.getImageUrl());
					}
					contentDto.setImageurl(images);
				}
				contentDtos.add(contentDto);
			}
		}
		return contentDtos;
	}

	@Override
	public PageBean<TravelDto> findByUserId(long userId, int page, int size) {
		PageBean<TravelDto> pageBean = new PageBean<>(page, size);
		List<TravelDto> travelDtos = new ArrayList<>();
		PageBean<Travel> travels = travelDao.findByUser(page, size, userId);
		for (Travel travel : travels.getList()) {
			User user = userDao.findById(travel.getUserId());
			TravelDto travelDto = new TravelDto(travel);
			travelDto.setUser(user);
			travelDtos.add(travelDto);
		}
		pageBean.setTotal(travels.getTotal());
		pageBean.setList(travelDtos);
		return pageBean;
	}

	@Override
	public void updateTravel(Travel travel) {
		travelDao.update(travel);
		
	}

	@Override
	public Travel findById(long id) {
		return travelDao.findById(id);
	}

}
