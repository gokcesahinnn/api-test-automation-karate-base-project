Feature:Create Booking Feature

  Background:
    Given url baseUrl
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    * def createData = read(modelPath + "/createBooking/createBookingData.json")

  Scenario Outline: Create Booking With Csv - <name>
    * createData.firstname = name
    And request createData
    When method post
    Then status 200
    And match response.booking == createData
    And match response.bookingid == "#number"
    Examples:
      | read(dataPath + "/user/userName.csv") |