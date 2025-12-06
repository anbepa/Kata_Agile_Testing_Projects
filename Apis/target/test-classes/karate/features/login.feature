Feature: Login API

  Background:
    * def baseUrl = karate.get('baseUrl')

  Scenario: Successful login returns user details
    Given url baseUrl + '/web/index.php/api/v1/login'
    And request { username: 'Admin', password: 'admin123' }
    When method post
    Then status 200
    And match response.login == true
    And match response.user.userName == 'Admin'

  Scenario: Login with invalid credentials fails
    Given url baseUrl + '/web/index.php/api/v1/login'
    And request { username: 'invalid', password: 'invalid' }
    When method post
    Then status 200
    And match response.login == false