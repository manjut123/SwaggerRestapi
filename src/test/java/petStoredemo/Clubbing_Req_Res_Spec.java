package petStoredemo;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

//import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeClass;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Clubbing_Req_Res_Spec {
	
	//sample GET Request in this example - https://petstore.swagger.io/v2/pet/findByStatus?status=sold
	static RequestSpecification reqspec;
	static RequestSpecBuilder reqbuild;
           ResponseSpecBuilder resbuild;
           ResponseSpecification resspec;

	@Test//(enabled=false)
	public void reslog() {
		//log header only	
		given()
			 .spec(reqspec)
			
		.when()
		    .get("/findByStatus")
			
		.then()
			 .log()
			 .ifError()
			// .ifStatusCodeIsEqualTo(404)
			 .spec(resspec);
		}
		
		@BeforeClass
		public void beforeClass() {
		
			reqbuild = new RequestSpecBuilder();
			reqbuild.setBaseUri("https://petstore.swagger.io/v2");
			reqbuild.setBasePath("/pet");
			reqbuild.addQueryParam("status","sold");
			reqspec=reqbuild.build();
			
			resbuild=new ResponseSpecBuilder();
			resbuild.expectStatusCode(200);
			resbuild.expectContentType("application/json");
			
			resbuild.expectBody("status",hasItem("sold"));
			resbuild.expectBody("category[0].name",equalTo("Canine"));
			resbuild.expectBody("name[0]",equalTo("Shredder"));
			resspec=resbuild.build();
			
		}

		@AfterClass
		public void afterClass() {
		}

	}

	
	
	