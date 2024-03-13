package partTwo.restLessonHamcrest13;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.User;
import pojo.UserCreationDTO;
import pojo.UserDTO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Homework {

    private String token;

    private int random = (int) (Math.random() * 1000);
    private String login = "login_Dtest" + random;
    private String password = "123";
    private String email = "Dtest@mail.ru";
    private String noteName = "title";
    private String content = "text";
    private String color = "#d7aefb";
    private int priority = 0;

    private int roleId = 23;
    private String roleName = "user";



    @Before
    public void before() {
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setRole(roleId, roleName);
        newUser.setNote(noteName, content, color, priority);

        // Создание DTO
        UserCreationDTO userCreationDTO = new UserCreationDTO();
        userCreationDTO.setLogin(newUser.getLogin());
        userCreationDTO.setPassword(newUser.getPassword());
        userCreationDTO.setEmail(newUser.getEmail());
        userCreationDTO.setRoles(newUser.getRoles());
        userCreationDTO.setNotes(newUser.getNotes());

        RestAssured.given()
                .body(userCreationDTO)
                .contentType("application/json")
                .post("http://172.24.120.5:8081/api/registration")
                .then()
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
    public void task1() {

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
                .assertThat();
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("me_schema.json"));

//        Assert.assertEquals(expectedUser, actualUser);
    }

}
