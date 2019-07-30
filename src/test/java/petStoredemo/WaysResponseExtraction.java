package petStoredemo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

//sample GET Request in this example - https://petstore.swagger.io/v2/pet/findByStatus?status=pending

public class WaysResponseExtraction {
	
	@Test//(enabled=false)
	public void getresponse_path()
	{
		//pass contenttype and queryparam as given 
		Response res=given()
		  .queryParam("status","pending")
		  .contentType("application/json")
		  
		//resource path
		.when()
		    .get("/pet/findByStatus")
		    
		 //verify response using statuscode and extract response 
		 .then()
		      .statusCode(200).extract().response();
		
		//get exact value from response using Path
		ArrayList<Integer> id=res.path("tags[0].id");
		System.out.println("Extracting using Path ");
		System.out.println("id "+id.get(0));
		
		//get exact value from response using Jsonpath
		String resstr=res.asString();
		JsonPath jsonpath=new JsonPath(resstr);
		String name=jsonpath.get("tags[1].name[0]");
		System.out.println("Extracting using Jsonpath ");
		System.out.println("name  "+name);
		
		
	}
	
	@Test//(enabled=false)
	public void verifyresponse()
	{
		given()
		  .queryParam("status","pending")
		  .contentType("application/json")
		.when()
		    .get("/pet/findByStatus")
		    
		 //verify string response value using hamcrest matcher
		 .then()
		      .statusCode(200).and().body("tags[0].id[1]",equalTo(2)).and().body("status",hasItem("pending"));
		
		}
    
    
	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI="http://petstore.swagger.io/v2";
        //RestAssured.basePath="/pet/findByStatus";
	}
}
