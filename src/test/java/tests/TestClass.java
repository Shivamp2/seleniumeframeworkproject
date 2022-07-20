package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import resources.Base;

public class TestClass extends Base {
	public WebDriver driver;
	@Test
	public void testClass() throws IOException {
		System.out.println("Test Class");
		driver = intializeDriver();
		driver.get("http://tutorialsninja.com/demo/");
		Assert.assertTrue(false);
		
		}
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

}
