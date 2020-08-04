package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

public class Hooks {
    public static WebDriver driver;

    /**
     * Create a method setUpDriver() that initializes a WebDriver instance.
     */
    @Before()
    public void setUpDriver(){
       driver=Driver.getHeadlessChromeDriver();
    }


    /**
     * create a tearDown method that executes after scenario and calls takesScreenshot()
     * and closeDriver() methods
     * @param scenario
     * @throws Exception
     */
	@After()
	public void tearDown(Scenario scenario) throws Exception {

    	Driver.takesScreenshot(scenario);
    	Driver.closeDriver();

	}
}
