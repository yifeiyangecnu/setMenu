package com.yangyifei.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ConnectUtil {
	public static InputStream postRequest(String url) throws ClientProtocolException, IOException
	{
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        CloseableHttpClient httpClient = httpClientBuilder.build(); 
		HttpPost post=new HttpPost(urlEncode(url));
		return httpClient.execute(post).getEntity().getContent();
	}
	public static InputStream getRequest(String url) throws UnsupportedOperationException, ClientProtocolException, IOException
	{
		HttpClientBuilder httpClientBuilder=HttpClientBuilder.create();
		 CloseableHttpClient httpClient = httpClientBuilder.build(); 
		 HttpGet get=new HttpGet(urlEncode(url));
		 return httpClient.execute(get).getEntity().getContent();
	}
	
	private static String urlEncode(String url) throws UnsupportedEncodingException
	{
		int pos=url.indexOf('?');
		if(pos>0)
		{
			return url.substring(0,pos)+URLEncoder.encode(url.substring(pos),"UTF-8");
		}
		return url;
	}
}
