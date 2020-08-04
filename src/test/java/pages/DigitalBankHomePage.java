package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Factory in Selenium is an inbuilt Page Object Model concept for Selenium WebDriver but it is very optimized.
 * It is used for initialization of Page objects or to instantiate the Page object itself.
 * It is also used to initialize Page class elements without using "FindElement/s."
 * <p>
 * Step 1: Implement Page Factory in the DigitalBankHomePage
 * Step 2: Use Annotations of Page Factory
 * Step 3: Locate and add WebElements from the corresponding page
 */
public class DigitalBankHomePage {
    //TODO: Implement here

    public DigitalBankHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='page-title']//li")
    public WebElement titleMessage;

    @FindBy(linkText = "Home")
    public WebElement homeLink;

    @FindBy(linkText = "Checking")
    public WebElement checkingLink;

    @FindBy(linkText = "Savings")
    public WebElement savingsLink;

    @FindBy(linkText = "Transactions")
    public WebElement transactionsLink;

    @FindBy(linkText = "Deposit")
    public WebElement depositLink;

     @FindBy(linkText = "Withdraw")
    public WebElement withdrawLink;

    @FindBy(linkText = "Digital Credit")
    public WebElement digitalCreditLink;

    @FindBy(linkText = "Transfer")
    public WebElement transferLink;

    @FindBy(linkText = "VISA Direct")
    public WebElement visaDirectLink;

    @FindBy(linkText = "New Savings")
    public WebElement newSavingsLink;

    @FindBy(linkText = "New Checking")
    public WebElement newCheckingLink;

    @FindBy(xpath = "//a[text()='Checking']/..//ul//a")
    public List<WebElement> checkingOptions;

    @FindBy(xpath = "//a[text()='Savings']/..//ul//a")
    public List<WebElement> savingsOptions;

    @FindBy(xpath = "//strong[starts-with(@class,'card-title')]")
    public WebElement header;

    @FindBy(xpath = "//strong[@class='card-title text-white']")
    public WebElement labelName;
}
