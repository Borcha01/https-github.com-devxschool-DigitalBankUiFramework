
#Digital Bank Web Application
## Bank Transactions Requirements

``` 
TODO: 

1. Refactor all implemented scenarios using Page Object Pattern using PageFactory
 - Create and Implement Page classes for each web page: Login, SignUp, Home, Checking, Savings, Transactions
 - Remove all hardcoded webelments that have been used in Steps classes and store them to Page classes using @FindBy annotation
2. Create and Implement Configuration class that reaads properties file
 - Create properties file and store parameters as key-value pairs, outside of compiled code.
3. Implement Hooks class with @Before , @After methods
4. Implement: 
 - getDriver() Method in Driver class based on the driver specified in properties file. Create additional conditions for
   chrome, firefox, safari, ie drivers  
-  getHeadlessChromeDriver() mehtod for headless execution
5. Create a method in Driver class that quits the driver
   which should check if instance is already instantiated once
6. Create a method in Driver class that takes screenshot
7. Implement Cucumeber-reports, add maven surefile plugin, cucumber-reporting plugin
    you can use  mvn clean test -Dcucumber.options="--tags @tagname"  to run your scenario
8. Create and Implement scenarios based on the requirements below: 

Prerequisites: 
Create a new user for each group
```

####Transaction requrements

```
User story #1: As a User, I want to be able to deposit money to my account
```
Functionalities:

 A. User should be able to select an any savings or checking accounts for the deposit.
- Submitting deposit with unselected account, should throw an error: "Please select an item in the list."

 B. User should be able to enter deposit amount: Deposit amount must be greater than 0.00.
- Submitting deposit without an amount, should throw an error: "Please fill out this field."
- Submitting deposit amount equal to 0.00, should throw an error: "The deposit amount ($entered amount) must be greater than $0.00"
 
 C. After a successful deposit, User will be redirected to account page and be able to see that entered amount has been applied to selected account balance.
 eg. Selected account: My checking account: balance $0.00, Deposit amount: $20.50.
  My checking account: balance $20.50
    
```
User story #2: As a User, I want to be able to withdraw money from my account
```

Functionalities:

 A. User should be able to select an any savings or checking account to withdraw from.
- Submitting withdraw with unselected account, should throw an error: "Please select an item in the list."

 B. User should be able to enter withdrawal amount: Withdrawal amount must be greater than 0.00.
- Submitting withdraw without an amount, should throw an error: "Please fill out this field."
-Submitting 
 
 C. Withdrawal of the amount greater than available balance should throw an error: 
"The withdraw amount ($entered amount) is greater than the available balance ($balance) and overdraft limit ($25.00)."
 
 D. After a successful withdraw, User will be redirected to account page and be able to see that entered amount has been applied to selected account balance.
 eg. Selected account: My checking account: balance $100.00, Withdraw amount: $20.50.
  My checking account: balance $70.50
