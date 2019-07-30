package post_putDemo;

import post_putDemo.Tag;
import post_putDemo.Category;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import post_putDemo.NestedPOJO;

public class PostandPutTest {
	NestedPOJO addload = new NestedPOJO();
	NestedPOJO addload1 = new NestedPOJO();

	@BeforeClass()
	public void setup_predata() {
		Tag tagobj = new Tag();
		tagobj.setId(89);
		tagobj.setName("spana");
		Category catobj = new Category();

		// add id
		addload.setId(80);

		// add category
		catobj.setId(87);
		catobj.setName("spana1");
		addload.setCategory(catobj);

		// add name
		addload.setName("linee");

		// add photourl
		List<String> picurl = new ArrayList<String>();
		picurl.add("www.loadpic.com");
		addload.setPhotoUrls(picurl);

		// add tag data
		List<Tag> taglist = new ArrayList<Tag>();
		taglist.add(tagobj);
		addload.setTags(taglist);

		// add status
		addload.setStatus("pending");

		RestAssured.baseURI = "https://petstore.swagger.io/v2";
	}

	@Test()
	public void postdata() {
		
		//POST request send and extract response
		Response res = given().contentType("application/json").body(addload).when().post("/pet").then().extract()
				.response();
		
		//verify response after post request
		ArrayList<String> tagname = res.path("tags.name");
		System.out.println("tag name after post request is " + tagname.get(0));
		Assert.assertTrue(tagname.get(0).equals("spana"), "post request data not properly store ");

	}

	@Test(dependsOnMethods = { "postdata" })
	public void putdata() {
		Tag tagobj = new Tag();
		tagobj.setId(84);
		tagobj.setName("fiza");
		Category catobj = new Category();

		// add id
		addload1.setId(80);

		// add category
		catobj.setId(85);
		catobj.setName("fiza1");
		addload1.setCategory(catobj);

		// add name
		addload1.setName("jiraf");

		// add photourl
		List<String> picurl = new ArrayList<String>();
		picurl.add("www.loadpic.com");
		addload1.setPhotoUrls(picurl);

		// add tag data
		List<Tag> taglist = new ArrayList<Tag>();
		taglist.add(tagobj);
		addload1.setTags(taglist);

		// add status
		addload1.setStatus("onhold");
        
		//send PUT request and extract response
		Response res = given().contentType("application/json").body(addload1).when().put("/pet").then().statusCode(200)
				.extract().response();
		
		
        //verify updated response data 
		ArrayList<String> tagname = res.path("tags.name");
		System.out.println("tag name after put request is " + tagname.get(0));
		Assert.assertTrue(tagname.get(0).equals("fiza"), "put request data not properly store ");
	}

}
