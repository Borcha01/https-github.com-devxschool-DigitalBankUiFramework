package utilities;

import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    /**
     * Implement getDriver Method based on the driver specified in properties file. Create additional conditions for
     * chrome, firefox, safari, ie drivers
     */
    public static WebDriver getDriver() {

        if (driver == null) {
            if (ConfigReader.getPropertiesValue("driver").equalsIgnoreCase("CHROME")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }else if (ConfigReader.getPropertiesValue("driver").equalsIgnoreCase("FIREFOX")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }else if (ConfigReader.getPropertiesValue("driver").equalsIgnoreCase("SAFARI")) {
                driver = new SafariDriver();
            }else if (ConfigReader.getPropertiesValue("driver").equalsIgnoreCase("IE")) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }else {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }

        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;

    }

    /**
     * Create a method that takes a screenshot in case scenario fails
     * @param scenario
     * @throws IOException
     */
    public static void takesScreenshot(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {
			// taking a screenshot
			final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			// adding the screenshot to the report
			scenario.embed(screenshot, "image/png");
		}
	}

    /**
     * Create a method that quits the driver
     * which should check if instance is already instantiated once
     */
	public static void closeDriver() {
		if(driver!=null) {
			driver.quit();
			driver=null;
		}
	}
}
