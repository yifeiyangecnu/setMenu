package com.yangyifei.wechat.domain;

public class ViewButton extends	SingleButton {
	public ViewButton(String name)
	{
		this.name=name;
		this.type=ButtonType.VIEW;
	}
	String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
