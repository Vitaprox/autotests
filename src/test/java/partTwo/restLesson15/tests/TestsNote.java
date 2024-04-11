package partTwo.restLesson15.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import partTwo.restLesson15.steps.DBSteps;
import partTwo.restLesson15.objects.Note;
import partTwo.restLesson15.objects.NoteCreationDTO;
import partTwo.restLesson15.objects.User;
import partTwo.restLesson15.steps.Steps;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Проверка заметок")
public class TestsNote {

    private Steps steps = new Steps();
    DBSteps db = new DBSteps();
    private ResponseSpecification responseSpecification;
    private RequestSpecification requestSpecification;
    private User newUser;
    private NoteCreationDTO noteCreationDTO;
    private Note newNote;
    private String token;

    @Before
    public void before(){
        newUser = new User().generateUser();
        newNote = new Note().generateNote();
        db.createUserWithStandardPassword(newUser.getLogin());
    }

    @Test
    @DisplayName(value = "Проверка создания заметки")
    public void createNoteWithAllFields() {
        noteCreationDTO = NoteCreationDTO.builder()
                .name(newNote.getName())
                .content(newNote.getContent())
                .color(newNote.getColor())
                .priority(newNote.getPriority())
                .build();
        ArrayList<NoteCreationDTO> notes = new ArrayList<>();
        notes.add(noteCreationDTO);
        token = steps.getToken(newUser.getLogin(), newUser.getPassword());

        requestSpecification = steps.createRequestSpecificationCreateNote(token, newUser.getLogin(), notes);
        responseSpecification = steps.createResponseSpecificationRegistration(201);
        steps.postRegistration(requestSpecification, responseSpecification);
    }

    @Test
    @DisplayName(value = "Проверка редактирования заметки")
    public void editNote() {
        int userId = db.getUserId(newUser.getLogin());
        db.addRandomNote(userId);
        int noteId = db.getLastNoteId(userId);
        int random = 100 + (int) (Math.random() * 1000);
        noteCreationDTO = NoteCreationDTO.builder()
                .id(noteId)
                .name("editName" + random)
                .content(newNote.getContent())
                .color(newNote.getColor())
                .priority(newNote.getPriority())
                .build();

        token = steps.getToken(newUser.getLogin(), newUser.getPassword());

        ArrayList<NoteCreationDTO> notes = new ArrayList<>();
        notes.add(noteCreationDTO);

        requestSpecification = steps.createRequestSpecificationEditNote(token, newUser.getLogin(), notes);
        responseSpecification = steps.createResponseSpecificationRegistration(204);
        steps.putEditNote(requestSpecification, responseSpecification);
    }

    @Test
    @DisplayName(value = "Проверка архивирования заметки")
    public void archiveNote() {

        int userId = db.getUserId(newUser.getLogin());
        db.addRandomNote(userId);
        int noteId = db.getLastNoteId(userId);
        List<Object> fields = db.getNoteFields(noteId);

        noteCreationDTO = NoteCreationDTO.builder()
                .id(noteId)
                .name((String) fields.get(0))
                .content((String) fields.get(1))
                .color((String) fields.get(2))
                .priority((Integer) fields.get(3))
                .archiveFlag(true)
                .build();

        token = steps.getToken(newUser.getLogin(), newUser.getPassword());

        ArrayList<NoteCreationDTO> notes = new ArrayList<>();
        notes.add(noteCreationDTO);

        requestSpecification = steps.createRequestSpecificationEditNote(token, newUser.getLogin(), notes);
        responseSpecification = steps.createResponseSpecificationRegistration(204);
        steps.putEditNote(requestSpecification, responseSpecification);
    }

    @Test
    @DisplayName(value = "Проверка удаления заметки")
    public void deleteNote() {
        int userId = db.getUserId(newUser.getLogin());
        db.addRandomNote(userId);
        int noteId = db.getLastNoteId(userId);

        token = steps.getToken(newUser.getLogin(), newUser.getPassword());

        requestSpecification = steps.createRequestSpecificationDeleteNote(noteId, token, newUser.getLogin());
        responseSpecification = steps.createResponseSpecificationRegistration(204);
        steps.delete(requestSpecification, responseSpecification);
    }
}
