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

public class GetCallTest {
	String url="http://restapi.demoqa.com/utilities/weather/city/pune";
	String apiUrl;
	MyRestClient restClient;
@BeforeMethod
public void setUp() {
	apiUrl=url+"/pune";
	
}
@Test
public void getAPITest() throws ClientProtocolException, IOException {
restClient=new MyRestClient();
 CloseableHttpResponse httpResponse=restClient.get(apiUrl);
//status code
int statusCode=httpResponse.getStatusLine().getStatusCode();
System.out.println(statusCode);
//reason phrase
String reasonPhrase=httpResponse.getStatusLine().getReasonPhrase();
System.out.println("reason phrase is "+reasonPhrase); 
//assertion
Assert.assertEquals(statusCode, 200);
//Json String
HttpEntity entity=httpResponse.getEntity();
String responseString=EntityUtils.toString(entity);
System.out.println("Response String is "+responseString);
//All headers
Header headersArray[]=httpResponse.getAllHeaders();
HashMap<String,String> allHeaders=new HashMap<String,String>();
for(Header temp:headersArray) {
	allHeaders.put(temp.getName(), temp.getValue());
	}
System.out.println("Headers in the  response are------->"+allHeaders);
String contentType=allHeaders.get("Content-Type");
//assertion
Assert.assertEquals(contentType, "application/json");
}
} 