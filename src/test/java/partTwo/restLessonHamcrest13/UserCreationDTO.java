package partTwo.restLessonHamcrest13;

import lombok.Getter;
import lombok.Setter;
import partTwo.restLessonHamcrest13.Note;
import pojo.Role;

import java.util.List;

@Getter
@Setter
public class UserCreationDTO {

    private int id;
    private String login;
    private String password;
    private String email;
    private List<Note> notes;
    private List<Role> roles;
}
