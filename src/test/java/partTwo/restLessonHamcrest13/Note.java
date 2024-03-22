package partTwo.restLessonHamcrest13;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Note {

    private int id;
    private String name;
    private String content;
    private String color;
    private int priority;
}
