package randomapi

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class RandomAPILoadTest extends Simulation {
  val protocol: HttpProtocolBuilder = http.baseUrl("http://localhost:8080/")
  val scn: ScenarioBuilder = scenario("Load testing of Random Number API")
    .exec(
      http("Get Random Number")
        .get("api/random")
        .check(status.is(200))
    )

  val duringSeconds: Integer = Integer.getInteger("duringSeconds", 10)
  val constantUsers: Integer = Integer.getInteger("constantUsers", 10)
  setUp(scn.inject(constantConcurrentUsers(constantUsers) during (duringSeconds))
    .protocols(protocol))
    .maxDuration(1800)
    .assertions(global.responseTime.max.lt(20000), global.successfulRequests.percent.gt(95))
}