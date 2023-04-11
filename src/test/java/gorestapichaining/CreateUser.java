package gorestapichaining;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class CreateUser {

	@Test
	void testcreateuser(ITestContext context) {
		Faker fk = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", fk.name().fullName());
		data.put("email", fk.internet().emailAddress());
		data.put("gender", "male");
		data.put("status", "inactive");
		
		String bearerToken = "9bb35d65b05c3f2c07eef7a6be0d1eacb235673155aec4dc1af034f0a9044f95";
		
		int id=given()
			.header("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		System.out.println("generated id is: "+id);
		
		//context.setAttribute("user_id", id); //like environment variable - test level
		context.getSuite().setAttribute("user_id", id); // suite level
	}
}
