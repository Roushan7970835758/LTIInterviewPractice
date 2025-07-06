package cucumberRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\selenium\\cucumber", glue ="selenium.stepDefenation",
monochrome=true,tags="@Test2",
		plugin= {"html:target/cucumber.html", "pretty" })
public class cucumberRunner extends AbstractTestNGCucumberTests  {

	
	
}
