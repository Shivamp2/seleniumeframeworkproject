package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	
	Logger log;
	WebDriver driver;

	@Test(dataProvider = "getLoginData")
	public void login(String emailId, String password, String expectedResult) throws IOException {
		
		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountDropdown().click();
		log.debug("Clicked on My Account dropdown");
		landingpage.loginOption().click();
		log.debug("Clicked on login option");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(emailId);
		log.debug("Email addressed got entered");
		loginpage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginpage.loginButton().click();
		log.debug("Clicked on Login Button");

		AccountPage accountpage = new AccountPage(driver);

		String actualResult = null;

		try {
			if (accountpage.editAccountInformationOption().isDisplayed())
				log.debug("User got logged in");
				actualResult = "Successfull";

		} catch (Exception e) {
			log.debug("User didn't log in");
			actualResult = "Failure";
		}
		Assert.assertEquals(actualResult, expectedResult);
		log.info("Login Test got passed");
	}
	
	
	@BeforeMethod
	public void openBrowser() throws IOException {
		
		log = LogManager.getLogger(LoginTest.class.getName());
		driver = intializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
		
	}
	@AfterMethod
	public void closeBrowser() {
		driver.close();
		log.debug("Browser got closed");
	}

	@DataProvider
	public Object[][] getLoginData() {

		Object[][] data = { { "shivampatil@gmail.com", "Bingo@123", "Successfull" },
				{ "dummy@gmail.com", "test@123", "Failure" } };
		return data;
	}

}
