package partTwo.restLesson;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

public class ExampleRestAssured {

    @Test
    public void exampleRestAssuredTest() {
        RestAssured.given()
                .log().method()
                .log().uri()
                .get("https://randomuser.me/")
                .andReturn()
                .prettyPrint();
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
