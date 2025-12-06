Feature: Employee search API

  Background:
    * def baseUrl = karate.get('baseUrl')

  # When unauthenticated the API should reject the request.
  Scenario: Search employees without authentication
    Given url baseUrl + '/web/index.php/api/v1/employee/search'
    When method get
    Then status 401