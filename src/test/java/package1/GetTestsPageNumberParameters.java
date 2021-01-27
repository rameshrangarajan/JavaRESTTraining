package package1;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetTestsPageNumberParameters {

@Parameters("pageNumber")
//@Test
public void getListOfUsersUsingPageNumberParam(int pageNumber) {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RestAssured.rootPath="data";
	RequestSpecification reqSpec=RestAssured.given();
	//reqSpec.contentType(ContentType.JSON);
	reqSpec.headers("content-type","application/json");
	reqSpec.queryParam("page", pageNumber);
	//RestAssured.get().then().body("data[0].id", equalTo(1));
	reqSpec.get().then().body("id", hasItems(7)); //equal to body("data.id", hasItems(7))
		
}

@Parameters("pageNumber")
@Test
public void getListOfUsersQueryParam(int pageNumber) {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	reqSpec.queryParam("page", pageNumber);
	String respInString=reqSpec.get().body().asString();
	System.out.println(respInString);
}








}
