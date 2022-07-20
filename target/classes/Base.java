package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public static Properties prop;
	
	public  WebDriver intializeDriver() throws IOException {
		
        WebDriver driver = null;
        
		prop = new Properties();
		String propPath = System.getProperty("user.dir") + "/src/main/java/resources/data.properties";
		FileInputStream ip = new FileInputStream(propPath);
		prop.load(ip);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("safari")) {

			WebDriverManager.iedriver().setup();
			driver = new SafariDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}
	
	public String takeScreenshot(String testName,WebDriver driver) throws IOException {
		
		File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"/screenshots/"+testName+".png";
		FileUtils.copyFile(SourceFile,new File(destinationFilePath));
		
		return destinationFilePath;
	
	}
}