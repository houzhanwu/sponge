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

	public void addArea(Area area) {
		areaDao.addArea(area);
	}

	public void deleteArea(Long areaId) {
		areaDao.deleteArea(areaId);
	}

	public void updateArea(Area area) {
		areaDao.updateArea(area);
	}

	public List<Area> listAllArea() {
		return areaDao.listAllArea();
	}
}
