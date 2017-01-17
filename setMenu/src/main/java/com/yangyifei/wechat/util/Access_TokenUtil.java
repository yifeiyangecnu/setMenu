package com.yangyifei.wechat.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangyifei.wechat.Exception.MyException;

public class Access_TokenUtil {
	private  static String TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";
	private static final String LOCAL_TOKEN="token.txt";
	private static int EXPIRES_IN=7200;
	public static String getToken(String appId, String appSecret) throws JsonProcessingException, UnsupportedOperationException, ClientProtocolException, IOException, MyException
	{
		TOKEN_URL+="appid="+appId+"&secret="+appSecret;
		if(!isTokenVailed())
		{
			 ObjectMapper objectMapper=new ObjectMapper();
			 JsonNode response = objectMapper.readTree(ConnectUtil.getRequest(TOKEN_URL	));
			 if(response.has("errcode"))
			 {
				 throw new MyException(response.get("errmsg").asText());
			 }
			 String token = response.get("access_token").asText();
			 updateLocalToken(token);
			 System.out.println("token 来自远程");
			return token;
		}
		System.out.println("token 来自本地");
		return getTokenFormLocal() ;
	}
	
	private static void updateLocalToken(String token) throws IOException
	{
		File tokenFile=new File(LOCAL_TOKEN);
		FileWriter fileWriter=new FileWriter(tokenFile);
		Date now=new Date();
		fileWriter.write(token+"\t"+String.valueOf(now.getTime()));
		fileWriter.close();
	}
	private static String getTokenFormLocal() throws FileNotFoundException, MyException
	{
		File tokenFile=new File(LOCAL_TOKEN);
		Scanner scanner=new Scanner(tokenFile);
		String token="";
		if(scanner.hasNext())
			token=scanner.next();
		else
		{
			scanner.close();
			throw new MyException(LOCAL_TOKEN+"不存在token");
		}
		scanner.close();
		return token;
	}
	private static boolean isTokenVailed() throws MyException, FileNotFoundException
	{
		File tokenFile=new File(LOCAL_TOKEN);
		if(!tokenFile.exists())
		{
			return false;
		}
		Scanner scanner=new Scanner(tokenFile);
		long tiemstamp=0;
		if(scanner.hasNext());
		{
			scanner.next();
		}
		if(scanner.hasNext())
		{
			tiemstamp=Long.parseLong(scanner.next());
		}
		else
		{
			scanner.close();
			throw new MyException(LOCAL_TOKEN+"不存在timestamp");
		}
		scanner.close();
		Date now=new Date();
		return ((now.getTime()-tiemstamp)/1000)<EXPIRES_IN;
	}

	public static int getEXPIRES_IN() {
		return EXPIRES_IN;
	}

	public static void setEXPIRES_IN(int eXPIRES_IN) {
		EXPIRES_IN = eXPIRES_IN;
	}


	public static String getTokenUrl() {
		return TOKEN_URL;
	}

	public static String getLocalToken() {
		return LOCAL_TOKEN;
	}
	
	
	
}
