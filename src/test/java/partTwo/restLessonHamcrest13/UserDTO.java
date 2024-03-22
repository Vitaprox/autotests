package partTwo.restLessonHamcrest13;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {

    @JsonIgnore
    private Integer id;
    private String login;
    private String email;

}
