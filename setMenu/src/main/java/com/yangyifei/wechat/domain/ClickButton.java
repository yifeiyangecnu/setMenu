package com.yangyifei.wechat.domain;

public class ClickButton extends SingleButton {
	public ClickButton(String name)
	{
		this.name=name;
		this.type=ButtonType.CLICK;
	}
	/**
	 * �˵�KEYֵ��������Ϣ�ӿ����ͣ�������128�ֽ�
	 */
	String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
