package petStoredemo;

	import java.io.File;
import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Map;
	import static org.hamcrest.Matchers.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;
	import io.restassured.response.Response;

	import static io.restassured.RestAssured.given;
    import petStoredemo.Addpayload1;
    
	public class PostRequest {

		@Test
		public void testpost()
		{
			Addpayload1 payload1=new Addpayload1();
			
			payload1.setId(7);
			payload1.setPetId(7);
			payload1.setQuantity(29);
			payload1.setShipDate("2018-10-05T12:21:43.469+0000");
			payload1.setStatus("running");
			payload1.setComplete(true);
			
			Response res =given()
			    .body(payload1).contentType(ContentType.JSON)
			.when()
			     .post("/store/order")
			 .then().extract().response();
			
			System.out.println("Response return is "+res.prettyPrint());
			//String shpdate=res.path("shipDate");
			//System.out.println("shipdate is "+shpdate);
			
		}
		@BeforeClass
		public void beforeClass() {
			RestAssured.baseURI = "https://petstore.swagger.io";
			RestAssured.basePath = "/v2";

		}
		
		@AfterClass
		public void cleardata()
		{
			given()
				 .contentType(ContentType.JSON)
			.when()
				     .delete("/store/order/7")
			.then().statusCode(200);
		}
	}


