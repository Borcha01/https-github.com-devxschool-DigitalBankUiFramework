package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.BankAccount;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DigitalBankCheckingPage;
import pages.DigitalBankHomePage;
import utilities.BrowserHelper;
import utilities.Driver;

import java.text.DecimalFormat;
import java.util.List;

public class DigitalBankCheckingAccountSteps {

    private final WebDriver driver = Hooks.driver;
    private List<BankAccount> accountInfo;
    private DigitalBankHomePage bankHomePage = new DigitalBankHomePage(driver);
    private DigitalBankCheckingPage bankCheckingPage = new DigitalBankCheckingPage(driver);

    @And("^Verify that \"([^\"]*)\" welcoming message is displayed$")
    public void verifyThatWelcomingMessageIsDisplayed(String arg0) throws Throwable {

        BrowserHelper.waitForPageLoadComplete(driver, 30);
        Assert.assertTrue("Welcoming message is not displayed", bankHomePage.titleMessage.isDisplayed());
        Assert.assertEquals("Welcoming message mismatch", arg0, bankHomePage.titleMessage.getText().trim());

    }

    @And("^Verify that panel with account information is displayed$")
    public void verifyThatPanelWithAccountInformationIsDisplayed() {

        Assert.assertTrue("'Home' link is not displayed", bankHomePage.homeLink.isDisplayed());
        Assert.assertTrue("'Checking' link is not displayed", bankHomePage.checkingLink.isDisplayed());
        Assert.assertTrue("'Savings' link is not displayed", bankHomePage.savingsLink.isDisplayed());
        Assert.assertTrue("'Transactions' link is not displayed", bankHomePage.transactionsLink.isDisplayed());
        Assert.assertTrue("'Digital credit' link is not displayed", bankHomePage.digitalCreditLink.isDisplayed());
        Assert.assertTrue("'Transfer' link is not displayed", bankHomePage.transferLink.isDisplayed());
        Assert.assertTrue("'Visa Credit' link is not displayed", bankHomePage.visaDirectLink.isDisplayed());

    }


    @When("^User clicks on \"([^\"]*)\" account$")
    public void userClicksOnAccount(String type) {

        if (type.equalsIgnoreCase("Checking")) {

            BrowserHelper.jsClick(driver, bankHomePage.checkingLink);
            BrowserHelper.jsClick(driver, bankHomePage.newCheckingLink);

        } else if (type.equalsIgnoreCase("Savings")) {

            BrowserHelper.jsClick(driver, bankHomePage.savingsLink);
            BrowserHelper.jsClick(driver, bankHomePage.newSavingsLink);
        }

    }

