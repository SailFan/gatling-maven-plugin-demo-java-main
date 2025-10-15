package ApiEndpoints;


import static io.gatling.javaapi.core.CoreDsl.StringBody;

import io.gatling.javaapi.core.Session;

import io.gatling.javaapi.http.HttpRequestActionBuilder;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class APiEndpoints {


   //获取spu列表
   public static final HttpRequestActionBuilder spu = http("spu")
           .get("/api/skulist")
           .check(status().is(200));



    public static final HttpRequestActionBuilder sku = http("sku")
            .post("/api/addToCart")
            .body(StringBody((Session session) -> {
                // 从 Session 中读取值（确保 Simulation 里事先 set 了这两个值）
                int productId = session.getInt("productId");
                String username = session.getString("username");

                // 返回 JSON 字符串
                return String.format(
                        "{\"productId\":%d,\"username\":\"%s\",\"quantity\":1}",
                        productId,
                        username);
            }))
            .asJson()
            .check(status().is(200));

}