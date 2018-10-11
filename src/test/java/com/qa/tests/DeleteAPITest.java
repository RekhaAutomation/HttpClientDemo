package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.MyRestClient;

public class DeleteAPITest {
	String url = "https://reqres.in/api/users";
	String apiUrl;
	MyRestClient restClient;

	@BeforeMethod
	public void setUp() {
		apiUrl = url + "/2";

	}

	@Test
	public void deleteAPITest() throws ClientProtocolException, IOException {
		restClient = new MyRestClient();
		CloseableHttpResponse httpResponse = restClient.delete(apiUrl);
		// 1.status code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		// reason phrase
		String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
		System.out.println("reason phrase is " + reasonPhrase);
		// assertion
		Assert.assertEquals(statusCode, 204);

		// 2.Json String
		HttpEntity entity = httpResponse.getEntity();
		Assert.assertNull(entity);
	
		// 3.All headers
		Header headersArray[] = httpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header temp : headersArray) {
			allHeaders.put(temp.getName(), temp.getValue());
		}

		System.out.println("Headers in the  response are------->" + allHeaders);

	}
}
