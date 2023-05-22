package performanceRunners

import com.intuit.karate.gatling.PreDef.{karateFeature, karateProtocol}
import io.gatling.core.Predef.{Simulation, configuration, global, openInjectionProfileFactory, rampUsers, scenario}

import scala.concurrent.duration.{Duration, SECONDS}

// Performance Testing With Assertion
// mvn clean test-compile gatling:test -Dgatling.simulationClass=performanceRunners.createBookingWithCsvSimulation -DuserCount=5 -Dduration=10 -DresponseTime=50 -DsuccessfulRequests=95

class performanceTestingSimulationWithAssertion extends Simulation {
  val protocol = {
    karateProtocol()
  }

  val userCount = System.getProperty("userCount")
  val duration = System.getProperty("duration")
  val responseTime = System.getProperty("responseTime")
  val successfulRequests = System.getProperty("successfulRequests")
  val firstPerformanceTest = scenario("First Performance Test").exec(karateFeature("classpath:features/createBookingWithCsv.feature"))

  setUp(
    firstPerformanceTest.inject(rampUsers(userCount.toInt) during Duration(duration.toInt, SECONDS)).protocols(protocol)
  ).assertions(
    global.responseTime.max.lt(responseTime.toInt),
    global.successfulRequests.percent.gt(successfulRequests.toInt)
  )

}
