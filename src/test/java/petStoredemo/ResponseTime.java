package petStoredemo;

	import org.testng.annotations.Test;
	import static io.restassured.RestAssured.given;
	import io.restassured.RestAssured;
import io.restassured.assertion.ResponseTimeMatcher;
import io.restassured.http.ContentType;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;

	//import static org.hamcrest.Matchers.equalTo;
	import org.testng.annotations.BeforeClass;
	import static org.hamcrest.Matchers.*;
	import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

	public class ResponseTime {
       
		//sample GET request in this example - https://petstore.swagger.io/v2/store/order/6
		@Test(priority=0)
		public void GetResponseTime () {
			
			// pass above data as request in json format
			long restime=given().contentType(ContentType.JSON)

			.when().get("/order/6")

			// Hard assertion .body verify condition line by line
			.then().statusCode(200).and().body("complete",equalTo(false)).extract().time();
			
			System.out.println("Total Time taken for response -> "+restime);
			
			boolean chktime=restime>2L;
			
			System.out.println("TIME Greater than 2 Seconds ? "+chktime);
			Assert.assertTrue(chktime, "Response taking much time then expected ");
						
		}
		@Test(priority=1)
		public void verifyResponseTime () {
			
			// pass above data as request in json format
			given().contentType(ContentType.JSON)

			.when().get("/order/6")

			// Hard assertion .body verify condition line by line
			.then().statusCode(200).and().time(lessThan(2L), TimeUnit.SECONDS);

		}

		@BeforeClass
		public void beforeClass() {
			RestAssured.baseURI = "https://petstore.swagger.io/v2";
			RestAssured.basePath = "/store";
		}

		@AfterClass
		public void afterClass()
		{
			
		}

	}

