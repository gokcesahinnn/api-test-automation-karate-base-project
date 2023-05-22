Feature: Get Booking Caller

  Background:
    * def bookingId = karate.get("__arg.bookingId", "23886")

  @get_booking_ids_caller
  Scenario: Get booking With Parameter
    Given url baseUrl
    And path bookingId
    And header Accept = 'application/json'
    When method get
    Then status 200
