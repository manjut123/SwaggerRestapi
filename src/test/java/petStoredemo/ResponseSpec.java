package petStoredemo;
	
    import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import static org.hamcrest.Matchers.equalTo;
	import static org.hamcrest.Matchers.anything;

	import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

	import io.restassured.builder.ResponseSpecBuilder;
	import io.restassured.http.ContentType;
	import io.restassured.response.Response;
	
	import io.restassured.specification.ResponseSpecification;

	public class ResponseSpec {

		//GET Request used this example - https://petstore.swagger.io/v2/user/manju
		//declare varibale for requestspecification,requestbuilder
		static ResponseSpecification resSpec;
		static ResponseSpecBuilder resBuild;
		
		@BeforeClass()
		public void setup()
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			RestAssured.basePath="/user";
			
			//initialized response builder
			resBuild=new ResponseSpecBuilder();
			resBuild.expectBody("id",equalTo(4));
			resBuild.expectBody("email",equalTo("m@jd.com"));
			resBuild.expectHeader("server","Jetty(9.2.9.v20150224)");
			
			//initialize response specification
			resSpec=resBuild.build();
		}
		
		@Test//(enabled=false)
		public void RequestSpecDemo()
		{
			given()
			     .contentType("application/json")
			.when()
			    .get("/manju")
			// pass response spec object to verify response as per condition mention in response builder 
			.then()
			     .spec(resSpec);
			
		}
		
		        
	}

