package package1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import pojoPackage.CreateUsersPojo;

public class PostExamples {
	
	@Test
	public void createUser() {
		given().contentType(ContentType.JSON).body("{\r\n" + 
				"    \"name\": \"Brij\",\r\n" + 
				"    \"job\": \"leader1\"\r\n" + 
				"}").when().post("https://reqres.in/api/users").then().log().all();
	}
	
	@Test
	public void createUserUsingPojo() {
		CreateUsersPojo createUsersPojo=new CreateUsersPojo("BBSingh", "Xoriant");
		given().header("content-type", "application/json").body(createUsersPojo).when().post("https://reqres.in/api/users").then().log().all();
	}
	
	@Test
	public void createUserUsingJsonPayload() throws IOException, ParseException {
		//json-simple API
		JSONParser jp=new JSONParser();
		FileReader fileReader=new FileReader("./src/test/resources/createUserPayload.json");
		File fl = new File("./src/test/resources/createUserPayload.json");
		JSONObject jsonPayload=(JSONObject)jp.parse(fileReader);
		given().header("content-type", "application/json").body(jsonPayload).when().post("https://reqres.in/api/users").then().log().all();
		given().header("content-type", "application/json").body(fl).when().post("https://reqres.in/api/users").then().log().all();

	}
	
	@Test
	public void writeFileContents() throws IOException {
		//json-simple API
		JSONObject jo=new JSONObject();
		FileWriter fw=new FileWriter("./src/test/resources/createUserPayload.json");
		jo.put("name", "ABCD");
		jo.put("job", "TestJob");
		jo.put("place", "Pune");
		fw.write(jo.toString());
		fw.flush();
		fw.close();
	}
	
	
	
		
	
	

}
