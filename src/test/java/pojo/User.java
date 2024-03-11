package pojo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
public class User {

    private String login;
    private String password;
    private String email;
    private List<Note> notes;
    private List<Role> roles;

    public void setDefaultRole() {
        Role defaultRole = new Role();
        defaultRole.setId("2");
        defaultRole.setName("ROLE_USER");

        List<Role> defaultListRole = new ArrayList<>();
        defaultListRole.add(defaultRole);

        this.roles = defaultListRole;
    }

    public void setDefaultNote() {
        Note defaultNote = new Note();
        defaultNote.setName("NoteName");
        defaultNote.setContent("NoteCont");
        defaultNote.setColor("#d7aefb");
        defaultNote.setPriority("0");

        List<Note> defaultListNote = new ArrayList<>();
        defaultListNote.add(defaultNote);

        this.notes = defaultListNote;
    }
}
