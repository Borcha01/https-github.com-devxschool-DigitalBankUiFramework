package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.AccountInfo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.DigitalBankHomePage;
import pages.DigitalBankTransactionsPage;
import utilities.BrowserHelper;
import utilities.ConfigReader;
import utilities.Driver;

import java.text.DecimalFormat;
import java.util.List;

public class DigitalBankTransactionsSteps {
    private final WebDriver driver = Hooks.driver;
    private DigitalBankHomePage bankHomePage = new DigitalBankHomePage(driver);
    private DigitalBankTransactionsPage bankTransactionsPage = new DigitalBankTransactionsPage(driver);
    private AccountInfo accountInfo= new AccountInfo();

    @Given("^User navigates to Deposit page$")
    public void userNavigatesToDepositPage() {
        bankHomePage.transactionsLink.click();
        bankHomePage.depositLink.click();
        Assert.assertEquals("Failed: Url mismatch",ConfigReader.getPropertiesValue("depositPageUrl"),driver.getCurrentUrl());
    }

    @When("^User User submits a deposit without selected account$")
    public void userUserSubmitsADepositWithoutSelectedAccount() {
        bankTransactionsPage.amount.sendKeys("200");
        bankTransactionsPage.submit.click();
    }

    @When("^User User submits a deposit without amount$")
    public void userUserSubmitsADepositWithoutAmount() {
      new Select(bankTransactionsPage.account).selectByIndex(1);
      bankTransactionsPage.submit.click();

    }

    @When("^User submits a deposit with \"([^\"]*)\" amount$")
    public void UserSubmitsADepositWithAmount(String arg0) throws Throwable {
         accountInfo.setDepositAmount(Double.parseDouble(arg0));
         bankTransactionsPage.amount.sendKeys(arg0);
         bankTransactionsPage.submit.click();
    }

    @When("^User submits a deposit with invalid amount$")
    public void UserSubmitsADepositWithInvalidAmount() {
         new Select(bankTransactionsPage.account).selectByIndex(1);
         bankTransactionsPage.amount.sendKeys("0");
         bankTransactionsPage.submit.click();
    }

    @And("^User selects deposit account as \"([^\"]*)\"$")
    public void userSelectsDepositAccountAs(String arg0) throws Throwable {
        List<WebElement> accounts = new Select(bankTransactionsPage.account).getOptions();
        for (int i=1;i<accounts.size();i++) {
            if(accounts.get(i).getText().contains(arg0)){
                new Select(bankTransactionsPage.account).selectByIndex(i);
                break;
            }
        }
        String str = new Select(bankTransactionsPage.account).getFirstSelectedOption().getText();
        accountInfo.setBeginningBalance(Double.parseDouble(str
                .substring(str.lastIndexOf("$")+1,str.length()-1)));
        accountInfo.setAccountId(new Select(bankTransactionsPage.account).getFirstSelectedOption().getAttribute("value"));


    }

    @Then("^Verify that deposit amount has been applied$")
    public void verifyThatDepositAmountHasBeenApplied() {
        BrowserHelper.waitForPageLoadComplete(driver,20);
        accountInfo.setTotalBalance(accountInfo.getDepositAmount()+accountInfo.getBeginningBalance());
        String actualBalance = bankTransactionsPage.getBalanceByAccountId(accountInfo.getAccountId(),driver).getText();
        String expectedBalance = "Balance: $"+new DecimalFormat("0.00").format(accountInfo.getTotalBalance()) +"";
        Assert.assertEquals("Balance mismatch",expectedBalance,actualBalance);

    }

    @Given("^User navigates to Withdraw page$")
    public void userNavigatesToWithdrawPage() {
        bankHomePage.transactionsLink.click();
        bankHomePage.withdrawLink.click();
        Assert.assertEquals("Failed: Url mismatch",ConfigReader.getPropertiesValue("withdrawPageUrl"),driver.getCurrentUrl());

    }

    @When("^User User submits a withdraw without selected account$")
    public void userUserSubmitsAWithdrawWithoutSelectedAccount() {
        bankTransactionsPage.amount.sendKeys("200");
        bankTransactionsPage.submit.click();
    }

    @When("^User User submits a withdraw without amount$")
    public void userUserSubmitsAWithdrawWithoutAmount() {
         new Select(bankTransactionsPage.account).selectByIndex(1);
      bankTransactionsPage.submit.click();
    }

    @When("^User submits a withdraw with invalid amount$")
    public void userSubmitsAWithdrawWithInvalidAmount() {
            new Select(bankTransactionsPage.account).selectByIndex(1);
         bankTransactionsPage.amount.sendKeys("0");
         bankTransactionsPage.submit.click();
    }

    @And("^User selects withdraw account as \"([^\"]*)\"$")
    public void userSelectsWithdrawAccountAs(String arg0) throws Throwable {

        List<WebElement> accounts = new Select(bankTransactionsPage.account).getOptions();
        Select select = new Select(bankTransactionsPage.account);
        for (int i=1;i<accounts.size();i++) {

            if(accounts.get(i).getText().contains(arg0)){
               select.selectByIndex(i);
                break;
            }
        }
        Thread.sleep(2000);
        String str = select.getFirstSelectedOption().getText();
        accountInfo.setBeginningBalance(Double.parseDouble(str
                .substring(str.lastIndexOf("$")+1,str.length()-1)));
        accountInfo.setAccountId(select.getFirstSelectedOption().getAttribute("value"));

    }

    @When("^User submits a withdraw with \"([^\"]*)\" amount$")
    public void userSubmitsAWithdrawWithAmount(String arg0) throws Throwable {
         accountInfo.setWithdrawAmount(Double.parseDouble(arg0));
         bankTransactionsPage.amount.sendKeys(arg0);
         bankTransactionsPage.submit.click();
    }

    @Then("^Verify that withdraw amount has been applied$")
    public void verifyThatWithdrawAmountHasBeenApplied() {
        BrowserHelper.waitForPageLoadComplete(driver,20);
        accountInfo.setTotalBalance(accountInfo.getBeginningBalance()-accountInfo.getWithdrawAmount());

        String actualBalance = bankTransactionsPage.getBalanceByAccountId(accountInfo.getAccountId(),driver).getText();
        String expectedBalance = "Balance: $"+new DecimalFormat("0.00").format(accountInfo.getTotalBalance()) +"";
        Assert.assertEquals("Balance mismatch",expectedBalance,actualBalance);

    }
}
