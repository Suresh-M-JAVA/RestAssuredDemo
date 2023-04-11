package postreqbody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XmlSchemaValidation {

	@Test
	void xmlschemavalidation() {
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("travel.xsd"));
	}

}
