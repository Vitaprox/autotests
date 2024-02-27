package partTwo.restLesson;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

public class ExampleRestAssured {

    @Test
    public void exampleRestAssuredTest() {
        RestAssured.given()
                .log().method()
                .log().uri()
                .get("http://172.24.120.5:8081/api/users/login_user_lesson/notes/archive")
                .then()
                .log().all();
//                .andReturn()
//                .prettyPrint();
    }

    @Test
    public void createUserTest() {
//        RestAssured.given()
//                .get("https://randomuser.me/api/")
//                .then().log().ifValidationFails().statusCode(400);
        User user1 = new User.Builder()
                .withLogin("login1")
                .withPassword("111")
                .build();

        Note note1 = new Note.Builder()
                .withTitle("title1")
                .build();
        Note note2 = new Note.Builder()
                .withText("text2")
                .build();
        Note note3 = new Note.Builder()
                .withTitle("Title3")
                .withText("title3")
                .build();

        System.out.println(note1.getTitle() + " " + note1.getText());
        System.out.println(note2.getTitle() + " " + note2.getText());
        System.out.println(note3.getTitle() + " " + note3.getText());


    }
}
