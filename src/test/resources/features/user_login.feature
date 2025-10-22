Feature: OrangeHRM Login Functionality Test
  As an end user
  I want to log into the OrangeHRM system
  So that I can access the system features

  #Scenario 1: User can go to PIM page and logout successful
  Scenario: User can go to PIM page and logout successful
    Given The OrangeHRM login page is launched
    When Enter valid username and valid password
    And Click the login button
    Then The login should be successful, and the Dashboard page is shown
    And Go to the PIM page
    Then User logout

  #Scenario 1: Successful login with valid credentials
#  Scenario: Login successfully with correct username and password
#    Given The OrangeHRM login page is launched
#    When Enter username "Admin" and password "admin123"
#    And Click the login button
#    Then The login should be successful, and the Dashboard page is shown

  #Scenario 2:Failed login with invalid credentials
#  Scenario: Login failed with incorrect password
#    Given The OrangeHRM login page is launched
#    When Enter username "Admin" and password "Invalid"
#    And Click the login button
#    Then The login should fail, and error message "Invalid credentials" should be displayed

  #Data-driven scenario: Test login with multiple credential sets
#  Scenario Outline: Login with multiple with multiple credential sets
#    Given The OrangeHRM login page is launched
#    When Enter username '<username>' and password '<password>'
#    And Click the login button
#    Then The login result should match expectation:"<expectedResult>" with message:"<expectedMessage>"
#
    #test data table:
#    Examples:
#    | username | password | expectedResult | expectedMessage          | #message
#    | Admin    | admin123 | Success        | OrangeHRM                | #valid login (title check)
#    | Admin    | invalid  | Failure        | Invalid credentials      | #Wrong password
#    |          | admin123 | Failure        | Required                 | #Empty username
#    | Admin    |          | Failure        | Required                 | #Empty password

