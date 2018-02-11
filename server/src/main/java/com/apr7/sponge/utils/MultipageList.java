package com.apr7.sponge.utils;

import java.util.List;

public class MultipageList<T> {

	private List<T> data;
	private int total;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public MultipageList() {

	}

	public MultipageList(List<T> data, int total) {
		this.data = data;
		this.total = total;
	}
}
