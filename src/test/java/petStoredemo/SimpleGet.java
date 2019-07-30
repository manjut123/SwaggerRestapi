package petStoredemo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class SimpleGet {
	
	//sample GET request in this example - https://petstore.swagger.io/v2/pet/findByStatus?status=sold
	
	@BeforeClass()
	public void Setup()
	{
     // define baseURI and basepath using Restassured library
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	RestAssured.basePath="/pet/";
	}
	
	@Test()
	public void GetReq()
	{
		//set pre-defined condition/parameter/contentype in given block
		given()
		  .queryParam("status","sold")
		  .contentType("application/json")
		//give exact resource location in when block
		.when()
		   .get("/findByStatus")
		// mention required verification code in then block
		.then()
		     .statusCode(200);		 
		
	}
	}