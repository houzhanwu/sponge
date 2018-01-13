package com.apr7.sponge.model.vo;

import java.util.List;

import com.apr7.sponge.model.Company;
import com.apr7.sponge.model.Workshop;

public class CompanyWorkshopVO extends Company {

	private List<Workshop> workshops;

	public List<Workshop> getWorkshops() {
		return workshops;
	}

	public void setWorkshops(List<Workshop> workshops) {
		this.workshops = workshops;
	}
}
