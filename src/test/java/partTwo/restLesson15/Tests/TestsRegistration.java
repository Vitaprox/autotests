package partTwo.restLesson15.Tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import partTwo.restLesson15.objects.Note;
import partTwo.restLesson15.objects.NoteCreationDTO;
import partTwo.restLesson15.objects.User;
import partTwo.restLesson15.objects.UserCreationDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static partTwo.restLesson15.Properties.*;

@DisplayName("Проверка регистрации")
public class TestsRegistration {

    private ResponseSpecification responseSpecification;
    private RequestSpecification requestSpecification;
    private UserCreationDTO userCreationDTO;
    private User newUser;
    private NoteCreationDTO noteCreationDTO;
    private Note newNote;
    private String token;

    @Before
    public void before(){
        newUser = new User().generateUser();
        newNote = new Note().generateNote();
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

        setToken("Dtest", "Dtest");

        createRequestSpecificationCreateNote("Dtest", notes);
        createResponseSpecificationRegistration(201);
        postRegistration();
    }

    @Test
    @DisplayName(value = "Проверка регистрации только с обязательными полями")
    public void registrationWithRequiredFieldsTest() {
        userCreationDTO = UserCreationDTO.builder().login(newUser.getLogin())
                .password(newUser.getPassword())
                .build();

        createRequestSpecificationRegistration(userCreationDTO);
        createResponseSpecificationRegistration(201);
        postRegistration();
    }

    @Test
    @DisplayName(value = "Проверка регистрации со всеми полями")
    public void registrationWithAllFieldsTest() {
        userCreationDTO = userCreationDTO.builder().login(newUser.getLogin())
                .password(newUser.getPassword())
                .email(newUser.getEmail())
                .roles(newUser.getRoles())
                .build();

        createRequestSpecificationRegistration(userCreationDTO);
        createResponseSpecificationRegistration(201);
        postRegistration();
    }

    @Test
    @DisplayName(value = "Проверка регистрации только с отправкой логина")
    public void registrationOnlyWithLoginTest() {
        userCreationDTO = userCreationDTO.builder().login(newUser.getLogin()).build();

        createRequestSpecificationRegistration(userCreationDTO);
        createResponseSpecificationRegistration(500);
        postRegistrationFail("Password is required");
    }

    @Test
    @DisplayName(value = "Проверка регистрации только с отправкой пароля")
    public void registrationOnlyWithPasswordTest() {
        userCreationDTO = userCreationDTO.builder().password(newUser.getPassword()).build();

        createRequestSpecificationRegistration(userCreationDTO);
        createResponseSpecificationRegistration(500);
        postRegistrationFail("Login is required");
    }

    private void createRequestSpecificationCreateNote(String login, ArrayList<NoteCreationDTO> notes) {
        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri(BASE_URI)
                .setBasePath(PATH_NOTE_1 + login + PATH_NOTE_2)
                .setContentType(ContentType.JSON)
                .setBody(notes)
                .build();
    }

    private void setToken(String login, String password) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", password);
        formParams.put("username", login);
        JsonPath response = RestAssured.given()
                .formParams(formParams)
                .baseUri(BASE_URI)
                .basePath(PATH_LOGIN)
                .get()
                .jsonPath();
        token = response.get("access_token");
    }

    private void postRegistration() {
        RestAssured.given(requestSpecification).log().all()
                .post()
                .then().log().all().spec(responseSpecification);
    }

    private void postRegistrationFail(String message) {
        RestAssured.given(requestSpecification).log().all()
                .post()
                .then().log().all().spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("registration_error_schema.json"))
                .body("level", equalTo("ERROR"),
                        "message", equalTo(message));
    }

    private void createResponseSpecificationRegistration(int status) {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    private void createRequestSpecificationRegistration(UserCreationDTO userCreationDTO) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(PATH_REGISTRATION)
                .setContentType(ContentType.JSON)
                .setBody(userCreationDTO)
                .build();
    }
}
