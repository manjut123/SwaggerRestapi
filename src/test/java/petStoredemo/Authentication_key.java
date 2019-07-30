package petStoredemo;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Authentication_key {

	static RequestSpecification reqspec;
	static RequestSpecBuilder reqbuild;

	@Test
	public void loginFAQ() {
		// set base uri
		reqbuild = new RequestSpecBuilder();
		reqbuild.setBaseUri("https://ischatbotqnamaker.azurewebsites.net");
		reqbuild.setBasePath("/qnamaker/knowledgebases/e05ee3a9-a3de-4033-93eb-811cab6c29fa");

		// set authorization key using hash-map function
		Map<String, String> headerSet = new HashMap<String, String>();
		headerSet.put("Authorization", "EndpointKey 49d8a5e0-c369-4ef0-a3f8-96797e82a4b9");
		headerSet.put("Content-Type", "application/json");

		// set body/pay-load using hash-map
		Map<String, String> query = new HashMap<String, String>();
		query.put("question", "f9 settings");

		// pass headers and body to request builder
		reqbuild.addHeaders(headerSet);
		reqbuild.setBody(query);

		// initialize request specification
		reqspec = reqbuild.build();

		// send post request and verify status code
		given().spec(reqspec).when().post("/generateAnswer").then().statusCode(200);

	}
}