    @Then("^Verify that \"([^\"]*)\" dropdown has following options$")
    public void verify_that_dropdown_has_following_options(String dropdown, DataTable options) throws Throwable {
        List<String> list = options.asList(String.class);
        List<WebElement> accountOptions = null;

        if (dropdown.equalsIgnoreCase("Checking")) {
            Assert.assertTrue("'Checking' link is not displayed", bankHomePage.checkingLink.isDisplayed());
            BrowserHelper.jsClick(driver, bankHomePage.checkingLink);
            accountOptions = bankHomePage.checkingOptions;

        } else if (dropdown.equalsIgnoreCase("Savings")) {
            Assert.assertTrue("'Checking' link is not displayed", bankHomePage.checkingLink.isDisplayed());
            BrowserHelper.jsClick(driver, bankHomePage.savingsLink);
            accountOptions = bankHomePage.savingsOptions;
        }

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(list.get(i) + " link did not match", list.get(i), accountOptions.get(i).getText().trim());
        }


    }

    @Then("^Verify \"([^\"]*)\" header is displayed$")
    public void verifyHeaderIsDisplayed(String arg0) throws Throwable {
        Assert.assertEquals("Failed: Header mismatch", arg0, bankHomePage.header.getText().trim());
    }

    @And("^Verify \"([^\"]*)\" label is displayed$")
    public void verifyLabelIsDisplayed(String arg0) throws Throwable {
        Assert.assertTrue(arg0 + " label is not displayed", bankHomePage.labelName.isDisplayed());
    }

    @And("^Verify that radio buttons are unchecked$")
    public void verifyThatRadioButtonsAreUnchecked() {

        for (WebElement el : bankCheckingPage.radioBtnList) {
            Assert.assertFalse("Failed: Radio buttons are selected", el.isSelected());
        }
    }

    @And("^Verify that \"([^\"]*)\" button is displayed$")
    public void verifyThatButtonIsDisplayed(String arg0) throws Throwable {

        for (WebElement el : bankCheckingPage.submitResetBtns) {
            Assert.assertTrue("Failed: " + arg0 + " is not Displayed", el.isDisplayed());
        }

    }

    @When("^User submits an account without Account Type$")
    public void userSubmitsAnAccountWithoutAccountType() {
        driver.switchTo().window(driver.getWindowHandle());

        bankCheckingPage.individualAccountOwnership.click();
        bankCheckingPage.accountName.sendKeys("Test Account");
        bankCheckingPage.deposit.sendKeys("200");
        bankCheckingPage.submit.click();

    }

    @Then("^Verify field error message \"([^\"]*)\" is displayed$")
    public void verifyFieldErrorMessageIsDisplayed(String arg0) throws Throwable {

        Assert.assertEquals("Failed: Validation error message mismatch", arg0, driver.switchTo().activeElement().getAttribute("validationMessage"));

    }

    @When("^User submits checking account without Account Ownership$")
    public void userSubmitsAnAccountWithoutAccountOwnership() {

        driver.switchTo().window(driver.getWindowHandle());

        bankCheckingPage.standardCheckingAccountType.click();
        bankCheckingPage.accountName.sendKeys("Test Account");
        bankCheckingPage.deposit.sendKeys("200");
        bankCheckingPage.submit.click();
    }

    @When("^User submits checking account without Account Name$")
    public void userSubmitsAnAccountWithoutAccountName() {
        bankCheckingPage.individualAccountOwnership.click();
        bankCheckingPage.standardCheckingAccountType.click();
        bankCheckingPage.deposit.sendKeys("200");
        bankCheckingPage.submit.click();
    }

    @When("^User submits checking account without Initial Deposit Amount$")
    public void userSubmitsAnAccountWithoutInitialDepositAmount() {
        bankCheckingPage.individualAccountOwnership.click();
        bankCheckingPage.standardCheckingAccountType.click();
        bankCheckingPage.accountName.sendKeys("Test Account");
        bankCheckingPage.submit.click();
    }

    @When("^User submits checking account with \"([^\"]*)\" deposit$")
    public void userSubmitsAnAccountWithDeposit(String depositAmount) throws Throwable {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("Individual")));

        BrowserHelper.jsClick(driver, bankCheckingPage.individualAccountOwnership);
        BrowserHelper.jsClick(driver, bankCheckingPage.standardCheckingAccountType);
        driver.switchTo().window(driver.getWindowHandle());
        bankCheckingPage.deposit.sendKeys(depositAmount);
        bankCheckingPage.accountName.sendKeys("Test Account");
        bankCheckingPage.submit.click();
    }

    @Then("^Verify alert error message \"([^\"]*)\" is displayed$")
    public void verifyAlertErrorMessageIsDisplayed(String arg0) throws Throwable {

        Assert.assertEquals("Failed: Alert error message mismatch", arg0, bankCheckingPage.depositErrorMsg.getText());

    }


    @When("^User creates Checking account with following info:$")
    public void user_creates_account_with_following_info(List<BankAccount> accounts) throws Throwable {
        accountInfo = accounts;
        driver.switchTo().window(driver.getWindowHandle());


        for (BankAccount account : accounts) {

            WebElement accountOwnership = driver.findElement(By.id(account.getAccountOwnership()));
            accountOwnership.click();
            WebElement accountType = driver.findElement(By.id(account.getAccountType()));
            accountType.click();
            bankCheckingPage.accountName.sendKeys(account.getAccountName());
            bankCheckingPage.deposit.sendKeys(account.getInitialDeposit());

        }
        bankCheckingPage.submit.click();


    }

    @When("^User creates \"([^\"]*)\" account with following info and click Reset button$")
    public void user_creates_account_with_following_info_and_click_reset_button(String arg1, List<BankAccount> accounts) throws Throwable {
        accountInfo = accounts;

        for (BankAccount account : accounts) {

            WebElement accountOwnership = driver.findElement(By.id(account.getAccountOwnership()));
            accountOwnership.click();
            WebElement accountType = driver.findElement(By.id(account.getAccountType()));
            accountType.click();
            bankCheckingPage.accountName.sendKeys(account.getAccountName());
            bankCheckingPage.deposit.sendKeys(account.getInitialDeposit());

        }
        bankCheckingPage.resetBtn.click();


    }

    @Then("^Verify that fields are reset to default values$")
    public void verifyThatFieldsAreResetToDefaultValues() {

        for (BankAccount account : accountInfo) {

            WebElement accountOwnership = driver.findElement(By.id(account.getAccountOwnership()));
            Assert.assertFalse(accountOwnership.isSelected());
            WebElement accountType = driver.findElement(By.id(account.getAccountType()));
            Assert.assertFalse("Failed: Account type radio button is selected", accountType.isSelected());
        }

        Assert.assertTrue("Failed: Account name field is not blank", BrowserHelper.isBlank(bankCheckingPage.accountName));
        Assert.assertTrue("Failed: Initial deposit field is not blank", BrowserHelper.isBlank(bankCheckingPage.deposit));
        bankCheckingPage.resetBtn.click();


    }

    @Then("^Verify newly created account information$")
    public void verify_newly_created_account_information() throws Throwable {


        for (BankAccount account : accountInfo) {

            WebElement accountName = driver.findElement(By.xpath("//div[text()='" + account.getAccountName() + "']"));

            Assert.assertTrue("Failed: Account name is not displayed", accountName.isDisplayed());

            WebElement accountType = driver.findElement(By.xpath("//div[text()='" + account.getAccountName() + "']/..//div[2]/small"));
            Assert.assertEquals("Failed: Account name mismatch", "Account: " + account.getAccountType(), accountType.getText().trim());

            WebElement accountOwnership = driver.findElement(By.xpath("//div[text()='" + account.getAccountName() + "']/..//div[3]/small"));
            Assert.assertEquals("Failed: Account ownership mismatch", "Ownership: " + account.getAccountOwnership(), accountOwnership.getText().trim());

            WebElement balance = driver.findElement(By.xpath("//div[text()='" + account.getAccountName() + "']/..//div[7]"));
            Assert.assertEquals("Failed: Balance mismatch", "Balance: $" + new DecimalFormat("0.00").format(Double.valueOf(account.getInitialDeposit())), balance.getText().trim());
        }

        Assert.assertTrue("Failed: Transaction table is not displayed", bankCheckingPage.tableHeader.isDisplayed());
        Assert.assertEquals("Failed: Date header is not displayed", "Date", bankCheckingPage.tableDate.getText());
        Assert.assertEquals("Failed: Category header is not displayed", "Category", bankCheckingPage.tableCategory.getText());
        Assert.assertEquals("Failed: Description header is not displayed", "Description", bankCheckingPage.tableDescription.getText());
        Assert.assertEquals("Failed: Amount header is not displayed", "Amount", bankCheckingPage.tableAmount.getText());
        Assert.assertEquals("Failed: Balance header is not displayed", "Balance", bankCheckingPage.tableBalance.getText());


    }


}
