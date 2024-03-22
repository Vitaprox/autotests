package partTwo.restLessonHamcrest13;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import partTwo.restLessonHamcrest13.User;
import partTwo.restLessonHamcrest13.UserCreationDTO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Homework {

    private String token;

    private int random = 400 + (int) (Math.random() * 1000);
    private String login = "login_Dtest" + random;
    private String password = "123";
    private String email = "Dtest@mail.ru";
    private int noteId = 653;
    private String noteName = "title";
    private String content = "text";
    private String color = "#d7aefb";
    private int priority = 1;

    private int roleId = 23;
    private String roleName = "ROLE_USER";



    @Before
    public void before() {

        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setRole(roleId, roleName);
        newUser.setNote(noteId, noteName, content, color, priority);

        // Создание DTO
        UserCreationDTO userCreationDTO = new UserCreationDTO();
        userCreationDTO.setId(random);
        userCreationDTO.setLogin(newUser.getLogin());
        userCreationDTO.setPassword(newUser.getPassword());
        userCreationDTO.setEmail(newUser.getEmail());
        userCreationDTO.setRoles(newUser.getRoles());
        userCreationDTO.setNotes(newUser.getNotes());

        RestAssured.given()
                .body(userCreationDTO)
                .contentType("application/json")
                .log().all()
                .post("http://172.24.120.5:8081/api/registration")
                .then()
                .log().all()
                .statusCode(201);

        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", password);
        formParams.put("username", login);
        JsonPath response = RestAssured.given()
                .formParams(formParams)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();
        token = response.get("access_token");
    }


    @Test
    public void task2_1() {
        RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get("http://172.24.120.5:8081/api/users/" + login + "/notes")
                .then()
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("one_note_schema.json"));
    }

    @Test
    public void task2_2() {
        UserDTO expectedUser = new UserDTO();
        expectedUser.setLogin(login);
        expectedUser.setEmail(email);

        UserDTO actualUser = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get("http://172.24.120.5:8081/api/users/me")
                .then()
                .statusCode(200).extract().body().as(UserDTO.class);

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void classwork() throws IOException {
//        UserDTO expectedUser = new UserDTO();
//        expectedUser.setId(6);
//        expectedUser.setLogin("Dtest");
//        expectedUser.setEmail("");


//        UserDTO expectedUser = new ObjectMapper().readValue(new File("target/me.json"), UserDTO.class);
        System.out.println(token);
        RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get("http://172.24.120.5:8081/api/users/me")
                .then()
                .statusCode(200)
                .log().body()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("me_schema.json"));

//        Assert.assertEquals(expectedUser, actualUser);
    }

}
