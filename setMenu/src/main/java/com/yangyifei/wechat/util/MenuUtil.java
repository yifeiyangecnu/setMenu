package com.yangyifei.wechat.util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangyifei.wechat.Exception.MyException;

public class MenuUtil {
	 private static  String ACCESS_TOKEN="123";
	 private static final String SET_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?";
	 public static boolean setMenu(String jsonPram) throws ClientProtocolException, IOException, MyException
	 {
		 ACCESS_TOKEN=Access_TokenUtil.getToken();
		 String url=SET_MENU_URL+"access_token="+ACCESS_TOKEN+"&body="+jsonPram;
		System.out.println(url);
		 ObjectMapper objectMapper=new ObjectMapper();
		 JsonNode response = objectMapper.readTree(ConnectUtil.postRequest(url));
		String errmsg=response.get("errmsg").asText();
		 int errcode=response.get("errcode").asInt();
		 if(errcode==0)
		 {
			 return true;
		 }
		 else
		 {
			 throw new MyException(errmsg);
		 }
	 }
}
