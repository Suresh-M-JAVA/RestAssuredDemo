package postreqbody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {

	//@Test(priority = 1)
	void testbasicAuthentication() {
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test(priority = 2)
	void testDigestAuthentication() {
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test(priority = 3)
	void testPreemptiveAuthentication() {
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test(priority = 4)
	void testBearerTokenAuthentication() {
		String bt = "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";
	
		given()
			.headers("Authorization", "Bearer "+ bt)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	   
	//@Test(priority = 5)
	void testOAuth1Authentication() {
	
		given()
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test(priority = 6)
	void testOAuth2Authentication() {
	
		given()
			.auth().oauth2("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")
		.when()
			.get("https://api.github.com/users/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 7)
		void testAPIKeyAuthentication() {
		
//			given()
//				.queryParam("appid", "9578bfe0f99261b641d313dbd4168041")
//			.when()
//				.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
//			.then()
//				.statusCode(200)
//				.log().all();
		
		given()
			.queryParam("appid", "9578bfe0f99261b641d313dbd4168041")
			.pathParam("mypath", "data/2.5/forecast/daily")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
			
		.when()
			.get("https://api.openweathermap.org/{mypath}")
			
		.then()
			.statusCode(200)
			.log().all();
		}
}
