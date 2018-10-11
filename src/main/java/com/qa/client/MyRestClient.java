package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class  MyRestClient {
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		//create a client
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//create a get request
		HttpGet get=new HttpGet(url);
		//execute the get request
		CloseableHttpResponse httpResponse=httpClient.execute(get);
		return httpResponse;
			}
		public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
		//creating a  client
		CloseableHttpClient client=HttpClients.createDefault();
		//creating post request
		HttpPost post=new HttpPost(url);
		//for adding payload---json entity
		post.setEntity(new StringEntity(entityString));
		//add header
		for(Entry<String,String> entry:headerMap.entrySet()) {
			post.addHeader(entry.getKey(), entry.getValue());
			
		}
	CloseableHttpResponse response=client.execute(post);
		return response;
	}
//put call
	public CloseableHttpResponse put(String url,String entityString,HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
		//creating a  client
		CloseableHttpClient client=HttpClients.createDefault();
		//creating put request
		HttpPut put=new HttpPut(url);
		//for adding payload---json entity
		put.setEntity(new StringEntity(entityString));
		//add header
		for(Entry<String,String> entry:headerMap.entrySet()) {
			put.addHeader(entry.getKey(), entry.getValue());
			
		}
	CloseableHttpResponse response=client.execute(put);
		return response;
	}
	//delete call
	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {
		//creating a  client
		CloseableHttpClient client=HttpClients.createDefault();
		//creating put request
		HttpDelete delete=new HttpDelete(url);
		CloseableHttpResponse response=client.execute(delete);
		return response;
		
		
	

	}
	}
