package partTwo.restLesson5;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Homework {

    @Test
    public void classwork() {
        Map<String, String> params = new HashMap<>();
        params.put("password", "Dtest");
        params.put("username", "Dtest");
        JsonPath response = RestAssured.given()
                .formParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();
        String token = response.get("access_token");

        System.out.println("token " + token);
    }

    @Test
    public void task1_2() {
        String key = "refresh_token";
        Map<String, String> params = new HashMap<>();
        params.put("password", "Dtest");
        params.put("username", "Dtest");
        JsonPath response = RestAssured.given()
                .formParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();
        String token = response.get(key);
        if (token != null) {
            System.out.println(key + " " + token);
        } else {
            System.out.println("Не найден ключ " + key);
        }

    }

}
