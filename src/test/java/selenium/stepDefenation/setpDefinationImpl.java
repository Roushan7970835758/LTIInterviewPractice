package selenium.stepDefenation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

public class setpDefinationImpl {
	WebDriver driver;
	int price = 0, lowestPrice = Integer.MAX_VALUE,weekDay=0;

	@Given("I lonched book my trip website")
	public void i_lonched_book_my_trip_website() {
		// Write code here that turns the phrase above into concrete actions
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.makemytrip.com/");

	}

	@Given("closes the login popup")
	public void closes_the_login_popup() {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//span[@data-cy='closeModal']")).click();
	}

	@Then("click on departure date button")
	public void click_on_departure_date_button() {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//span[contains(text(),'Departure')]")).click();
	}

	@Then("click next month arrow")
	public void click_next_month_arrow() {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
	}

	@Then("select the second row of month")
	public void select_the_second_row_of_month() {
		// Write code here that turns the phrase above into concrete actions

		for (int i = 0; i < 7; i++) {
			int xpathIndex = i+1;
			String currPrice = driver.findElement(By.xpath(
					"(((//div[@class='DayPicker-Month'])[2]//div[@class='DayPicker-Week'])[2]//p[@class=' todayPrice'])["+ xpathIndex +"]"))
					.getText();
//			System.out.println("The "+i+ " day has price: "+currPrice);
			price = convertStrToInt(currPrice);

			if (price < lowestPrice) {
				lowestPrice = price;
				weekDay = xpathIndex;
				
			}
		}

	}

	@Then("print the lowest price of the week and it's date.")
	public void print_the_lowest_price_of_the_week_and_it_s_date() {

		System.out.println("the lowest price of week is: " + lowestPrice);
		driver.findElement(By.xpath("(((//div[@class='DayPicker-Month'])[2]//div[@class='DayPicker-Week'])[2]//p[@class=' todayPrice'])["+ weekDay +"]")).click();
	}
	@Then("click on search button")
	public void click_on_search_button() {
		driver.findElement(By.xpath("//p[@data-cy='submit']//a")).click();
	}

	public int convertStrToInt(String str) {
		String temp ="";
		for(int i=0;i<str.length();i++) {
			char ch = str.charAt(i);
			if(ch>='0' && ch<='9') {
				temp+=ch;
			}
		}
//		System.out.println("After converting price into proper String: "+ temp);
		return Integer.parseInt(temp);
	}
}
