package selenium.tests;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standAlone {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--incognito");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(15));
		
		
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");
		
		driver.findElement(By.xpath("//button[contains(text(),' Widgets')]")).click();
		JavascriptExecutor js = (JavascriptExecutor )driver;
		js.executeScript("window.scrollBy(0,100)");
		
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(),' Select Menu')]"))));
		driver.findElement(By.xpath("//a[contains(text(),' Select Menu')]")).click();
		
		WebElement  dropdown = driver.findElement(By.id("inputGroupSelect03"));
		Select option = new Select(dropdown);
		option.selectByVisibleText("Proof.");
		
		Actions a = new Actions(driver);
		a.contextClick(driver.findElement(By.xpath("//h1[contains(text(),'Select Menu')]"))).build().perform();
		
		
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
		
		a.moveToElement(driver.findElement(By.id("name"))).click().keyDown(Keys.SHIFT).sendKeys("Roushan Kumar").build().perform();
		
		Set<String> windows = driver.getWindowHandles();
		
		Iterator<String> it = windows.iterator();
		String tab1 = it.next();
		String tab2 = it.next();
		
		driver.switchTo().window(tab1);
		a.click(driver.findElement(By.xpath("//h1[contains(text(),'Select Menu')]"))).perform();
		
		
		
		
		
		
	
		
		
		
		
	}

}
