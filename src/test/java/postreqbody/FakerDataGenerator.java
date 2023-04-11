package postreqbody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	@Test
	void testfakerdatagenerator() {
		Faker fk = new Faker();
		
		String fullname = fk.name().fullName();
		String firstname = fk.name().firstName();
		String lastname = fk.name().lastName();
		
		String username = fk.name().username();
		String password = fk.internet().password();
		
		String phone = fk.phoneNumber().cellPhone();
		
		String email = fk.internet().safeEmailAddress();
	
		System.out.println(fullname);
		System.out.println(firstname);
		System.out.println(lastname);
		System.out.println(username);
		System.out.println(password);
		System.out.println(phone);
		System.out.println(email);
	}
}
