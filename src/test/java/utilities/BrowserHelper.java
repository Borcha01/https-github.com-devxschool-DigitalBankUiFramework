package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * WebDriver Helper Extensions is designed to simplify Java based Selenium/WebDriver tests.
 * It’s built on top of Selenium/WebDriver to make your tests more readable, reusable and
 * maintainable by providing easy handling towards browser and advance identifiers.
 */

public class BrowserHelper {

    /**
     * Create a WebDriver helper method that waits until page completely loaded.
     * Hint: Try to implement a method usung JavascriptExecutor.
     */
    public static void waitForPageLoadComplete(WebDriver driver, int specifiedTimeout) {
        //TODO: Implement here:

        Wait<WebDriver> wait = new WebDriverWait(driver, specifiedTimeout);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }

    /**
     * Create a WebDriver helper method that waits until element is visible.
     */
    public static WebElement waitForVisibility(WebDriver driver, WebElement element, int timeToWaitInSec) {
        //TODO: Implement here:

        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    /**
     * Create a WebDriver helper method that checks whether a text field is blank i.e. no input is given
     */
    public static boolean isBlank(WebElement element) {
        //TODO: Implement here:

        if (element.getAttribute("value").isEmpty()) {
            return true;
        }
        return false;
    }

     /**
     * Create a WebDriver helper method that performs JavascriptExecutor click operation
     */
    public  static void jsClick(WebDriver driver,WebElement element){
        //TODO: Implement here:

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
