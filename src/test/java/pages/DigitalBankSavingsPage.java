package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Factory in Selenium is an inbuilt Page Object Model concept for Selenium WebDriver but it is very optimized.
 * It is used for initialization of Page objects or to instantiate the Page object itself.
 * It is also used to initialize Page class elements without using "FindElement/s."
 * <p>
 * Step 1: Implement Page Factory in the DigitalBankSavingsPage
 * Step 2: Use Annotations of Page Factory
 * Step 3: Locate and add WebElements from the corresponding page
 */
public class DigitalBankSavingsPage {
    //TODO: Implement here

    public DigitalBankSavingsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "Individual")
    public WebElement individualAccountOwnership;

    @FindBy(id = "name")
    public WebElement accountName;

    @FindBy(id = "Money Market")
    public WebElement moneyMarketAccountType;

    @FindBy(id = "openingBalance")
    public WebElement deposit;

    @FindBy(css = "[class='btn btn-primary btn-sm']")
    public WebElement submit;

    @FindBy(id = "transactionTable")
    public WebElement tableHeader;
    @FindBy(xpath = "//table[@id='transactionTable']//th[1]")
    public WebElement tableDate;
    @FindBy(xpath = "//table[@id='transactionTable']//th[2]")
    public WebElement tableCategory;
    @FindBy(xpath = "//table[@id='transactionTable']//th[3]")
    public WebElement tableDescription;
    @FindBy(xpath = "//table[@id='transactionTable']//th[4]")
    public WebElement tableAmount;
    @FindBy(xpath = "//table[@id='transactionTable']//th[5]")
    public WebElement tableBalance;


}

