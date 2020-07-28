#Digital Bank Web Application
##### Test Username: JohnDoe@testemail.com
##### Test Password: Password1


``` 
TODO: 

1. Come up with all possible negative and positive scenarios 
based on the given requirements for Bank Account functionalities.
Practice scenario/scenario outlines with example tables
2. Create DigitalBankBankingAccountsSteps class and implement your scenarios
3. Create and Implement Runner class
4. Create and Implement a BrowserHelper class to store reusable methods
5. After completing automation script, implement cucumber-reporting
6. Use best practices to locate a webelemnets using:
 - Id locator
 - Name locator
 - linkText & Partial linkText
 - CSS Selector
 - XPath

Prerequisites: 
Create a new user for each group
```
     
## BANKING ACCOUNTS Requirements:
 
 #### CHECKING ACCOUNT

 ----
 
 #### User Story #1: 
 
>As a user, I want to be able to view all details in my account home page.

* Functionalities:
1. Display message "Welcome (First_name) on the right top header.
2. Display a panel with account information: 
- Home title
- BANKING ACCOUNTS title: with the following items: Checking, Saving, Transactions
- CREDIT ACCOUNTS title: with the following items: Digital Credit
- TRANSFERS / PAYMENTS title: with the following items: Transfer
- 3RD PARTY TRANSFERS title: with the following items: VISA Direct
----
 #### User Story #2: 
>As a User,
I want have the ability to create a new Checking bank account
so that my payment can be processed from my preferred account.
>

Functionalities:
1. Display Checking field under BANKING ACCOUNTS section, 
clicking on 'Checking' dropdown should be displayed with following options: View Checking, New Checking
2. Clicking on 'New Checking' option, should redirect user to "http://dbankdemo.com/account/checking-add" 
(Note: Click on 'New Checking' and try to open it in new tab)
3. Display a header "New Checking Account"
4. Display a label "Select Checking Account Type" with the following radio button options:
  Standard Checking, Interest Checking  
  - All Radio buttons should be unchecked by default  
5. Display a label "Select Account Ownership" with the following radio button options: Individual, Joint 
  - All Radio buttons should be unchecked by default  
6. Display an Input filed "Account Name". 
- Accepts alphanumeric and special characters.
6. Display an Input filed "Initial Deposit Amount"
- Minimum opening deposit is $25.00. Accepts numeric whole or decimal values.
7. Display 'Submit' button, clicking on it should successfully create an account
 and user will be redirected to page to view checking account.
8. Display 'Reset' button, clicking on it should reset all filled values to default state.


Logic:

If any of the following conditions are triggered, it should throw an Error Msg. 
* Rule 1: Unselected Account Type: 
 Logic: Account Type options are not selected.
Error Msg: "Please select one of these options."
* Rule 2: Unselected Account Ownership: 
 Logic: Account Ownership options are not selected.
Error Msg: "Please select one of these options."
* Rule 3: Empty Account Name: 
 Logic: Clicking submit button with an empty Account Name.
Error Msg: "Please fill out this field."
* Rule 4: Empty Initial Deposit Amount: 
 Logic: Clicking submit button with an empty Initial Deposit Amount.
Error Msg: "Please fill out this field."
* Rule 5: Invalid Initial Deposit Amount: 
 Logic: Clicking submit button with an invalid Initial Deposit Amount.
Error Msg: "Please match the requested format."
* Rule 6: Minimum Initial Deposit Amount: 
 Logic: Clicking submit button with Initial Deposit Amount less than a minimum amount.
Error Msg: "The initial deposit ($enteredAmount) entered does not meet the minimum amount ($25.00) required. Please enter a valid deposit amount."

---
 #### User Story #3: 
>As a User,
I want to have the ability to view all my Checking bank account
so that I can manage my accounts and see account balance and transactions.
>

Functionalities:
1. After User creates a new checking account, user should be able to view checking account information.
All information should match the entered values during the submition of checking account.
2. Display "Transactions" table with all transaction details: 
- Display header "Date": Date and time when transaction happened. 
- Display header "Category": What kind of transaction was made: Income, Misc etc
- Display header "Description": Displays transaction id and type of Transaction
- Display header "Amount": Displays the amount of made transaction
- Display header "Balance": Displays balance amount, after each transaction
3. If User has more than 1 checking accounts, only one of the accounts can be Actived: 
- Display ON/OFF toggle button on each Checking account
- Display transaction history for a particular checking account if it is Activated(ON)

 #### SAVINGS ACCOUNT

#### User Story #4: 
>As a User,
I want have the ability to create a new Savings bank account
so that my payment can be processed from my preferred account.
>

Functionalities:
1. Display Checking field under BANKING ACCOUNTS section, 
clicking on 'Savings' dropdown should be displayed with following options: View Savings, New Savings
2. Clicking on 'New Savings' option, should redirect user to "http://dbankdemo.com/account/savings-add"
(Note: Click on 'New Savings' and try to open it in new tab)
3. Display a header "New Savings Account"
4. Display a label "Select Savings Account Type" with the following radio button options:
  Savings, Money Market     
  - All Radio buttons should be unchecked by default  
5. Display a label "Select Account Ownership" with the following radio button options: Individual, Joint 
  - All Radio buttons should be unchecked by default  
6. Display an Input filed "Account Name". 
- Accepts alphanumeric and special characters.
6. Display an Input filed "Initial Deposit Amount"
- Minimum opening deposit is $2500.00. Accepts numeric whole or decimal values.
7. Display 'Submit' button, clicking on it should successfully create an account
 and user will be redirected to page to view saving account.
8. Display 'Reset' button, clicking on it should reset all filled values to default state.


Logic:

If any of the following conditions are triggered, it should throw an Error Msg. 
* Rule 1: Unselected Account Type: 
 Logic: Account Type options are not selected.
Error Msg: "Please select one of these options."
* Rule 2: Unselected Account Ownership: 
 Logic: Account Ownership options are not selected.
Error Msg: "Please select one of these options."
* Rule 3: Empty Account Name: 
 Logic: Clicking submit button with an empty Account Name.
Error Msg: "Please fill out these field."
* Rule 4: Empty Initial Deposit Amount: 
 Logic: Clicking submit button with an empty Initial Deposit Amount.
Error Msg: "Please fill out these field."
* Rule 5: Invalid Initial Deposit Amount: 
 Logic: Clicking submit button with an invalid Initial Deposit Amount.
Error Msg: "Please match the requested format."
* Rule 6: Minimum Initial Deposit Amount: 
 Logic: Clicking submit button with Initial Deposit Amount less than a minimum amount.
Error Msg: "TThe initial deposit ($enteredAmount) entered does not meet the minimum amount ($2500.00) required. Please enter a valid deposit amount."

---
 #### User Story #5: 
>As a User,
I want to have the ability to view all my Savings bank account
so that I can manage my accounts and see account balance and transactions.
>

Functionalities:
1. After User creates a new savings account, user should be able to view savings account information.
All information should match the entered values during the submission of savings account.
2. Display "Transactions" table with all transaction details: 
- Display header "Date": Date and time when transaction happened. 
- Display header "Category": What kind of transaction was made: Income, Misc etc
- Display header "Description": Displays transaction id and type of Transaction
- Display header "Amount": Displays the amount of made transaction
- Display header "Balance": Displays balance amount, after each transaction
3. If User has more than 1 checking accounts, only one of the accounts can be Actived: 
- Display ON/OFF toggle button on each Savings account
- Display transaction history for a particular checking account if it is Activated(ON)



