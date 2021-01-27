package package1;

import org.testng.annotations.Test;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import io.restassured.response.Response;
import pojoPackage.CreateUsersPojo;
import pojoPackage.CreateUsersPojo2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class GsonTest {
	Gson gson=new Gson();
	HashMap<String, String> hm;
	
	@Test
	public void createUserGson() {
		CreateUsersPojo createUsersPojo =new CreateUsersPojo("BrijB", "TL2");
		given().header("content-type", "application/json").body(gson.toJson(createUsersPojo)).when().post("https://reqres.in/api/users").then().log().all();
	}
	
	@Test
	public void createUserGsonHashMap() {
		hm=new HashMap<>();
		hm.put("name", "XoriantName");
		hm.put("job", "XoriantJob");
		given().header("content-type", "application/json").body(hm).when().post("https://reqres.in/api/users").then().log().all();
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
		given().header("content-type", "application/json").body(jo.toString()).when().post("https://reqres.in/api/users").then().log().all();
	}
	
	@Test
	public void exclusionStrategy() {
		CreateUsersPojo obj1= new CreateUsersPojo("BBSSSS", "TLTL");
		CreateUsersPojo2 obj2=new CreateUsersPojo2("BBS1", "TL2", "Pune");
		
		ExclusionStrategy es=new ExclusionStrategy() {

			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				// TODO Auto-generated method stub
				return f.getName().equalsIgnoreCase("name");
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

}
