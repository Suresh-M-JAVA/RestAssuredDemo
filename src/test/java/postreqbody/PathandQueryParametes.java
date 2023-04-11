package postreqbody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class PathandQueryParametes {
	
	//https://reqres.in/api/users?page=2&id=5
	
	@Test(priority = 1)
	void pathandQueryParam() {
		given()
			.pathParam("path1", "users")
			.queryParam("page", 2)
			.queryParam("id", 5)
		
		.when()
			.get("https://reqres.in/api/{path1}")
			
		.then()
			.statusCode(200)
			.log().all();
	}
}
