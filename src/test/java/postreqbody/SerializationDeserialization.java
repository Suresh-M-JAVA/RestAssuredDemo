package postreqbody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationDeserialization {
	
	@Test(priority = 1)
	void Pojo2Json() throws JsonProcessingException {
		
		POJO_Postreq stu = new POJO_Postreq();
		stu.setName("ashokkkkk");
		stu.setLocation("kgiri");
		stu.setPhone("23456457");
	
		//convert java obj to json obj
		ObjectMapper om = new ObjectMapper();
		
		String jsonData = om.writerWithDefaultPrettyPrinter().writeValueAsString(stu);
		System.out.println(jsonData);
	}
	
	@Test(priority = 2)
	void Json2Pojo() throws JsonMappingException, JsonProcessingException{
		
		String jsondata = "{\r\n"
				+ "  \"name\" : \"ashokkkkk\",\r\n"
				+ "  \"location\" : \"kgiri\",\r\n"
				+ "  \"phone\" : \"23456457\",\r\n"
				+ "  \"courses\" : null\r\n"
				+ "}";
		
		//convert json data to pojo obj
		ObjectMapper om1 = new ObjectMapper();
		POJO_Postreq stuobj = om1.readValue(jsondata,POJO_Postreq.class);
		System.out.println(stuobj.getName());
		System.out.println(stuobj.getPhone());
		System.out.println(stuobj.getLocation());
				
	}
}
