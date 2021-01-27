package package1;
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

import static io.restassured.module.jsv.JsonSchemaValidator.*;
public class GetTestsJsonSchemaValidation {

@Test
public void getListOfUsersQueryParam() {
	given()
	.contentType(ContentType.JSON)
	.when()
	.get("https://reqres.in/api/users?page=2").then().assertThat()
	.body(matchesJsonSchemaInClasspath("JsonSchemaGetUsers.json"));
}










}
