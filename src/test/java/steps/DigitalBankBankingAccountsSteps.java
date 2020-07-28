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
import utilities.BrowserHelper;
import utilities.Driver;

import java.text.DecimalFormat;
import java.util.List;

public class DigitalBankBankingAccountsSteps {

    private final WebDriver driver = Driver.getDriver();
    private List<BankAccount> accountInfo;

    @And("^Verify that \"([^\"]*)\" welcoming message is displayed$")
    public void verifyThatWelcomingMessageIsDisplayed(String arg0) throws Throwable {
        BrowserHelper.waitForPageLoadComplete(driver, 30);
        Assert.assertTrue("Welcoming message is not displayed",driver.findElement(By.xpath("//li[contains(text(),'" + arg0 + "')]")).isDisplayed());
    }

    @And("^Verify that panel with account information is displayed$")
    public void verifyThatPanelWithAccountInformationIsDisplayed() {
        WebElement home = driver.findElement(By.linkText("Home"));
        Assert.assertTrue("'Home' link is not displayed",home.isDisplayed());

        WebElement checking = driver.findElement(By.linkText("Checking"));
        Assert.assertTrue("'Checking' link is not displayed",checking.isDisplayed());

        WebElement savings = driver.findElement(By.linkText("Savings"));
        Assert.assertTrue("'Savings' link is not displayed",savings.isDisplayed());

        WebElement transactions = driver.findElement(By.linkText("Transactions"));
        Assert.assertTrue("'Transactions' link is not displayed",transactions.isDisplayed());

        WebElement digitalCredit = driver.findElement(By.linkText("Digital Credit"));
        Assert.assertTrue("'Digital credit' link is not displayed",digitalCredit.isDisplayed());

        WebElement transfer = driver.findElement(By.linkText("Transfer"));
        Assert.assertTrue("'Transfer' link is not displayed",transfer.isDisplayed());

        WebElement visaDirect = driver.findElement(By.linkText("VISA Direct"));
        Assert.assertTrue("'Visa Credit' link is not displayed",visaDirect.isDisplayed());

    }


    @When("^User clicks on \"([^\"]*)\" account$")
    public void userClicksOnAccount(String type) {

        WebElement element = driver.findElement(By.linkText(type));
        element.click();
        element = driver.findElement(By.linkText("New " + type));
        element.click();
    }

