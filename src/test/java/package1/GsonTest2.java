package package1;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoPackage.CreateUsersPojo;
import pojoPackage.CreateUsersPojo2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GsonTest2 {
	Gson gson=new Gson();
	HashMap<String, String> hm;
		
	@Test
	public void createUserGson() {
		CreateUsersPojo createUsersPojo =new CreateUsersPojo("Ramesh R", "QA Testing");
		given().header("content-type", "application/json").body(gson.toJson(createUsersPojo)).when().post("https://reqres.in/api/users").then().log().all();
	}
	
	@Test
	public void createUserGsonHashMap() {
		hm=new HashMap<>();
		hm.put("name", "Ramesh Rangarajan");
		hm.put("job", "QA Testing Job");
		given().header("content-type", "application/json").body(gson.toJson(hm)).when().post("https://reqres.in/api/users").then().log().all();
	}
	
	@Test
	public void createUsersBuilderGson() {
		hm=new HashMap<>();
		hm.put("name", "XoriantName1");
		hm.put("job", "XoriantJob1");
		GsonBuilder gb=new GsonBuilder();
		gson=gb.setPrettyPrinting().create();
		//given().header("content-type", "application/json").body(gson.toJson(hm)).when().post("https://reqres.in/api/users").then().log().all();
		System.out.println(gson.toJson(hm));
		
	}
	
	@Test
	public void createUser1() {
		JsonObject jo =new JsonObject();
		jo.addProperty("name", "BBS11");
		jo.addProperty("job", "TestLead1122");
		System.out.println(jo.toString());
	}
	
	@Test
	public void exclusionStrategy() {
		CreateUsersPojo obj1= new CreateUsersPojo("BBSSSS", "TLTL");
		CreateUsersPojo2 obj2=new CreateUsersPojo2("BBS1", "TL2", "Pune");
		
		ExclusionStrategy es=new ExclusionStrategy() {

			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				// TODO Auto-generated method stub
				return f.getName().equalsIgnoreCase("job");
				//return false;
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				
				//return clazz.equals(CreateUsersPojo.class);
				return false;
			}
			
		};
		
		GsonBuilder gb=new GsonBuilder();
		gb.setExclusionStrategies(es);
		Gson gson1=gb.create();
		System.out.println(gson1.toJson(obj1));
		
	}
	
	@Test
	public void sendPostUsingGsonFileObject() throws IOException, ParseException {
		
		
		  File fl = new File("./src/test/resources/createUserPayload1.json");
		  
		  Long time = given().contentType(ContentType.JSON).body(gson.toJson(fl)).when()
		  .post("https://reqres.in/api/users")
		  .then().extract().response().timeIn(TimeUnit.MILLISECONDS);
		  
		  System.out.println(time);
		  
		  Long time1 = given().contentType(ContentType.JSON).body(fl).when()
				  .post("https://reqres.in/api/users")
				  .then().extract().response().timeIn(TimeUnit.MILLISECONDS);
				  
				  System.out.println(time1);
	}
	
	@Test
	public void sendPostUsingGson() {
		
		JsonObject jo = new JsonObject();
		jo.addProperty("name", "Ramesh Rangarajan");
		jo.addProperty("job", "Test123");
		jo.addProperty("place", "Pune");
		String str = jo.toString();
		
		given().header("content-type", "application/json").body(str)
		.when().post("https://reqres.in/api/users")
		.then().extract().response().prettyPrint();
		
		String temp = given().header("content-type", "application/json").body(gson.toJson(jo))
		.when().post("https://reqres.in/api/users")
		.then().extract().response().asString();
		
		System.out.println(temp);
		
		given().header("content-type", "application/json").body(String.valueOf(jo))
		.when().post("https://reqres.in/api/users")
		.then().extract().response().prettyPrint();
	}
	
	@Test
	public void writeToFileUsingGson() throws IOException {
		
		File file = new File("./src/test/resources/createUserPayload2.json");
		if(file.createNewFile()) {
			System.out.println("File created");
		}
		else {
			System.out.println("File exists");
		}
		
		JsonObject jo = new JsonObject();
		jo.addProperty("name", "Ramesh Rangarajan");
		jo.addProperty("job", "QA Engineer");
		jo.addProperty("place", "Pune");
		
		FileWriter fw = new FileWriter(file);
		
		fw.write(jo.toString());
		fw.flush();
		fw.close();
	}
	
	@Test
	public void usingGsonExclusionStrategies() {
		CreateUsersPojo cup = new CreateUsersPojo("Ramesh", "QA Professional");
		
		ExclusionStrategy es = new ExclusionStrategy() {
			
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				// TODO Auto-generated method stub
				return false;
				//return f.getName().equalsIgnoreCase("name");
			}
			
			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				// TODO Auto-generated method stub
				//return clazz.equals(CreateUsersPojo.class);
				return false;
			}
		};
		
		GsonBuilder gb = new GsonBuilder();
		gb.setExclusionStrategies(es);
		gb.setPrettyPrinting();
		Gson gson = gb.create();
		
		System.out.println(gson.toJson(cup));
		
	}
	
	
}
