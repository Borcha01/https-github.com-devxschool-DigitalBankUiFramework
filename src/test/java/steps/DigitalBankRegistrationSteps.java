package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.User;
import gherkin.lexer.Th;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.DigitalBankLoginPage;
import pages.DigitalBankSignUpPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.MockData;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * TODO:
 * Add implementations of steps from feature file
 */
public class DigitalBankRegistrationSteps {
    private WebDriver driver = Driver.getDriver();
    private final DigitalBankLoginPage bankLoginPage = new DigitalBankLoginPage(driver);
    private final DigitalBankSignUpPage bankSignUpPage = new DigitalBankSignUpPage(driver);

    @Given("^User navigates to Digital Bank login page$")
    public void user_navigates_to_Digital_Bank_login_page() throws Throwable {
        driver.get(ConfigReader.getPropertiesValue("LOGIN_PAGE_URL"));
        assertEquals("Failed: URL mismatch", ConfigReader.getPropertiesValue("LOGIN_PAGE_URL"), driver.getCurrentUrl());
    }

    @Given("^Verify that web title is \"([^\"]*)\"$")
    public void verify_that_web_title_is(String expectedTitle) throws Throwable {

        assertEquals("Failed: Title mismatch", expectedTitle, driver.getTitle());

    }

    @When("^User logs in with following credentials$")
    public void user_logs_in_with_following_credentials(DataTable dataTable) throws Throwable {

        List<Map<String, String>> creds = dataTable.asMaps(String.class, String.class);
        bankLoginPage.userName.sendKeys(creds.get(0).get("username"));
        bankLoginPage.password.sendKeys(creds.get(0).get("password"));
        bankLoginPage.submitBtn.click();

    }

    @Then("^User successfully logged in to home page$")
    public void user_successfully_logged_in_to_home_page() throws Throwable {
        assertEquals("Failed: URL mismatch", ConfigReader.getPropertiesValue("HOME_PAGE_URL"), driver.getCurrentUrl());
    }

    @Then("^User should be displayed with the error message \"([^\"]*)\"$")
    public void user_should_be_displayed_with_the_error_message(String expectedErrorMessage) throws Throwable {

        assertTrue(bankLoginPage.invalidCredsErrorMsg.isDisplayed());
        String actualErrorMessage = bankLoginPage.invalidCredsErrorMsg.getText().trim().replaceAll("\\n", "").replace("×", "");
        assertEquals("Failed: Error message mismatch", expectedErrorMessage, actualErrorMessage);

    }


    @When("^User logs in with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userLogsInWithAnd(String username, String password) throws Throwable {


        bankLoginPage.userName.sendKeys(username);
        bankLoginPage.password.sendKeys(password);
        bankLoginPage.submitBtn.click();


    }

    @Given("^User navigates to Digital Bank signup page$")
    public void userNavigatesToDigitalBankSignupPage() {

        driver = Driver.getDriver();
        driver.get(ConfigReader.getPropertiesValue("SIGNUP_PAGE_URL"));
        assertEquals("Failed: URL mismatch", ConfigReader.getPropertiesValue("SIGNUP_PAGE_URL"), driver.getCurrentUrl());
    }

    @When("^User creates account with following fields$")
    public void user_creates_account_with_following_fields(DataTable dataTable) throws Throwable {

        List<User> userInfo = dataTable.asList(User.class);

        MockData mockData = new MockData();

        new Select(bankSignUpPage.title).selectByVisibleText(userInfo.get(0).getTitle());
        bankSignUpPage.firstName.sendKeys(userInfo.get(0).getFirstName());
        bankSignUpPage.lastName.sendKeys(userInfo.get(0).getLastName());

        if (userInfo.get(0).getGender().equalsIgnoreCase("M")) {
            bankSignUpPage.genderM.click();
        } else {
            bankSignUpPage.genderF.click();
        }
        bankSignUpPage.dob.sendKeys(userInfo.get(0).getDob());
        bankSignUpPage.ssn.sendKeys(mockData.generateRandomSsn());
        bankSignUpPage.emailAddress.sendKeys(mockData.generateRandomEmail());
        bankSignUpPage.password.sendKeys(userInfo.get(0).getPassword());
        bankSignUpPage.passwordConfirm.sendKeys(userInfo.get(0).getPassword());
        System.out.println(bankSignUpPage.emailAddress.getAttribute("value"));


        Thread.sleep(2000);
        bankSignUpPage.nextButton.click();

        bankSignUpPage.address.sendKeys(userInfo.get(0).getAddress());
        bankSignUpPage.locality.sendKeys(userInfo.get(0).getLocality());
        bankSignUpPage.region.sendKeys(userInfo.get(0).getRegion());
        bankSignUpPage.postalCode.sendKeys(userInfo.get(0).getPostalCode());
        bankSignUpPage.country.sendKeys(userInfo.get(0).getCountry());
        bankSignUpPage.homePhone.sendKeys(userInfo.get(0).getHomePhone());
        bankSignUpPage.mobilePhone.sendKeys(userInfo.get(0).getMobilePhone());
        bankSignUpPage.workPhone.sendKeys(userInfo.get(0).getWorkPhone());

        bankSignUpPage.agreeTermsCheckbox.click();
        bankSignUpPage.submitButton.click();
    }


    @Then("^User should be displayed with the message \"([^\"]*)\"$")
    public void userShouldBeDisplayedWithTheMessage(String expectedMessage) throws Throwable {

        assertEquals("Failed: Error message mismatch", expectedMessage, bankLoginPage.accountCreatedMessage.getText().trim());
    }


}

