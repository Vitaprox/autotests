package partTwo.restLessonBody7;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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


        ArrayList<Map<String, String>> arrayList = new ArrayList<>();
        Map<String,String> request = new HashMap<>();
        request.put("name","name21412");
        request.put("content","content21412");
        request.put("color","#fbbc04");
        request.put("priority","0");
        arrayList.add(request);

        RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .body(arrayList)
                .contentType("application/json")
                .post("http://172.24.120.5:8081/api/users/Dtest/notes")
                .then().log().status()
                .statusCode(201);
    }
}
