package partTwo.restLessonDTO11;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import pojo.UserCreationDTO;
import pojo.User;

public class Homework {
    @Test
    public void test() {
        User newUser = new User();
        newUser.setLogin("test_login1264215");
        newUser.setPassword("123");
        newUser.setEmail("test@mail24.ru");
        newUser.setDefaultRole();
        newUser.setDefaultNote();

        // Создание DTO
        UserCreationDTO userCreationDTO = new UserCreationDTO();
        userCreationDTO.setLogin(newUser.getLogin());
        userCreationDTO.setPassword(newUser.getPassword());
        userCreationDTO.setEmail(newUser.getEmail());
        userCreationDTO.setRoles(newUser.getRoles());
        userCreationDTO.setNotes(newUser.getNotes());

        RestAssured.given().log().all()
                .body(userCreationDTO)
                .contentType("application/json")
                .post("http://172.24.120.5:8081/api/registration")
                .then().log().all()
                .statusCode(201);

    }
}
