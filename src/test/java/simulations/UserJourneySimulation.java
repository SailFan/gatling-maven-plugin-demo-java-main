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

    ScenarioBuilder scenario = scenario("获取SPU")
            .exec(APiEndpoints.spu)
            .exec(session -> {
                long t = System.currentTimeMillis();
                System.out.println( "🕐 USER STARTED: scenario=" + session.scenario() + ", userId=" + session.userId() + ", time=" + t + " ms" );
                return session;
            });


    {
        // 注入虚拟用户
        setUp(
                scenario.injectOpen(
                        constantUsersPerSec(1).during(5),
                        nothingFor(5),
                        rampUsers(20).during(5)
//                        constantUsersPerSec(1).during(5)
//                        nothingFor(5),                    // 等待5秒
//                        atOnceUsers(1)                 // 立即启动1个用户
//                        rampUsers(5).during(30)      // 30秒内增加到5个用户
//                        constantUsersPerSec(1).during(60) // 保持1用户/秒，持续1分钟

//                        rampUsers(10).during(30),        // 30秒内增加到10个用户
//                        constantUsersPerSec(2).during(120), // 2用户/秒，持续2分钟
//                        rampUsers(20).during(30),        // 30秒内增加到20个用户
//                        constantUsersPerSec(3).during(60)// 3用户/秒，持续1分钟
                ) // 同时注入 1 个虚拟用户
        ).protocols(httpProtocol);
    }

}
