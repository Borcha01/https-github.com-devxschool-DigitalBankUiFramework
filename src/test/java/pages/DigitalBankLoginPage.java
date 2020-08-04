package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Factory in Selenium is an inbuilt Page Object Model concept for Selenium WebDriver but it is very optimized.
 * It is used for initialization of Page objects or to instantiate the Page object itself.
 * It is also used to initialize Page class elements without using "FindElement/s."
 *
 * Step 1: Implement Page Factory in the DigitalBankLoginPage
 * Step 2: Use Annotations of Page Factory
 * Step 3: Locate and add WebElements from the corresponding page
 */
public class DigitalBankLoginPage {
        //TODO: Implement here

     public DigitalBankLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    public WebElement userName;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "submit")
    public WebElement submitBtn;

    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-danger alert-dismissible fade show']")
    public WebElement invalidCredsErrorMsg;

    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']//span[2]")
    public WebElement accountCreatedMessage;


}
