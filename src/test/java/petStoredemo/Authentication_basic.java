package petStoredemo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Authentication_basic {

	static RequestSpecification reqspec;
	static RequestSpecBuilder reqbuild;

	@Test
	public void loginJenkin() {
		// set baseuri
		reqbuild = new RequestSpecBuilder();
		reqbuild.setBaseUri("http://localhost:8080");
		// set authentication method to send username and password
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("manjushree");
		authScheme.setPassword("dimdom123");
		reqbuild.setAuth(authScheme);
		reqspec = reqbuild.build();

		given().spec(reqspec).when().get("/login").then().statusCode(200);

	}
}
