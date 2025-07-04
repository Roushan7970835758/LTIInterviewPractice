package selenium.pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import selenium.abstractComp.abstractClass;

public class landingPage extends abstractClass{
	WebDriver driver;
	
	@FindBy(xpath="//button[contains(text(),' Widgets')]")
	WebElement wedgetsSec;
	
	@FindBy(xpath ="//a[contains(text(),' Select Menu')]")
	WebElement selectMenu;
	
	@FindBy(id="name")
	WebElement nameField;
	
	@FindBy(id="email")
	WebElement emailField;
	
	public landingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void SelectMenu() {
		wedgetsSec.click();
		JavascriptExecutor js = (JavascriptExecutor )driver;
		js.executeScript("window.scrollBy(0,100)");
		
		waitForEleToAppear(selectMenu);
		selectMenu.click();
		
		
		WebElement  dropdown = driver.findElement(By.id("inputGroupSelect03"));
		Select option = new Select(dropdown);
		option.selectByVisibleText("Proof.");
	}
	
	public void fillForm(String name, String email) throws IOException, InterruptedException {
		Properties props = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\resources\\globalProps.properties");
		props.load(fis);
		
		String url = props.getProperty("url2");

		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(url);
		
		Actions a = new Actions(driver);
		a.moveToElement(nameField).click().keyDown(Keys.SHIFT).sendKeys(name).build().perform();
		Thread.sleep(3000);
		
		emailField.sendKeys(email);
		Thread.sleep(2000);
		
		
	}
	
	
	
	
}
