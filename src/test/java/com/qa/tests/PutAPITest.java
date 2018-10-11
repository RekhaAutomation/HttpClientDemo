package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.MyRestClient;
import com.qa.users.UsersUpdate;

public class PutAPITest {
	String url = "https://reqres.in";
	String apiUrl;
	MyRestClient restClient;

	@BeforeMethod
	public void setUp() {
		apiUrl = url + "/api/users/2";

	}
	@Test
	public void createUserTest() throws ClientProtocolException, IOException {
		restClient = new MyRestClient();
		// header creation
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/Json");

		// preparing json payload---jackson api--core and data bind should be added to
		// pom.xml and json jar
		ObjectMapper mapper = new ObjectMapper();// ObjectMapper is a class from jakson api
		UsersUpdate users = new UsersUpdate("Nag", "Director");

		// converting java object to json String----Serialization---marshalling
		String jsonString = mapper.writeValueAsString(users);
		System.out.println(jsonString);

		// get the response
		CloseableHttpResponse response = restClient.put(apiUrl, jsonString, headerMap);

		// get status code and do validation
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("status code is ----" + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// get the json payload
		
		String responseStringUsers = EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseStringUsers);
		System.out.println("Json response is" + responseJson);
		
		// convert json string to java object---deserialization---unmarshelling
		UsersUpdate usersObject = mapper.readValue(responseStringUsers, UsersUpdate.class);
		
		System.out.println(usersObject.getName());
		System.out.println(usersObject.getJob());
		Assert.assertEquals(usersObject.getName(), users.getName());
		Assert.assertEquals(usersObject.getJob(), users.getJob());
		System.out.println("updated date is ------>"+usersObject.getUpdatedAt());
		Assert.assertNotNull(usersObject.getUpdatedAt());

	}
}



