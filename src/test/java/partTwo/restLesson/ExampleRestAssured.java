package partTwo.restLesson;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

public class ExampleRestAssured {

    @Test
    public void task2() {
        Map<String, String> params = new HashMap<>();
        params.put("password", "Dtest");
        params.put("username", "Dtest");
        RestAssured.given()
                .formParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void task1() {
        String login = "Dtest";
        String name = "task";
        String count = "1";

        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", "Dtest");
        formParams.put("username", "Dtest");
        JsonPath response = RestAssured.given()
                .formParams(formParams)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();
        String token = response.get("access_token");

        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("count", count);
        RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .queryParams(params)
                .get("http://172.24.120.5:8081/api/users/" + login + "/notes")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void createNoteTest() {
        Note note1 = new Note.Builder()
                .withTitle("Title1")
                .withText("text1")
                .build();
        Note note2 = new Note.Builder()
                .withTitle("Title2")
                .withText("text2")
                .build();
        Note note3 = new Note.Builder()
                .withTitle("Title3")
                .withText("text3")
                .build();

        System.out.println(note1.getTitle() + " " + note1.getText());
        System.out.println(note2.getTitle() + " " + note2.getText());
        System.out.println(note3.getTitle() + " " + note3.getText());


    }
}
