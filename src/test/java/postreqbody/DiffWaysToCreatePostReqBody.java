package postreqbody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
hashmap
org.json
POJO - plain old java object
external json file
*/


public class DiffWaysToCreatePostReqBody {

		//@Test(priority = 1)
		void byHashmap() {
			
			HashMap data = new HashMap();
			
			data.put("name", "tamil");
			data.put("location", "bangalore");
			data.put("phone", "34675790328");
			
			String coursearr[] = {"c","c++"};
			data.put("courses", coursearr);
			
			given()
				.contentType("application/json")
				.body(data)
				
			.when()
				.post("http://localhost:3000/students")
				
			.then()
				.statusCode(201)
				.body("name", equalTo("tamil"))
				.body("location", equalTo("bangalore"))
				.body("phone", equalTo("34675790328"))
				.body("courses[0]",equalTo("c"))
				.body("courses[1]",equalTo("c++"))
				.header("Content-Type","application/json; charset=utf-8")
				.log().all();		
		}
		
		//@Test(priority = 2)
		void byJsonLib() {
					
					JSONObject data = new JSONObject();
					data.put("name", "sudha");
					data.put("location", "cbe");
					data.put("phone", "1234562345");
					
					String coursearr[] = {"testng","maven"};
					data.put("courses", coursearr);
					
					given()
						.contentType("application/json")
						.body(data)
						
					.when()
						.post("http://localhost:3000/students")
						
					.then()
						.statusCode(201)
						.body("name", equalTo("sudha"))
						.body("location", equalTo("cbe"))
						.body("phone", equalTo("1234562345"))
						.body("courses[0]",equalTo("testng"))
						.body("courses[1]",equalTo("maven"))
						.header("Content-Type","application/json; charset=utf-8")
						.log().all();		
				}

		//@Test(priority = 3)
		void byPOJO() {
					
			POJO_Postreq data = new POJO_Postreq();
			data.setName("ashokkkkk");
			data.setLocation("kgiri");
			data.setPhone("23456457");
			
			String coursearr[] = {"postman toll","jenkins"};
			data.setCourses(coursearr);
			
					given()
						.contentType("application/json")
						.body(data)
						
					.when()
						.post("http://localhost:3000/students")
						
					.then()
						.statusCode(201)
						.body("name", equalTo("ashokkkkk"))
						.body("location", equalTo("kgiri"))
						.body("phone", equalTo("23456457"))
						.body("courses[0]",equalTo("postman toll"))
						.body("courses[1]",equalTo("jenkins"))
						.header("Content-Type","application/json; charset=utf-8")
						.log().all();		
				}
		
		@Test(priority = 4)
		void byExternalFile() throws FileNotFoundException {
					
				File f = new File(".\\body.json");
				FileReader fr = new FileReader(f);
				JSONTokener jt = new JSONTokener(fr);
				JSONObject data = new JSONObject(jt);
				
					given()
						.contentType("application/json")
						.body(data)
						
					.when()
						.post("http://localhost:3000/students")
						
					.then()
						.statusCode(201)
						.header("Content-Type","application/json; charset=utf-8")
						.log().all();		
				}
		
		@Test(priority = 5)
		void deleteStudent() {
			given()
			
			.when()
				.delete("http://localhost:3000/students/3")
				
			.then()
				.statusCode(200);
		}
	}
