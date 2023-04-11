package postreqbody;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class CookiesDemo {

	//@Test(priority = 1)
	void testCookies() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC")
			.cookie("NID")
			.cookie("1P_JAR");
		
	}
	
	@Test(priority = 2)
	void getCookiesInfo() {
		
		Response res=given()
		
		.when()
			.get("https://www.google.com/");
		
		//single cookie
		String cookie_val = res.getCookie("AEC");
		System.out.println(cookie_val);
		
		//all cookies
		Map<String, String> cookies_val = res.getCookies();
		
		System.out.println(cookies_val.keySet());
		
		for(String cookie_key : cookies_val.keySet())
		{
			String cookieval = res.getCookie(cookie_key);
			System.out.println("cookie key is " + cookie_key + "  cookie value is" + cookieval);
		}	
	}
	
}

