package partTwo.restLessonHamcrest12;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class Homework {

    private String token;

    @Before
    public void before() {
        executeQuery("DELETE FROM nfaut.notes WHERE user_id=6;");
        executeQuery("INSERT INTO nfaut.notes (id, user_id, name, content, priority, archive_flg) VALUES(561, 6, " +
                "'Title1', 'Text 2', 1, false);");
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", "Dtest");
        formParams.put("username", "Dtest");
        JsonPath response = RestAssured.given()
                .formParams(formParams)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();
        token = response.get("access_token");
    }

    @After
    public void after() {
        executeQuery("DELETE FROM nfaut.notes WHERE user_id=6;");
    }

    @Test
    public void homework() {
        RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get("http://172.24.120.5:8081/api/users/Dtest/notes")
                .then().log().body()
                .statusCode(200)
                .body("[0].name", equalTo("Title1"),
                        "[0].name", equalToIgnoringCase("title1"),
                        "[0].content", equalToCompressingWhiteSpace("Text    2"),
                        "[0].content", containsString("ext"),
                        "[0].content", startsWith("Te"),
                        "[0].content", endsWith("2"),
                        "[0].priority", equalTo(1),
                        "[0].priority", greaterThan(0),
                        "[0].priority", greaterThanOrEqualTo(1),
                        "[0].priority", lessThan(2),
                        "[0].priority", lessThanOrEqualTo(2),
                        "[0].priority", not(equalTo(2)));
    }

    private void executeQuery(String SQL) {
        String url = "jdbc:postgresql://172.24.120.5:5432/postgres";
        String login = "root";
        String password = "root";
        try {
            Connection connection= DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(SQL);
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
