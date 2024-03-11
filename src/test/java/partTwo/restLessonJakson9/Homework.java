package partTwo.restLessonJakson9;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import partTwo.restLessonPOJO8.Role;
import pojo.User;

import java.io.File;
import java.io.IOException;

public class Homework {

    @Test
    public void task1() {
        Role newRole = new Role();
        newRole.setId(72);
        newRole.setName("newRole");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("target/user.json"), newRole);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Role createRole = objectMapper.readValue(new File("target/user.json"), Role.class);
            System.out.println(createRole);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void classwork() {
        User newUser = new User();
        newUser.setLogin("test_login");
        newUser.setPassword("123");
        newUser.setEmail("test@login.ru");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("target/user.json"), newUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            User createUser = objectMapper.readValue(new File("target/user.json"), User.class);
            System.out.println(createUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
