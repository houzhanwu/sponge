package com.apr7.sponge.constants;

public enum CompanyStatusEnum {

	NORMAL(1, "正常"),
	OFFLINE(3, "离线"),
	NOT_INSTALLED(5, "未装");

	private int code;
	private String title;

	public int getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	private CompanyStatusEnum(int code, String title) {
		this.code = code;
		this.title = title;
	}
}
