package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * //TODO:
 * Implement a Junit Test Runner class.
 * This class just needs annotations to understand that cucumber features would be run through it
 * and you can specify feature files to be picked up plus the steps package location.
 */

@RunWith(Cucumber.class)
@CucumberOptions(

		plugin= {"pretty", "html:target/cucumber-report",
			"json:target/cucumber.json"},

	    features = {"src/test/resources"},
	    glue = {"steps"},

		tags = {"@regression"},
		dryRun = false


		)

public class CucumberRunner {
}
