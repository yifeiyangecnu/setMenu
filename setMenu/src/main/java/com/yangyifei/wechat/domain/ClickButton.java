package com.yangyifei.wechat.domain;

public class ClickButton extends SingleButton {
	public ClickButton(String name)
	{
		this.name=name;
		this.type=ButtonType.CLICK;
	}
	/**
	 * 菜单KEY值，用于消息接口推送，不超过128字节
	 */
	String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
