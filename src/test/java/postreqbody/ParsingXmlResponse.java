package postreqbody;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;


public class ParsingXmlResponse {

	//@Test
	void testXMLRes() {
	
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type","application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation.name[0]", equalTo("Developer"));
	}
	
	//@Test
	void testXMLResponse() {
	
		Response res=given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		String pageno = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageno,"1"); 
		
//		String name = res.xmlPath().get("TravelerinformationResponse.Travelers.Travelerinformation[1].name").toString();
//		Assert.assertEquals(name,"Developer"); 
		
	}
	
	@Test
	void testXMLResponses() {
	
		Response res=given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlobj = new XmlPath(res.asString());
		List<String> travellers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travellers.size(),10);
		
		//verify name present in response
		List<String> travellers_name = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		boolean status = false;
		
		for(String tname:travellers_name) {
			if(tname.equals("Developer")) {
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);
		
	}
	
}
