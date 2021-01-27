package package1;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetTests {
	
@Test
public void getListOfUsers() {
	RestAssured.baseURI="https://reqres.in/api/users?page=2";
	String respInString=RestAssured.get().body().asString();
	System.out.println(respInString);
}

@Test
public void getListOfUsersBasePath() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users?page=2";
	String respInString=RestAssured.get().body().asString();
	System.out.println(respInString);
}

@Test
public void getListOfUsersQueryParam() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	reqSpec.queryParam("page", 2);
	String respInString=reqSpec.get().body().asString();
	System.out.println(respInString);
}

@Test
public void getListOfUsersAssertBody() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	reqSpec.queryParam("page", 2);
	reqSpec.get().then().assertThat().statusCode(200);
}

@Test
public void getListOfUsersAssertBody1() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	//reqSpec.contentType(ContentType.JSON);
	reqSpec.headers("content-type","application/json");
	reqSpec.queryParam("page", 2);
	reqSpec.get().then().log().all();
}

@Test
public void getListOfUsersJsonPath() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	//reqSpec.contentType(ContentType.JSON);
	reqSpec.headers("content-type","application/json");
	reqSpec.queryParam("page", 1);
	//RestAssured.get().then().body("data[0].id", equalTo(1));
	reqSpec.get().then().body("data.id", hasItems(1,2,3,4,5,6));
		/*
		 * JsonPath jp=new JsonPath(respInStr); System.out.println(jp.get("data.id"));
		 */
}

@Test
public void getListOfUsersRootPath() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RestAssured.rootPath="data";
	RequestSpecification reqSpec=RestAssured.given();
	//reqSpec.contentType(ContentType.JSON);
	reqSpec.headers("content-type","application/json");
	reqSpec.queryParam("page", 2);
	//RestAssured.get().then().body("data[0].id", equalTo(1));
	reqSpec.get().then().body("id", hasItems(7)); //equal to body("data.id", hasItems(7))
	
	JsonPath jo = new JsonPath(reqSpec.get().body().asString());
	ArrayList<Integer> als = new ArrayList(Arrays.asList(7,8,9,10,11,12));
	List lst = jo.get("data.id");
	
	assertTrue(lst.containsAll(als));
}

}
