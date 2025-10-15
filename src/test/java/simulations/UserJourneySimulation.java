package simulations;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import ApiEndpoints.APiEndpoints;
import data.UserDataFeeder;
import groups.UserJourneyGroup;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;



import static io.gatling.javaapi.core.CoreDsl.*;
public class UserJourneySimulation extends Simulation {
    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:3000")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
            .disableCaching();


    ScenarioBuilder scn = scenario("User Journey")
            .feed(UserDataFeeder.userFeeder)
            .exec(UserJourneyGroup.createUserJourneyGroup())
            .group("首页").on(exec(APiEndpoints.spu).exec(APiEndpoints.sku));

    {
        // 注入虚拟用户
        setUp(
                scn.injectOpen(atOnceUsers(1)) // 同时注入 1 个虚拟用户
        ).protocols(httpProtocol);
    }

}
