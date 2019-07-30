package petStoredemo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import petStoredemo.Addpayload1;

public class SimplePost {

	@Test
	public void testpost()
	{
		Response res=given()
				.contentType("application/json")
				.body("{ \"id\": 45, \"category\": { \"name\": \"geta\" }, \"name\": \"fan1\", \"photoUrls\": [ \"www.der.com\" ], \"tags\": [ { \"name\": \"sdfsdj\" } ], \"status\": \"onwork\"}")
	
	    .when()
	          .post("/pet")
	   	 .then()
	   	    .extract().response();
	   	
	   	System.out.println("response return "+res.prettyPrint());
	   	System.out.println("status code "+res.getStatusCode());
     }
	
	@BeforeClass
	public void beforeClass() {
		RestAssured.baseURI = "https://petstore.swagger.io";
		RestAssured.basePath = "/v2";

	}
}
