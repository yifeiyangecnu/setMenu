package com.yangyifei.wechat.util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.yangyifei.wechat.Exception.MyException;
import com.yangyifei.wechat.domain.ClickButton;
import com.yangyifei.wechat.domain.Menu;
import com.yangyifei.wechat.domain.MulButton;
import com.yangyifei.wechat.domain.ViewButton;

public class Main {
	public static void main(String args[]) throws ClientProtocolException, IOException, MyException
	{
		Menu menu=new Menu();
		ClickButton button1=new ClickButton("今日快讯");
		button1.setKey("1234");
		MulButton mulButton1=new MulButton("复合按钮");
		ClickButton subButton_1=new ClickButton("天气");
		subButton_1.setKey("5678");
		ViewButton subButton2=new ViewButton("百度");
		subButton2.setUrl("http://www.baidu.com");
		mulButton1.getSub_button().add(subButton_1);
		mulButton1.getSub_button().add(subButton2);
		menu.getButton().add(button1);
		menu.getButton().add(mulButton1);
		MenuUtil.setMenu(JsonUtil.getJsonStr(menu));
		System.out.println(JsonUtil.getJsonStr(menu));
	}
}
