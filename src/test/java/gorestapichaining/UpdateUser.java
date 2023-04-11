package gorestapichaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


public class UpdateUser {

	@Test
	void testupdateuser(ITestContext context) {
		Faker fk = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", fk.name().fullName());
		data.put("email", fk.internet().emailAddress());
		data.put("gender", "female");
		data.put("status", "active");
		
		String bearerToken = "9bb35d65b05c3f2c07eef7a6be0d1eacb235673155aec4dc1af034f0a9044f95";
	//	int id=(Integer) context.getAttribute("user_id");
		int id= (Integer)context.getSuite().getAttribute("user_id"); // suite level
		given()
			.header("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
			.pathParam("id", id)
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(200)
			.log().all();
			
	}
}
