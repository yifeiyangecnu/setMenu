package com.yangyifei.wechat.domain;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	List<Button>	button=new ArrayList<Button>();

	public List<Button> getButton() {
		return button;
	}

	public void setButton(List<Button> button) {
		this.button = button;
	}
	
}
