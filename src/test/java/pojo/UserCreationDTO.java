package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserCreationDTO {

    private String login;
    private String password;
    private String email;
    private List<Note> notes;
    private List<Role> roles;
}
