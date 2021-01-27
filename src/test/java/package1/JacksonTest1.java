package package1;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import pojoPackage.CreateUsersPojo;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.specification.RequestSpecification;

public class JacksonTest1 {

	@Test
	public void POJOToJsonUsingJackson() throws JsonGenerationException, JsonMappingException, IOException {
		
		CreateUsersPojo cup = new CreateUsersPojo("Ramesh", "QA Professional");
		
		ObjectMapper om = new ObjectMapper();
		
		File file = new File("./src/test/resources/file3.json");
		
		om.writeValue(file, cup);
		
		given().contentType(ContentType.JSON).body(file)
		.when().post("https://reqres.in/api/users").then().assertThat().statusCode(201)
		.body("job", equalToIgnoringCase("QA Professional")).and().extract().response().prettyPrint();
	}
	
	@Test
	public void JSONToPOJOUsingJackson() throws JsonParseException, JsonMappingException, IOException {
		
		File file = new File("./src/test/resources/file3.json");
		ObjectMapper om = new ObjectMapper();
		CreateUsersPojo cup = om.readValue(file, CreateUsersPojo.class);
		
		given().headers("content-type", "application/json")
		.body(cup).when().post("https://reqres.in/api/users").then().assertThat()
		.statusCode(201).body("name", equalToIgnoringCase("ramesh"))
		.and().extract().response().prettyPrint();
	}
}
