package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.Driver;

public class Hooks {

    @Before()
    public void setupDriver(Scenario scenario){

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
//    	Driver.closeDriver();

	}
}
