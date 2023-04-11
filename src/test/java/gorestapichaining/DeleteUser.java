package gorestapichaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;


public class DeleteUser {

	@Test
	void deleteuser(ITestContext context) {
		String bearerToken = "9bb35d65b05c3f2c07eef7a6be0d1eacb235673155aec4dc1af034f0a9044f95";
		//int id=(Integer) context.getAttribute("user_id");
		int id= (Integer)context.getSuite().getAttribute("user_id"); // suite level
		given()
		.header("Authorization", "Bearer "+bearerToken)
		.pathParam("id", id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();
	}
}
