package performanceRunners

import com.intuit.karate.gatling.PreDef.{karateFeature, karateProtocol}
import io.gatling.core.Predef.{Simulation, constantUsersPerSec, openInjectionProfileFactory, rampUsers, scenario}

import scala.concurrent.duration.{Duration, SECONDS}

// run command line: mvn test-compile gatling:test -Dgatling.simulationClass=performanceRunners.bookingApiSimulationWithCsv -DuserCount=5 -Dduration=10 -DconstantDuration=10 -DconstantUser=5
// run command line: mvn test-compile gatling:test -Dgatling.simulationClass=performanceRunners.bookingApiSimulationWithCsv -DuserCount=5 -Dduration=10
class performanceTestingSimulation extends Simulation {
  val protocol = {
    karateProtocol()
  }

  val userCount = System.getProperty("userCount")
  val duration = System.getProperty("duration")
  val constantDuration = System.getProperty("constantDuration")
  val constantUser = System.getProperty("constantUser")
  val createBooking = scenario("Create Booking Data With Csv").exec(karateFeature("classpath:features/bookingPostWithCsv.feature"))


  // Load Model 1
  // mvn test -compile gatling: test - Dgatling.simulationClass = performanceRunners.bookingApiSimulationWithCsv - DuserCount = 5 - Dduration = 10 - DconstantDuration = 10 - DconstantUser = 5
  setUp(
    createBooking.inject(rampUsers(userCount.toInt) during Duration(duration.toInt, SECONDS),
      constantUsersPerSec(constantUser.toInt) during Duration(constantDuration.toInt, SECONDS)).protocols(protocol)
  )

  // Load Model 2
  // mvn test-compile gatling:test -Dgatling.simulationClass=performanceRunners.bookingApiSimulationWithCsv -DuserCount=5 -Dduration=10
  setUp(
    createBooking.inject(rampUsers(userCount.toInt) during Duration(duration.toInt, SECONDS)).protocols(protocol)
  )

}