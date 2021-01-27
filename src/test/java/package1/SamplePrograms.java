package package1;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class SamplePrograms {

	WebDriver wd;
	
	@BeforeGroups("selenium")
	public void setUp() {
		System.setProperty("webdriver.gecko.driver","D:\\geckodriver.exe");
		//wd = new ChromeDriver();
		wd = new FirefoxDriver();
	}
	
	@Test(groups= {"selenium"})
	public void seleniumTest() {
		
		wd.get("https://www.google.com/");
		//wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//JavascriptExecutor js = (JavascriptExecutor) wd;  
		
		
		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='q']")));
		wd.findElement(By.cssSelector("input[name='q']")).sendKeys("selenium tutorial");
		wd.findElement(By.cssSelector("input[name='q']")).sendKeys(Keys.ENTER);
		//WebDriverWait wait1 = new WebDriverWait(wd, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.gT5me")));
		List<WebElement> list = wd.findElements(By.xpath("//div[@id='search']//div[@class='g']//h3"));
		for(WebElement ele : list) {
			System.out.println(ele.getText());
		}
		String temp = wd.findElement(By.xpath("//div[@id='search']//div[@class='g'][3]//h3")).getText();
		//js.executeScript("document.getElementsByClassName('LC20lb DKV0Md')[3]");
		//String text = (String) js.executeScript("return arguments[0]");
		assertEquals(temp, "Free Selenium Tutorial | Selenium WebDriver Tutorials | For ...");
		wd.close();
	}
	
	@Test(groups= {"selenium"})
	public void seleniumTestItdc() {
		
		wd.get("https://itdc.co.in/");
		
		new Actions(wd).moveToElement(wd.findElement(By.linkText("ABOUT US"))).build().perform();
		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("BOARD OF DIRECTORS")));
		wd.findElement(By.cssSelector("#menu-item-7892")).click();
		//wd.findElement(By.linkText("BOARD OF DIRECTORS")).click();
		
		String text = wd.findElement(By.cssSelector("h1.animation-element")).getText();
		
		assertEquals(text, "BOARD OF DIRECTORS");
	
		new Actions(wd).keyDown(Keys.SHIFT).click(wd.findElement(By.cssSelector("a[title='facebook']"))).keyUp(Keys.SHIFT).build().perform();
		//wd.findElement(By.cssSelector("a[title='facebook']")).click();;
		
		
		/*
		 * Set<String> st = wd.getWindowHandles(); Iterator<String> iter =
		 * st.iterator(); //iter.next(); String str = ""; iter.next();
		 * 
		 * wd.switchTo().window(iter.next());
		 */
		  
		  ArrayList<String> tabs2 = new ArrayList<String> (wd.getWindowHandles());
		    wd.switchTo().window(tabs2.get(1));
		    ((JavascriptExecutor) wd).executeScript("window.focus();");
		 System.out.println(wd.getCurrentUrl());
		 System.out.println(wd.getPageSource());
		String temp = wd.findElement(By.xpath("//label[contains(text(),'Email or phone')]")).getText();
		assertEquals(temp, "Email or phone");
		wd.close();
		
	}
	
	@Test
	public void sortArray() {
		
		int arr[] = new int[] {2,7,5,9,0,10};
		int temp = 0;
		
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[i] < arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		
		System.out.println(Arrays.toString(arr));
		
		String str = "$234";
		String strarr[] = str.split("\\$");
		System.out.println(strarr[1]);
		for(String s : strarr) {
			System.out.println(s);
		}
	}
	
	@Test
	public void removeDuplicatesFromArray() {
		
		Integer arr[] = new Integer[] {2,3,2,7,3,6,5,5,6,7,7,7,6,5,6,3,2};
		//int newarr[] = new int[arr.length];
		int j = 0,i = 0;
		ArrayList arrl = new ArrayList<>(Arrays.asList(arr));
		System.out.println(arrl.size());
		for(i = 0; i < arrl.size(); i++) {
			for(j = i + 1; j < arrl.size(); j++) {
				if(arrl.get(i) == arrl.get(j)) {
					//arrl.remove(i);
					arrl.remove(j);
					//arrl.remove(j);
					
				}
				
				
			}
			
		}
		arrl.remove(i-1);
		System.out.println(Arrays.toString(arrl.toArray()));
		
	}
	
	@Test
	public void isPrime() {
		
		
		for(int i = 1; i <= 100; i++) {
			boolean temp = true;
			int sqrt = (int) Math.sqrt(i) + 1;
			for(int j = 2; j < sqrt; j++) {
				if(i % j == 0) {
					temp = false;
					break;
				}
			}
			if(temp) {
			System.out.println(i);
			}
		}
	}
	
	@Test
	public void fibonacciSeries() {
		
		int temp = 1;
		int temp1 = 0;
		int temp2 = 0;
		System.out.println(temp);
		while(temp1 < 100) {
			
			temp2 = temp + temp1;
			System.out.println(temp2);
			temp1 = temp;
			temp = temp2;
						
		}
	}
	
	@Test
	public void findSquareRoot() {
		
		int arr[] = new int[] {4, 9, 16, 25, 36, 49, 64, 81, 100};
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 1; j < arr[i]; j++) {
				if(arr[i]/j == j) {
					System.out.println("The square root of the number -: " + arr[i] + " is " + j);
					break;
				}
			}
		}
	}
}
