package com.yangyifei.wechat.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ButtonType {
	CLICK("click"),VIEW("view");
	String attribute;

	private ButtonType(String attribute) {
		this.attribute = attribute;
	}
	@JsonValue
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
}
