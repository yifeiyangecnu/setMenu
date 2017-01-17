package com.yangyifei.wechat.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ConnectUtil {
	public static InputStream postRequest(String url) throws ClientProtocolException, IOException
	{
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        CloseableHttpClient httpClient = httpClientBuilder.build(); 
		HttpPost post=new HttpPost(url);
		return httpClient.execute(post).getEntity().getContent();
	}
	public static InputStream postRequestWithBody(String url,String body) throws UnsupportedOperationException, ClientProtocolException, IOException
	{
		  HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
	        CloseableHttpClient httpClient = httpClientBuilder.build(); 
			HttpPost post=new HttpPost(url);
			HttpEntity entity=new StringEntity(body,"UTF-8");
			post.setEntity(entity);
			return httpClient.execute(post).getEntity().getContent();
	}
	public static InputStream getRequest(String url) throws UnsupportedOperationException, ClientProtocolException, IOException
	{
		HttpClientBuilder httpClientBuilder=HttpClientBuilder.create();
		 CloseableHttpClient httpClient = httpClientBuilder.build(); 
		 HttpGet get=new HttpGet(url);
		 return httpClient.execute(get).getEntity().getContent();
	}
	
}
