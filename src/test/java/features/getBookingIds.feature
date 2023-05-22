Feature: Get Booking Ids

  Scenario: Get Booking All
    Given url baseUrl
    When method get
    Then status 200
    * print response