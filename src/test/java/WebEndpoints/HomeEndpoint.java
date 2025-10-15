package WebEndpoints;

import io.gatling.javaapi.http.HttpRequestActionBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class HomeEndpoint {
    public static final HttpRequestActionBuilder homePage = http("HomePage")
            .get("http://localhost:3000/")
            .check(status().in(200, 304));

}