Feature:Create Booking Feature

  Background:
    Given url baseUrl
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    * def createData = read(modelPath + "/createBooking/createBookingData.json")

  @smoke
  Scenario: Create Booking
    And request createData
    When method post
    Then status 200
    And match response.booking == createData
    And match response.bookingid == "#number"
    * def createdBooking = call read(callerPath + "/getBooking/getBookingCallers.feature"){"bookingId": #(response.bookingid)}
    Then match createdBooking.response == createData
    And match createdBooking.bookingId == response.bookingid

