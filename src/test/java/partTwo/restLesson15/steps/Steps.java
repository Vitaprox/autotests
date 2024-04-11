package partTwo.restLesson15.steps;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import partTwo.restLesson15.objects.NoteCreationDTO;
import partTwo.restLesson15.objects.User;
import partTwo.restLesson15.objects.UserCreationDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static partTwo.restLesson15.Properties.*;

public class Steps {

    public RequestSpecification createRequestSpecificationEditNote(String token, String login, ArrayList<NoteCreationDTO> notes) {
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri(BASE_URI)
                .setBasePath(PATH_NOTE_1 + login + PATH_NOTE_2)
                .setContentType(ContentType.JSON)
                .setBody(notes)
                .build();
    }

    public RequestSpecification createRequestSpecificationCreateNote(String token, String login, ArrayList<NoteCreationDTO> notes) {
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri(BASE_URI)
                .setBasePath(PATH_NOTE_1 + login + PATH_NOTE_2)
                .setContentType(ContentType.JSON)
                .setBody(notes)
                .build();
    }

    public String getToken(String login, String password) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", password);
        formParams.put("username", login);
        JsonPath response = RestAssured.given()
                .formParams(formParams)
                .baseUri(BASE_URI)
                .basePath(PATH_LOGIN)
                .get()
                .jsonPath();
        return response.get("access_token");
    }

    public void putEditNote(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        RestAssured.given(requestSpecification).log().all()
                .put()
                .then().log().all().spec(responseSpecification);
    }

    public void postRegistration(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        RestAssured.given(requestSpecification).log().all()
                .post()
                .then().log().all().spec(responseSpecification);
    }

    public void postRegistrationFail(String message, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        RestAssured.given(requestSpecification).log().all()
                .post()
                .then().log().all().spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("registration_error_schema.json"))
                .body("level", equalTo("ERROR"),
                        "message", equalTo(message));
    }

    public ResponseSpecification createResponseSpecificationRegistration(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public RequestSpecification createRequestSpecificationRegistration(UserCreationDTO userCreationDTO) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(PATH_REGISTRATION)
                .setContentType(ContentType.JSON)
                .setBody(userCreationDTO)
                .build();
    }


    public RequestSpecification createRequestSpecificationDeleteNote(int noteId, String token, String login) {
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri(BASE_URI)
                .setBasePath(PATH_NOTE_1 + login + PATH_NOTE_2 + noteId)
                .setContentType(ContentType.JSON)
                .build();
    }

    public void delete(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        RestAssured.given(requestSpecification).log().all()
                .delete()
                .then().log().all().spec(responseSpecification);
    }

}
