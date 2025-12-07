Feature: Add and verify employee



  Scenario: Create and validate a new employee
    Given the admin user logs into OrangeHRM
    When they add a new employee with first name "Andres", last name "Bernal" and a profile photo
    And they search for the employee named "Andres Bernal" in the directory
    Then the employee "Andres Bernal" should appear in the search results