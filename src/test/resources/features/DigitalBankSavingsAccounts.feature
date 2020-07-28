@regression
Feature: As a Customer, I want to add/edit/manage Bank Account Payment Profile
Background:
    Given User navigates to Digital Bank login page
    And Verify that web title is "Digital Bank"
    When User logs in with "JohnDoe@testemail.com " and "Password1"
    Then User successfully logged in to home page

  Scenario: As a User, I want have the ability to create a new Savings bank account so that my payment can be processed from my preferred account.


    And Verify that "Welcome John" welcoming message is displayed
    And Verify that "Savings" dropdown has following options
      | View Savings |
      | New Savings  |


  Scenario: Savings account page validations
    When User clicks on "Savings" account
    Then Verify "New Savings Account" header is displayed
    And  Verify "Select Checking Account Type" label is displayed
    And Verify that radio buttons are unchecked
    Then Verify "Select Account Ownership" label is displayed
    Then Verify "Account Name" label is displayed
    Then Verify "Initial Deposit" label is displayed
    And Verify that "Submit" button is displayed
    And Verify that "Reset" button is displayed

  Scenario: Savings account page validations without account type

    When User clicks on "Savings" account
    When User submits an account without Account Type
    Then Verify field error message "Please select one of these options." is displayed


  Scenario: Savings account page validations without account ownership

    When User clicks on "Savings" account
    When User submits an account without Account Ownership
    Then Verify field error message "Please select one of these options." is displayed


  Scenario: Savings account page validations without account name

    When User clicks on "Savings" account
    When User submits an account without Account Name
    Then Verify field error message "Please fill out this field." is displayed


  Scenario: Savings account page validations without account deposit

    When User clicks on "Savings" account
    When User submits an account without Initial Deposit Amount
    Then Verify field error message "Please fill out this field." is displayed


  Scenario Outline: Savings account page validations with Invalid account deposit

    When User clicks on "Savings" account
    When User submits an account with "<deposit>" deposit
    Then Verify field error message "<errorMsg>" is displayed
    Examples:
      | deposit | errorMsg                           |
      | abc123  | Please match the requested format. |
      | @#$1q   | Please match the requested format. |
      | 200,33  | Please match the requested format. |

  Scenario Outline: Savings account page validations with less then minimum account deposit

    When User clicks on "Savings" account
    When User submits an account with "<deposit>" deposit
    Then Verify alert error message "The initial deposit ($<deposit>) entered does not meet the minimum amount ($2500.00) required. Please enter a valid deposit amount." is displayed
    Examples:
      | deposit |
      | 150.22  |

  Scenario: Create a Savings account with valid data

    When User clicks on "Savings" account
    When User creates "Savings" account with following info:
      | accountType  | accountOwnership | accountName  | initialDeposit |
      | Money Market | Joint            | Test Account | 2500           |
    Then Verify newly created account information