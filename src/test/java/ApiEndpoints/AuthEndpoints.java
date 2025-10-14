package ApiEndpoints;


import io.gatling.core.body.StringBody;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

import static io.gatling.javaapi.core.CoreDsl.ElFileBody;
import static io.gatling.javaapi.http.HttpDsl.http;

public class AuthEndpoints {

    public static final HttpRequestActionBuilder auth = http("User Login")
            .post("/api/login")
            .body(ElFileBody("bodies/login.json"))
            .asJson()
            .header("Content-Type", "application/json");

}