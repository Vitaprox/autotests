package partTwo.restLesson1;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class Homework {

    @Test
    public void task1() {
        RestAssured.given()
                .log().method()
                .log().uri()
                .get("http://172.24.120.5:8081/api/users/Dtest/notes/archive")
                .then()
                .statusCode(403)
                .log().body();
    }
}
