Feature: Add and verify employee

  # This scenario automates the process of logging into the OrangeHRM demo site,
  # creating a new employee with basic details, uploading a profile photo and
  # then searching for the newly created employee in the Directory module.  It
  # follows the flow described in the Agile Testing kata: navigate to PIM,
  # create an employee, upload a photo, move to Directory and validate the
  # stored information.

  Scenario: Create and validate a new employee
    Given the admin user logs into OrangeHRM
    When they add a new employee with first name "John" and last name "Doe"
    And they upload a profile photo for the employee
    And they search for the employee named "John Doe" in the directory
    Then the employee "John Doe" should appear in the search results