Feature: Add New Employee
      As an Admin
      I want to add new employees via the PIM module
      So that their records are stored in the system

    Background: User can go to PIM page and logout successful
          Given The OrangeHRM login page is launched
          When Enter valid username and valid password
          And Click the login button
          Then The login should be successful, and the Dashboard page is shown
          And Go to the PIM page
          Then User logout

    Scenario Outline: Add employee with valid details
          When I enter first name "<firstName>" last name "<lastName>"
          And I enter employeeId "<employeeId>"
          And I upload employee avatar
          And I click the save button
          Then I should be redirect to PersonalDetails page
          And the employee details page should display first name "<firstName>"

          Examples:
          | firstName | lastName | employeeId |
          |John       |Doe       |09291607    |
          |Jane       |Smith     |09291608    |
          |Mike       |Johnson   |09291609    |


