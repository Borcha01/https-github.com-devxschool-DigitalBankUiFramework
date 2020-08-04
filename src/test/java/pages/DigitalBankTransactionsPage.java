package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Factory in Selenium is an inbuilt Page Object Model concept for Selenium WebDriver but it is very optimized.
 * It is used for initialization of Page objects or to instantiate the Page object itself.
 * It is also used to initialize Page class elements without using "FindElement/s."
 * <p>
 * Step 1: Implement Page Factory in the DigitalBankTransactionsPage
 * Step 2: Use Annotations of Page Factory
 * Step 3: Locate and add WebElements from the corresponding page
 */
public class DigitalBankTransactionsPage {
    //TODO: Implement here

    public DigitalBankTransactionsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[class='btn btn-primary btn-sm']")
    public WebElement submit;

   @FindBy(css = "[class='btn btn-danger btn-sm']")
    public WebElement resetBtn;

    @FindBy(id="id")
    public WebElement account;

    @FindBy(id="amount")
    public WebElement amount;

    public WebElement getBalanceByAccountId(String id, WebDriver driver){
        WebElement element =  driver.findElement(By.xpath("//input[@value='"+id+"']/../following-sibling::div[contains(text(),'Balance')]"));
        return element;
    }
}
