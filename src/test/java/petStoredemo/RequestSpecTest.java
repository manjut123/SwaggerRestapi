package petStoredemo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.anything;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestSpecTest {
    
	//GET Request used this example - https://petstore.swagger.io/v2/user/manju
	
	//declare varibale for requestspecification,requestbuilder
	static RequestSpecification reqspec;
	static RequestSpecBuilder reqbuild;
	
	@BeforeClass()
	public void setup()
	{
		//initialize request builder variable and set URI and content-type using it.
		reqbuild=new RequestSpecBuilder();
		reqbuild.setBaseUri("https://petstore.swagger.io/v2");
		reqbuild.setBasePath("/user");
		reqbuild.setAccept(ContentType.JSON);
		
		//initialize request specification object
		reqspec=reqbuild.build();
		
	}
	
	@Test//(enabled=false)
	public void RequestSpecDemo()
	{
		//pass request spec object with all pre-defined parameter mention in request builder
		given()
		   .spec(reqspec)
		.when()
		    .get("/manju")
		.then()
		     .statusCode(200)
		     .and()
		     .body("id",equalTo(4))
		     .and()
		     .body("phone",equalTo("3920930292"));
		
	}
	
	        
}
