package petStoredemo;

	import org.testng.annotations.Test;
	import static io.restassured.RestAssured.given;
	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;

	//import static org.hamcrest.Matchers.equalTo;
	import org.testng.annotations.BeforeClass;
	import static org.hamcrest.Matchers.*;
	import static org.testng.Assert.assertTrue;

	import org.testng.annotations.AfterClass;

	public class CheckAssertions {
      
		//request GET URI Used in this example - https://petstore.swagger.io/v2/store/order/8
			
		@Test(priority=0)
		public void HardassertionDemo () {
			
			// pass above data as request in json format
			given().contentType(ContentType.JSON)

			.when().get("/order/8")

			// Hard assertion .body verify condition line by line
			.then().statusCode(200).log().body()
			.body("id", equalTo(8))
			.body("petId",lessThan(27))
			.body("complete",equalTo(false));
					
		}
		@Test(priority=1)
		public void SoftassertionDemo () {
			
			// pass above data as request in json format
			given().contentType(ContentType.JSON)

			.when().get("/order/8")

			// Hard assertion .body verify condition line by line
			.then().statusCode(200).log().body()
			.body("id", equalTo(8),"petId",lessThan(90),"complete",equalTo(false));
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

