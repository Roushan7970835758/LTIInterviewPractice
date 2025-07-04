package selenium.testComponents;

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
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import selenium.abstractComp.abstractClass;
import selenium.pageObjects.landingPage;

public class baseTest  {
	public WebDriver driver;
	public landingPage l1;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties props = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\globalProps.properties");
		
		props.load(fis);
		
		String browserName = props.getProperty("browserName");
		
		switch(browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			 break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("unsuported browser: "+ browserName);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public landingPage LounchApplication() throws IOException {
		driver =initializeDriver();
		
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\globalProps.properties");
		
		props.load(fis);
		
		String browserUrl = props.getProperty("url1");
		driver.get(browserUrl);
		
		l1 = new landingPage(driver);
		
		return l1;
		
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.close();
//		driver.quit();
		
	}
	
	public String getScreenshot(String name,WebDriver driver) throws IOException {
		if(driver == null) {
			throw new RuntimeException("driver is null can't take ss");
		}
		TakesScreenshot  ts = (TakesScreenshot )driver;
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String fileName = System.getProperty("user.dir")+"//Report//"+name+".png";
		
		FileUtils.copyFile(source,new File(fileName));
		
		return fileName;
	}
}













