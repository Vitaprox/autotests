package partTwo.restLessonHamcrest13;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NotesDTO {
    private List<Note> notes;
}