    @Then("^Verify that \"([^\"]*)\" dropdown has following options$")
    public void verify_that_dropdown_has_following_options(String dropdown, DataTable options) throws Throwable {
        WebElement checking = driver.findElement(By.linkText(dropdown));
        Assert.assertTrue("'Checking' link is not displayed",checking.isDisplayed());
        List<String> list = options.asList(String.class);
        checking.click();
        List<WebElement> accountOptions = driver.findElements(By.xpath("//a[text()='" + dropdown + "']/..//ul//a"));

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(list.get(i)+" link did not match",list.get(i), accountOptions.get(i).getText().trim());
        }


    }

    @Then("^Verify \"([^\"]*)\" header is displayed$")
    public void verifyHeaderIsDisplayed(String arg0) throws Throwable {
        WebElement header = driver.findElement(By.xpath("//strong[starts-with(@class,'card-title')]"));
        Assert.assertEquals("Failed: Header mismatch",arg0, header.getText().trim());
    }

    @And("^Verify \"([^\"]*)\" label is displayed$")
    public void verifyLabelIsDisplayed(String arg0) throws Throwable {
        Assert.assertTrue(arg0+ " label is not displayed",driver.findElement(By.xpath("//strong[text()='" + arg0 + "']")).isDisplayed());
    }

    @And("^Verify that radio buttons are unchecked$")
    public void verifyThatRadioButtonsAreUnchecked() {
        List<WebElement> radioBtnList = driver.findElements(By.xpath("//input[@type='radio']"));
        for (WebElement el : radioBtnList) {
            Assert.assertFalse("Failed: Radio buttons are selected",el.isSelected());
        }
    }

    @And("^Verify that \"([^\"]*)\" button is displayed$")
    public void verifyThatButtonIsDisplayed(String arg0) throws Throwable {
        Assert.assertTrue("Failed: "+arg0+ " is not Displayed",driver.findElement(By.xpath("//button[contains(text(),'" + arg0 + "')]")).isDisplayed());
    }

    @When("^User submits an account without Account Type$")
    public void userSubmitsAnAccountWithoutAccountType() {
        WebElement accountOwnership = driver.findElement(By.id("Individual"));
        accountOwnership.click();
        WebElement accountName = driver.findElement(By.id("name"));
        accountName.sendKeys("Test Account");
        WebElement deposit = driver.findElement(By.id("openingBalance"));
        deposit.sendKeys("200");
        WebElement submit = driver.findElement(By.cssSelector("[class='btn btn-primary btn-sm']"));
        submit.click();

    }

    @Then("^Verify field error message \"([^\"]*)\" is displayed$")
    public void verifyFieldErrorMessageIsDisplayed(String arg0) throws Throwable {

        Assert.assertEquals("Failed: Validation error message mismatch",arg0, driver.switchTo().activeElement().getAttribute("validationMessage"));

    }

    @When("^User submits an account without Account Ownership$")
    public void userSubmitsAnAccountWithoutAccountOwnership() {

        WebElement accountType = driver.findElement(By.xpath("(//input[@name='accountType'])[2]"));
        accountType.click();
        WebElement accountName = driver.findElement(By.id("name"));
        accountName.sendKeys("Test Account");
        WebElement deposit = driver.findElement(By.id("openingBalance"));
        deposit.sendKeys("200");
        WebElement submit = driver.findElement(By.cssSelector("[class='btn btn-primary btn-sm']"));
        submit.click();
    }

    @When("^User submits an account without Account Name$")
    public void userSubmitsAnAccountWithoutAccountName() {
        WebElement accountOwnership = driver.findElement(By.id("Individual"));
        accountOwnership.click();
        WebElement accountType = driver.findElement(By.xpath("(//input[@name='accountType'])[2]"));
        accountType.click();
        WebElement deposit = driver.findElement(By.id("openingBalance"));
        deposit.sendKeys("200");
        WebElement submit = driver.findElement(By.cssSelector("[class='btn btn-primary btn-sm']"));
        submit.click();
    }

    @When("^User submits an account without Initial Deposit Amount$")
    public void userSubmitsAnAccountWithoutInitialDepositAmount() {
        WebElement accountOwnership = driver.findElement(By.id("Individual"));
        accountOwnership.click();
        WebElement accountType = driver.findElement(By.xpath("(//input[@name='accountType'])[2]"));
        accountType.click();
        WebElement accountName = driver.findElement(By.id("name"));
        accountName.sendKeys("Test Account");
        WebElement submit = driver.findElement(By.cssSelector("[class='btn btn-primary btn-sm']"));
        submit.click();
    }

    @When("^User submits an account with \"([^\"]*)\" deposit$")
    public void userSubmitsAnAccountWithDeposit(String depositAmount) throws Throwable {
        WebElement accountOwnership = driver.findElement(By.id("Individual"));
        accountOwnership.click();
        WebElement accountType = driver.findElement(By.xpath("(//input[@name='accountType'])[2]"));
        accountType.click();
        WebElement accountName = driver.findElement(By.id("name"));
        accountName.sendKeys("Test Account");
        WebElement deposit = driver.findElement(By.id("openingBalance"));
        deposit.sendKeys(depositAmount);
        WebElement submit = driver.findElement(By.cssSelector("[class='btn btn-primary btn-sm']"));
        submit.click();
    }

    @Then("^Verify alert error message \"([^\"]*)\" is displayed$")
    public void verifyAlertErrorMessageIsDisplayed(String arg0) throws Throwable {
        WebElement error = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//span[2]"));
        Assert.assertEquals("Failed: Alert error message mismatch",arg0, error.getText());
    }


    @When("^User creates \"([^\"]*)\" account with following info:$")
    public void user_creates_account_with_following_info(String arg1, List<BankAccount> accounts) throws Throwable {
        accountInfo = accounts;

        for (BankAccount account : accounts) {

            WebElement accountOwnership = driver.findElement(By.id(account.getAccountType()));
            accountOwnership.click();
            WebElement accountType = driver.findElement(By.id(account.getAccountOwnership()));
            accountType.click();
            WebElement accountName = driver.findElement(By.id("name"));
            accountName.sendKeys(account.getAccountName());
            WebElement deposit = driver.findElement(By.id("openingBalance"));
            deposit.sendKeys(account.getInitialDeposit());

        }
        WebElement submit = driver.findElement(By.cssSelector("[class='btn btn-primary btn-sm']"));
        submit.click();


    }

    @When("^User creates \"([^\"]*)\" account with following info and click Reset button$")
    public void user_creates_account_with_following_info_and_click_reset_button(String arg1, List<BankAccount> accounts) throws Throwable {
        accountInfo = accounts;

        for (BankAccount account : accounts) {

            WebElement accountOwnership = driver.findElement(By.id(account.getAccountOwnership()));
            accountOwnership.click();
            WebElement accountType = driver.findElement(By.id(account.getAccountType()));
            accountType.click();
            WebElement accountName = driver.findElement(By.id("name"));
            accountName.sendKeys(account.getAccountName());
            WebElement deposit = driver.findElement(By.id("openingBalance"));
            deposit.sendKeys(account.getInitialDeposit());
        }
        WebElement reset = driver.findElement(By.cssSelector("[class='btn btn-danger btn-sm']"));
        reset.click();


    }

    @Then("^Verify that fields are reset to default values$")
    public void verifyThatFieldsAreResetToDefaultValues() {

        for (BankAccount account : accountInfo) {

            WebElement accountOwnership = driver.findElement(By.id(account.getAccountOwnership()));
            Assert.assertFalse(accountOwnership.isSelected());
            WebElement accountType = driver.findElement(By.id(account.getAccountType()));
            Assert.assertFalse("Failed: Account type radio button is selected",accountType.isSelected());
        }

        WebElement accountName = driver.findElement(By.id("name"));
        Assert.assertTrue("Failed: Account name field is not blank",BrowserHelper.isBlank(accountName));
        WebElement deposit = driver.findElement(By.id("openingBalance"));
        Assert.assertTrue("Failed: Initial deposit field is not blank",BrowserHelper.isBlank(deposit));
        WebElement reset = driver.findElement(By.cssSelector("[class='btn btn-danger btn-sm']"));
        reset.click();


    }

    @Then("^Verify newly created account information$")
    public void verify_newly_created_account_information() throws Throwable {


        for (BankAccount account: accountInfo){
        WebElement accountName = driver.findElement(By.xpath("//div[text()='" + account.getAccountName() + "']"));
        Assert.assertTrue("Failed: Account name is not displayed",accountName.isDisplayed());

        WebElement accountType = driver.findElement(By.xpath("//div[text()='" + account.getAccountName() + "']/..//div[2]/small"));
        Assert.assertEquals("Failed: Account name mismatch","Account: " + account.getAccountType(), accountType.getText().trim());

        WebElement accountOwnership = driver.findElement(By.xpath("//div[text()='" + account.getAccountName() + "']/..//div[3]/small"));
        Assert.assertEquals("Failed: Account ownership mismatch","Ownership: " + account.getAccountOwnership(), accountOwnership.getText().trim());

        WebElement balance = driver.findElement(By.xpath("//div[text()='" + account.getAccountName() + "']/..//div[7]"));
        Assert.assertEquals("Failed: Balance mismatch","Balance: $" + new DecimalFormat("0.00").format(Double.valueOf(account.getInitialDeposit())), balance.getText().trim());
        }

        WebElement transactionsHeader = driver.findElement(By.xpath("//strong[@class='card-title text-light']"));
        Assert.assertEquals("Failed: Transaction table is not displayed","Transactions", transactionsHeader.getText().trim());

        WebElement transactionTable = driver.findElement(By.id("transactionTable"));
        Assert.assertTrue("Failed: Transaction table is not displayed",transactionTable.isDisplayed());

        WebElement tableHeader = driver.findElement(By.xpath("//table[@id='transactionTable']//th[1]"));
        Assert.assertEquals("Failed: Date header is not displayed","Date", tableHeader.getText());
        tableHeader = driver.findElement(By.xpath("//table[@id='transactionTable']//th[2]"));
        Assert.assertEquals("Failed: Category header is not displayed","Category", tableHeader.getText());
        tableHeader = driver.findElement(By.xpath("//table[@id='transactionTable']//th[3]"));
        Assert.assertEquals("Failed: Description header is not displayed","Description", tableHeader.getText());
        tableHeader = driver.findElement(By.xpath("//table[@id='transactionTable']//th[4]"));
        Assert.assertEquals("Failed: Amount header is not displayed","Amount", tableHeader.getText());
        tableHeader = driver.findElement(By.xpath("//table[@id='transactionTable']//th[5]"));
        Assert.assertEquals("Failed: Balance header is not displayed","Balance", tableHeader.getText());


    }


}
