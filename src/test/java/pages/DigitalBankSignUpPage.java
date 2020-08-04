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
 * Step 1: Implement Page Factory in the DigitalBankSignUpPage
 * Step 2: Use Annotations of Page Factory
 * Step 3: Locate and add WebElements from the corresponding page
 */
public class DigitalBankSignUpPage {
    //TODO: Implement here

    public DigitalBankSignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "title")
    public WebElement title;

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(xpath = "//label[@for='male']//input")
    public WebElement genderM;

    @FindBy(xpath = "//label[@for='female']//input")
    public WebElement genderF;

    @FindBy(id = "dob")
    public WebElement dob;

    @FindBy(id = "ssn")
    public WebElement ssn;

    @FindBy(id = "emailAddress")
    public WebElement emailAddress;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "confirmPassword")
    public WebElement passwordConfirm;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-flat m-b-30 m-t-30']")
    public WebElement submitButton;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement nextButton;

    @FindBy(id = "address")
    public WebElement address;

    @FindBy(id = "locality")
    public WebElement locality;

    @FindBy(id = "region")
    public WebElement region;

    @FindBy(id = "postalCode")
    public WebElement postalCode;

    @FindBy(id = "country")
    public WebElement country;

    @FindBy(id = "homePhone")
    public WebElement homePhone;

    @FindBy(id = "mobilePhone")
    public WebElement mobilePhone;

    @FindBy(id = "workPhone")
    public WebElement workPhone;

    @FindBy(id = "agree-terms")
    public WebElement agreeTermsCheckbox;


}
