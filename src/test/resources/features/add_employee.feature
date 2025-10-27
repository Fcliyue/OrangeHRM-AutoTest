Feature: Add New Employee
      As an Admin
      I want to add new employees via the PIM module
      So that their records are stored in the system

    Background: User logs in and navigates to PIM page
          Given The OrangeHRM login page is launched
          When Enter valid username and valid password
          And Click the login button
          Then The login should be successful, and the Dashboard page is shown
          And Go to the PIM page

    Scenario Outline: Add employee with valid details
          When Add Employee page is displayed
          Then I enter first name "<firstName>" last name "<lastName>"
          And I enter employeeId "<employeeId>"
          And I upload employee avatar
          And I click the save button
          Then I should be redirected to PersonalDetails page
          And the employee details page should display first name "<firstName>"

          Examples:
          | firstName | lastName | employeeId |
          |John1       |Doe1       |090877305    |
#          |Mick       |Charil    |092916232    |
#          |Rose       |Lin       |0929160903    |



