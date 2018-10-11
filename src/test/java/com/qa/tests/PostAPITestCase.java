package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
 import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.MyRestClient;
import com.qa.users.Users;

public class PostAPITestCase {
	String url = "https://reqres.in";
	String apiUrl;
	MyRestClient restClient;

	@BeforeMethod
	public void setUp() {
		apiUrl = url + "/api/users";

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
		Users users = new Users("Rekha", "Manager");

		// converting java object to json String----Serialization---marshalling
		String jsonString = mapper.writeValueAsString(users);
		System.out.println(jsonString);

		// get the response
		CloseableHttpResponse response = restClient.post(apiUrl, jsonString, headerMap);

		// get status code and do validation
 		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("status code is ----" + statusCode);
		Assert.assertEquals(statusCode, 201);
		
		// get the json payload
		
		String responseStringUsers = EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseStringUsers);
		System.out.println("Json response is" + responseJson);
		
		// convert json string to java object---deserialization---unmarshelling
		Users usersObject = mapper.readValue(responseStringUsers, Users.class);
		
		System.out.println(usersObject.getName());
		System.out.println(usersObject.getJob());
		System.out.println(users.getName());
		Assert.assertEquals(usersObject.getName(), users.getName());
		Assert.assertEquals(usersObject.getJob(), users.getJob());

	}
}
