package package1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import pojoPackage.CreateUsersPojo;

public class PostExamples2 {
	
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
		JSONObject jsonPayload=(JSONObject)jp.parse(fileReader);
		given().header("content-type", "application/json").body(jsonPayload).when().post("https://reqres.in/api/users").then().log().all();

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
	
	@Test
	public void writeToJsonFile() throws IOException {
		
		File jsonfile = new File("./src/test/resources/createUserPayload1.json");
		if(jsonfile.createNewFile()) {
			System.out.println("File created");
		}
		else {
			System.out.println("File exists");
		}
		
		JSONObject jo=new JSONObject();
		jo.put("name", "Ramesh");
		jo.put("job", "Testing");
		FileWriter fw = new FileWriter(jsonfile);
		fw.write(jo.toString());
		fw.flush();
		fw.close();
		}
	
	@Test
	public void sendPostUsingJsonFileObject() throws IOException, ParseException {
		
		
		  File fl = new File("./src/test/resources/createUserPayload1.json");
		  
		  System.out.println(String.valueOf(given().header("content-type", "application/json").
		  body(fl).when()
		  .post("https://reqres.in/api/users").then()
		  .extract().response().timeIn(TimeUnit.MILLISECONDS)));
		
		  given().header("content-type", "application/json").
		  body(fl).when()
		  .post("https://reqres.in/api/users").then().assertThat().body("name",
		  equalToIgnoringCase("ramesh"), "job", equalToIgnoringCase("testing"));
		 
		
			
			  String str = given().header("content-type", "application/json").
			  body(fl).when()
			  .post("https://reqres.in/api/users").then().extract().response().asString();
			  System.out.println(str);
			  
			  given().header("content-type", "application/json").
			  body(fl).when()
			  .post("https://reqres.in/api/users").then().extract().response().prettyPrint();
			 
			  Long time = given().header("content-type", "application/json").
			  body(fl).when()
			  .post("https://reqres.in/api/users").then().extract()
			  .response().getTimeIn(TimeUnit.MILLISECONDS);
			  System.out.println("Response Time = " + time.toString());
		//JsonPath js = new JsonPath(str);
		
	}
	
	@Test
	public void postExampleUsingPOJO() {
		CreateUsersPojo pojo = new CreateUsersPojo("XoriantName", "XoriantJob");
		given().contentType(ContentType.JSON).body(pojo)
		.post("https://reqres.in/api/users").then().extract().response().prettyPrint();
	}
	
	@Test
	public void postExampleUsingNullValueInHashMap() {
		HashMap<String, String> hm = new HashMap<>();
		hm.put("name", null);
		hm.put("job", "Xoriant123Job");
		given().contentType(ContentType.JSON).body(hm)
		.post("https://reqres.in/api/users").then().assertThat()
		.body("name", equalToObject(null)).extract().response().prettyPrint();
	}
	
	@Test
	public void postExampleUsingHashMap() {
		HashMap<String, String> hm = new HashMap<>();
		hm.put("name", "XoriantEmployee");
		hm.put("job", "Xoriant123Job");
		given().contentType(ContentType.JSON).body(hm)
		.post("https://reqres.in/api/users").then().assertThat()
		.body("name", equalToIgnoringCase("xoriantemployee")).extract().response().prettyPrint();
	}
	
	@Test
	public void postExample() {
		
		RestAssured.baseURI = "https://reqres.in/";
		RestAssured.basePath = "api/users";
		CreateUsersPojo cup = new CreateUsersPojo("Ramesh", "QA Engineer");
		given().contentType(ContentType.JSON).body(cup)
		.when().post().then().body("name", equalToIgnoringCase("ramesh"))
		.statusCode(201).extract().response().prettyPrint();
	}
	
}
