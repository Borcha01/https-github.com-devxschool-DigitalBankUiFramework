package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Factory in Selenium is an inbuilt Page Object Model concept for Selenium WebDriver but it is very optimized.
 * It is used for initialization of Page objects or to instantiate the Page object itself.
 * It is also used to initialize Page class elements without using "FindElement/s."
 * <p>
 * Step 1: Implement Page Factory in the DigitalBankCheckingPage
 * Step 2: Use Annotations of Page Factory
 * Step 3: Locate and add WebElements from the corresponding page
 */
public class DigitalBankCheckingPage {

    //TODO: Implement here

    public DigitalBankCheckingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='radio']")
    public List<WebElement> radioBtnList;

    @FindAll({
            @FindBy(xpath = "//button[@class='btn btn-primary btn-sm']"),
            @FindBy(xpath = "//button[@class='btn btn-danger btn-sm']")
    })
    public List<WebElement> submitResetBtns;

    @FindBy(id = "Individual")
    public WebElement individualAccountOwnership;

    @FindBy(id = "name")
    public WebElement accountName;

    @FindBy(id = "openingBalance")
    public WebElement deposit;

    @FindBy(css = "[class='btn btn-primary btn-sm']")
    public WebElement submit;

    @FindBy(id = "Standard Checking")
    public WebElement standardCheckingAccountType;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]//span[2]")
    public WebElement depositErrorMsg;

    @FindBy(css = "[class='btn btn-danger btn-sm']")
    public WebElement resetBtn;

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
    @FindBy(xpath = "//label[@class='switch switch-text switch-success switch-pill float-right']/..//div[7]")
    public WebElement activeAccount;

}
