package postreqbody;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {

	@Test(priority = 1)
	void singleFileUpload() {
		File file = new File("C:\\Users\\Suresh\\Documents\\a.txt");
		
		given()
			.multiPart("file", file)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadFile") 
		.then()
			.statusCode(200)
			.body("fileName", equalTo("a.txt"))
			.log().all();
	}
	
	//@Test
	void multipleFileUpload() {
		File file1 = new File("C:\\Users\\Suresh\\Documents\\a.txt");
		File file2 = new File("C:\\Users\\Suresh\\Documents\\apitestinglearning.txt");
		
		given()
			.multiPart("files", file1)
			.multiPart("files", file2)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadMultipleFiles") 
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("a.txt"))
			.body("[1].fileName", equalTo("apitestinglearning.txt"))
			.log().all();
	}
	
	@Test(priority = 2)
	void fileDownload() {
		given()
		
		.when()
			.get("http://localhost:8080/downloadFile/a.txt")
		.then()
			.statusCode(200)
			.log().body();
	}
}
