package com.apr7.sponge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apr7.sponge.dao.AreaDao;
import com.apr7.sponge.model.Area;

@Service
public class AreaService {
	@Autowired
	private AreaDao areaDao;

	public List<Area> listAllArea() {
		return areaDao.listAllArea();
	}
}
