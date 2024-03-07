package partTwo.restLesson6;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Homework {

    @Test
    public void task1() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", "Dtest");
        formParams.put("username", "Dtest");
        JsonPath response = RestAssured.given()
                .formParams(formParams)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();
        String token = response.get("access_token");

        RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .get("http://172.24.120.5:8081/api/users/Dtest/notes/archive")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void task2() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", "Dtest");
        formParams.put("username", "Dtest");
        JsonPath response = RestAssured.given()
                .formParams(formParams)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();
        String token = response.get("access_token");

        RestAssured.given()
                .log().all()
                .cookie("cookieName", "cookieValue")
                .header("Authorization", "Bearer " + token)
                .get("http://172.24.120.5:8081/api/users/Dtest/notes/archive")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void task3() {
        Map<String, String> cookies = new HashMap<>();
        cookies.put("cookieName1", "cookieValue1");
        cookies.put("cookieName2", "cookieValue2");
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", "Dtest");
        formParams.put("username", "Dtest");
        JsonPath response = RestAssured.given()
                .formParams(formParams)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();
        String token = response.get("access_token");

        RestAssured.given()
                .log().all()
                .cookies(cookies)
                .header("Authorization", "Bearer " + token)
                .get("http://172.24.120.5:8081/api/users/Dtest/notes/archive")
                .then().log().all()
                .statusCode(200);
    }

}
