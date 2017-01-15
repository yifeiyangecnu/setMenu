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
	private static final String APPID="wxa2a5eb0e8694c896";
	private static final String APP_SECRET="";
	private static final String TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APP_SECRET;
	private static final String LOCAL_TOKEN="token.txt";
	private static int EXPIRES_IN=0;
	public static String getToken() throws JsonProcessingException, UnsupportedOperationException, ClientProtocolException, IOException, MyException
	{
		if(!isTokenVailed())
		{
			 ObjectMapper objectMapper=new ObjectMapper();
			 JsonNode response = objectMapper.readTree(ConnectUtil.getRequest(TOKEN_URL	));
			 if(response.has("errcode"))
			 {
				 throw new MyException(response.get("errmsg").asText());
			 }
			 String token = response.get("access_token").asText();
			 EXPIRES_IN=response.get("expires_in").asInt();
			 updateLocalToken(token);
			return token;
		}
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
			throw new MyException(LOCAL_TOKEN+"中没有token");
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
			throw new MyException(LOCAL_TOKEN+"中没有timestamp");
		}
		scanner.close();
		Date now=new Date();
		return ((now.getTime()-tiemstamp)/1000)<EXPIRES_IN;
	}
}
