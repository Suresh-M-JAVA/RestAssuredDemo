package postreqbody;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParsingJsonResponseData {

	//approach 1
	//@Test(priority = 1)
	void testJsonResponse() {
		
		given()
			.contentType("application/json")
		
		.when()
			.get("http://localhost:3000/students")
			
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("location[0]", equalTo("dpi"));
	}
	
	//approach 2
	//@Test(priority = 2)
	void testJsonResponse1() {
		
		Response res = given()
			.contentType("ContentType.JSON")
		
		.when()
			.get("http://localhost:3000/students");
		
		Assert.assertEquals(res.statusCode(),200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String loc =  res.jsonPath().get("location[0]").toString();
		Assert.assertEquals(loc, "dpi");
	}
	
	@Test(priority = 3)
	void testJsonResponse2() {
		
		Response res = given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/students");
		

		//starts as objects then array of objects		
	/*	JSONObject jo = new JSONObject(res.asString());	
		for(int i=0;i<jo.getJSONArray("students").length();i++) 
		{
			String phonenum = jo.getJSONArray("students").getJSONObject(i).get("phone").toString();
			System.out.println(phonenum);
		} */
		
		//starts as array of objects
		JSONArray ja=new JSONArray(res.asString());
		System.out.println(ja);
		
		//search in json
		boolean status = false;
		
		for(int i=0;i<ja.length();i++) {
			String phonenum = ja.getJSONObject(i).getString("phone").toString();
			if(phonenum.equals("23456457")) {
				status = true;
				break;
			}
		}
		Assert.assertEquals(status,true);
		
		//validate total of id
		double totalofids=0;
		for(int i=0;i<ja.length();i++) {
			double id = ja.getJSONObject(i).getInt("id");
			totalofids += id;
		}
		System.out.println(totalofids);
	}

}
