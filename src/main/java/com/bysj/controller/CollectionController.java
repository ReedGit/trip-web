package com.bysj.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bysj.dto.TravelDto;
import com.bysj.model.Collection;
import com.bysj.model.PageBean;
import com.bysj.service.CollectionService;
import com.bysj.utils.Constants;

/**
 * 
 * <p>
 * Title: CollectionController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * 
 * @author zerolu
 * @date 2016年4月14日
 * @version 1.0
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {

	@Resource(name = "collectionService")
	private CollectionService collectionService;

	@RequestMapping(value = "/isCollect", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject isCollected(@RequestParam Map<String, Object> map) {
		JSONObject result = new JSONObject();
		long travelId = 0;
		if (map.containsKey("travelId"))
			travelId = Long.parseLong(map.get("travelId").toString());
		long userId = 0;
		if (map.containsKey("userId"))
			userId = Long.parseLong(map.get("userId").toString());
		boolean isCollect = collectionService.isCollectedByUser(travelId,
				userId);
		if (isCollect) {
			result.put("status", "0");
			result.put("msg", "");
			result.put("data", Constants.IS_COLLECTED);
		} else {
			result.put("status", "0");
			result.put("msg", "");
			result.put("data", Constants.NOT_COLLECTED);
		}
		return result;
	}

	@RequestMapping(value = "/travels", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getTravels(@RequestParam Map<String, Object> map) {
		JSONObject result = new JSONObject();
		long userId = 0;
		if (map.containsKey("userId"))
			userId = Long.parseLong(map.get("userId").toString());
		int page = 1;
		if (map.containsKey("page"))
			page = Integer.parseInt(map.get("page").toString());
		PageBean<TravelDto> travels = collectionService.findCollectionByUser(
				userId, page, Constants.PAGE_SIZE);
		result.put("status", "0");
		result.put("size", travels.getTotal());
		result.put("msg", "");
		result.put("data", travels.getList());
		return result;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getUsers(@RequestParam Map<String, Object> map) {
		JSONObject result = new JSONObject();
		long travelId = 0;
		if (map.containsKey("travelId"))
			travelId = Long.parseLong(map.get("travelId").toString());
		int total = collectionService.getTotalByTravel(travelId);
		result.put("status", "0");
		result.put("size", total);
		result.put("msg", "");
		return result;
	}

	@RequestMapping(value = "/collect", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject collect(@RequestParam Map<String, Object> map) {
		JSONObject result = new JSONObject();
		long travelId = 0;
		if (map.containsKey("travelId"))
			travelId = Long.parseLong(map.get("travelId").toString());
		long userId = 0;
		if (map.containsKey("userId"))
			userId = Long.parseLong(map.get("userId").toString());
		Collection collection = new Collection();
		collection.setTravelId(travelId);
		collection.setUserId(userId);
		collectionService.saveCollection(collection);
		result.put("status", "0");
		result.put("msg", "收藏成功！");
		return result;
	}

	/**
	 * 取消收藏
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject cancel(@RequestParam Map<String, Object> map) {
		JSONObject result = new JSONObject();
		long travelId = 0;
		if (map.containsKey("travelId"))
			travelId = Long.parseLong(map.get("travelId").toString());
		long userId = 0;
		if (map.containsKey("userId"))
			userId = Long.parseLong(map.get("userId").toString());
		Collection collection = new Collection();
		collection.setTravelId(travelId);
		collection.setUserId(userId);
		boolean bool = collectionService.deleteCollection(collection);
		if (bool) {
			result.put("status", "0");
			result.put("msg", "取消收藏成功！");
		}
		return result;
	}
}
