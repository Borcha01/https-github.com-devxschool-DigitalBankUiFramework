package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.BankAccount;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DigitalBankSavingsPage;
import utilities.BrowserHelper;
import utilities.Driver;

import java.text.DecimalFormat;
import java.util.List;

public class DigitalBankSavingsAccountSteps {
        private final WebDriver driver = Hooks.driver;
        private DigitalBankSavingsPage savingsPage = new DigitalBankSavingsPage(driver);
        private List<BankAccount> accountInfo;

     @When("^User submits savings account without Account Ownership$")
    public void userSubmitsAnAccountWithoutAccountOwnership() {

        driver.switchTo().window(driver.getWindowHandle());

        savingsPage.moneyMarketAccountType.click();
        savingsPage.accountName.sendKeys("Test Account");
        savingsPage.deposit.sendKeys("200");
        savingsPage.submit.click();
    }

    @When("^User submits savings account without Account Name$")
    public void userSubmitsAnAccountWithoutAccountName() {
        savingsPage.individualAccountOwnership.click();
        savingsPage.moneyMarketAccountType.click();
        savingsPage.deposit.sendKeys("200");
        savingsPage.submit.click();
    }

    @When("^User submits savings account without Initial Deposit Amount$")
    public void userSubmitsAnAccountWithoutInitialDepositAmount() {
        savingsPage.individualAccountOwnership.click();
        savingsPage. moneyMarketAccountType.click();
        savingsPage.accountName.sendKeys("Test Account");
        savingsPage.submit.click();
    }

    @When("^User submits savings account with \"([^\"]*)\" deposit$")
    public void userSubmitsAnAccountWithDeposit(String depositAmount) throws Throwable {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("Individual")));

        BrowserHelper.jsClick(driver, savingsPage.individualAccountOwnership);
        BrowserHelper.jsClick(driver, savingsPage.moneyMarketAccountType);
        driver.switchTo().window(driver.getWindowHandle());
        savingsPage.deposit.sendKeys(depositAmount);
        savingsPage.accountName.sendKeys("Test Account");
        savingsPage.submit.click();
}

@When("^User creates Savings account with following info:$")
    public void user_creates_account_with_following_info( List<BankAccount> accounts) throws Throwable {
        accountInfo = accounts;
        driver.switchTo().window(driver.getWindowHandle());


        for (BankAccount account : accounts) {

            WebElement accountOwnership = driver.findElement(By.id(account.getAccountOwnership()));
            accountOwnership.click();
            WebElement accountType = driver.findElement(By.id(account.getAccountType()));
            accountType.click();
            savingsPage.accountName.sendKeys(account.getAccountName());
            savingsPage.deposit.sendKeys(account.getInitialDeposit());

        }
        savingsPage.submit.click();


    }

     @Then("^Verify newly created Savings account information$")
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

        Assert.assertTrue("Failed: Transaction table is not displayed", savingsPage.tableHeader.isDisplayed());
        Assert.assertEquals("Failed: Date header is not displayed", "Date", savingsPage.tableDate.getText());
        Assert.assertEquals("Failed: Category header is not displayed", "Category", savingsPage.tableCategory.getText());
        Assert.assertEquals("Failed: Description header is not displayed", "Description", savingsPage.tableDescription.getText());
        Assert.assertEquals("Failed: Amount header is not displayed", "Amount", savingsPage.tableAmount.getText());
        Assert.assertEquals("Failed: Balance header is not displayed", "Balance", savingsPage.tableBalance.getText());


    }

}
