@get_booking_ids_caller
Feature: Get Booking

  Background:
    Given url baseUrl

  @get_booking_id
  Scenario: Get booking With Parameter
    When method get
    Then status 200
    * def firstBookingId = response[0].bookingid