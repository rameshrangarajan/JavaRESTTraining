package package1;
import org.testng.annotations.DataProvider;
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

public class DP {

@DataProvider
public static Object[][] getData() {
	Object[][] obj=new Object[2][2];
	obj[0][0]=2;
	obj[0][1]=0;
	obj[1][0]=1;
	obj[1][1]=0;
	return obj;
}
}
