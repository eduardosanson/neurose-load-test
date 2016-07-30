/**
  * Created by eduardo.sanson on 29/07/2016.
  */
import io.gatling.core.Predef._ // 2
import io.gatling.http.Predef._
import scala.concurrent.duration._

class OrderSimulation  extends Simulation{
  val httpConf = http // 4
    .baseURL("http://neurose-void.herokuapp.com") // 5

    val cenario = scenario("createOrders")
      .exec(http("request_1")
        .post("/orders")
        .body(ElFileBody("jsonFile.json"))
        .asJSON) // 10

  setUp( // 11
//    cenario.inject(atOnceUsers(1000)) // 12
//    cenario.inject(rampUsers(15000) over(1 seconds))
    cenario.inject(constantUsersPerSec(5) during(1))
  ).protocols(httpConf) // 13

}
