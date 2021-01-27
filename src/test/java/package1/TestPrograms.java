package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class TestPrograms {

	WebDriver wd;
	
	@BeforeGroups("selenium")
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe");
		
		wd = new FirefoxDriver();
	}
	
	@Test(groups="selenium")
	public void testProgram() throws InterruptedException {
		
		wd.get("https://www.goibibo.com");
		wd.findElement(By.cssSelector("#gosuggest_inputSrc")).sendKeys("Shirdi");
		Thread.sleep(2000);
		wd.findElement(By.cssSelector("#gosuggest_inputSrc")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(3000);
		wd.findElement(By.cssSelector("#gosuggest_inputDest")).sendKeys("Mum");
		Thread.sleep(2000);
		wd.findElement(By.cssSelector("#gosuggest_inputDest")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		//wd.findElement(By.xpath("//*[contains(text(),'Goodnews')]")).click();
		Thread.sleep(2000);
		wd.findElement(By.cssSelector("#fare_20200927")).click();
		Thread.sleep(3000);
		wd.findElement(By.cssSelector("#returnCalendar")).click();
		Thread.sleep(2000);
		wd.findElement(By.cssSelector("#fare_20200930")).click();
		Thread.sleep(3000);
		wd.findElement(By.cssSelector("#gi_search_btn")).click();
		
	}
	
	@Test
	public void testProgram1() {
		
		RestAssured.basePath = "api/user";
		RestAssured.baseURI = "https://reqres.in/";
		//RequestSpecification reqspec = RestAssured.given();
		given().contentType(ContentType.JSON).queryParam("page", 2)
		.when().get().then().statusCode(200).extract().response().prettyPrint();
	}
}
