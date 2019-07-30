package endtoEnd;

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

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class PlaceOrder {

	@Test(priority=0)
	public void placeOrder() {

		// create object of POJO class to set data required

		Dataadd addpayload = new Dataadd();
		addpayload.setId(4);
		addpayload.setPetId(44);
		addpayload.setQuantity(21);
		addpayload.setShipdate("2018-10-05T12:21:43.469+0000");
		addpayload.setStatus("hold");
		addpayload.setComplete(true);

		// pass above data as request in json format
		given().contentType(ContentType.JSON).body(addpayload)

		.when().post("/order")

		// verify status code and return id in response is correct
		.then().statusCode(200).log().body().body("id", equalTo(4));

	}

	@Test(priority=1)
	public void getOrder() {

		// pass above data as request in json format

		Response res=given().contentType(ContentType.JSON)

				.when().get("/order/4")

				// verify status code and return id in response is correct

		//.then().log().body().body("status", equalTo("hold"));
             .then().extract().response();
		String status=res.path("status");
		Assert.assertEquals(status,"hold");
	}

	@Test(priority=2)
	public void deleteOrder() {

		// pass above data as request in json format

		given().contentType(ContentType.JSON)

				.when().delete("/order/4")

		// verify status code 200

				.then().statusCode(200).log().body();

	}

	@BeforeClass
	public void beforeClass() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		RestAssured.basePath = "/store";
	}

	@AfterClass
	public void afterClass() {
		
		// verify deleted id with get request and status code 404 in afterclass

		given().contentType(ContentType.JSON)

		.when().get("/order/4").then().statusCode(404);
	}

}
