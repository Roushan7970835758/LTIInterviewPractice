package selenium.stepDefenation;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class stepDefinationImpl2 {

    WebDriver driver;
    int price = 0, lowestPrice = Integer.MAX_VALUE, weekDay = 0;


@Given("I launch the {string} website")
public void i_launch_the_website(String string) {
    // Write code here that turns the phrase above into concrete actions
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.get("https://www.makemytrip.com/");
}
@Given("I close the login popup if it appears")
public void i_close_the_login_popup_if_it_appears() throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	Thread.sleep(3000);
	try {
		WebElement popup = driver.findElement(By.xpath("//span[@data-cy='closeModal']"));
		if(popup.isDisplayed()) {
			driver.findElement(By.xpath("//span[@data-cy='closeModal']")).click();
		}
	}catch(Exception e) {
		System.out.println("login popup doesn't appears");
	}
	
}

@Then ("wait for 2 seconds")
public void waid_for_2_seconds() throws InterruptedException {
	Thread.sleep(2000);
}
@When("I click on the departure date calendar")
public void i_click_on_the_departure_date_calendar() {
    // Write code here that turns the phrase above into concrete actions
	driver.findElement(By.xpath("//span[contains(text(),'Departure')]")).click();
}
@When("I navigate to the month after next")
public void i_navigate_to_the_month_after_next() {
    // Write code here that turns the phrase above into concrete actions
	driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
}
@When("I select the second week of that month")
public void i_select_the_second_week_of_that_month() {
    // Write code here that turns the phrase above into concrete actions
	for (int i = 0; i < 7; i++) {
		int xpathIndex = i+1;
		String currPrice = driver.findElement(By.xpath(
				"(((//div[@class='DayPicker-Month'])[2]//div[@class='DayPicker-Week'])[2]//p[@class=' todayPrice'])["+ xpathIndex +"]"))
				.getText();
//		System.out.println("The "+i+ " day has price: "+currPrice);
		price = convertStrToInt(currPrice);

		if (price < lowestPrice) {
			lowestPrice = price;
			weekDay = xpathIndex;
			
		}
	}
}
@Then("I capture and print the lowest price and its corresponding date")
public void i_capture_and_print_the_lowest_price_and_its_corresponding_date() {
    // Write code here that turns the phrase above into concrete actions
	System.out.println("the lowest price of week is: " + lowestPrice);
	driver.findElement(By.xpath("(((//div[@class='DayPicker-Month'])[2]//div[@class='DayPicker-Week'])[2]//p[@class=' todayPrice'])["+ weekDay +"]")).click();

}
@Then("I click on the search button to proceed")
public void i_click_on_the_search_button_to_proceed() {
    // Write code here that turns the phrase above into concrete actions
	driver.findElement(By.xpath("//p[@data-cy='submit']//a")).click();
}

@And("Close the Browser")
public void Close_the_Browser() {
	driver.close();
}

public int convertStrToInt(String str) {
	String temp ="";
	for(int i=0;i<str.length();i++) {
		char ch = str.charAt(i);
		if(ch>='0' && ch<='9') {
			temp+=ch;
		}
	}
//	System.out.println("After converting price into proper String: "+ temp);
	return Integer.parseInt(temp);
}
}
