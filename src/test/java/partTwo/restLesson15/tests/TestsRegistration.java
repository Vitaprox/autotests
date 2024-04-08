package partTwo.restLesson15.tests;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import partTwo.restLesson15.objects.User;
import partTwo.restLesson15.objects.UserCreationDTO;
import partTwo.restLesson15.steps.Steps;

@DisplayName("Проверка регистрации")
public class TestsRegistration {

    private Steps steps = new Steps();

    private ResponseSpecification responseSpecification;
    private RequestSpecification requestSpecification;
    private UserCreationDTO userCreationDTO;
    private User newUser;

    @Before
    public void before(){
        newUser = new User().generateUser();
    }

    @Test
    @DisplayName(value = "Проверка регистрации только с обязательными полями")
    public void registrationWithRequiredFieldsTest() {
        userCreationDTO = UserCreationDTO.builder().login(newUser.getLogin())
                .password(newUser.getPassword())
                .build();

        requestSpecification = steps.createRequestSpecificationRegistration(userCreationDTO);
        responseSpecification = steps.createResponseSpecificationRegistration(201);
        steps.postRegistration(requestSpecification, responseSpecification);
    }

    @Test
    @DisplayName(value = "Проверка регистрации со всеми полями")
    public void registrationWithAllFieldsTest() {
        userCreationDTO = UserCreationDTO.builder().login(newUser.getLogin())
                .password(newUser.getPassword())
                .email(newUser.getEmail())
                .roles(newUser.getRoles())
                .build();

        requestSpecification = steps.createRequestSpecificationRegistration(userCreationDTO);
        responseSpecification = steps.createResponseSpecificationRegistration(201);
        steps.postRegistration(requestSpecification, responseSpecification);
    }

    @Test
    @DisplayName(value = "Проверка регистрации только с отправкой логина")
    public void registrationOnlyWithLoginTest() {
        userCreationDTO = userCreationDTO.builder().login(newUser.getLogin()).build();

        requestSpecification = steps.createRequestSpecificationRegistration(userCreationDTO);
        responseSpecification = steps.createResponseSpecificationRegistration(500);
        steps.postRegistrationFail("Password is required", requestSpecification, responseSpecification);
    }

    @Test
    @DisplayName(value = "Проверка регистрации только с отправкой пароля")
    public void registrationOnlyWithPasswordTest() {
        userCreationDTO = userCreationDTO.builder().password(newUser.getPassword()).build();

        requestSpecification = steps.createRequestSpecificationRegistration(userCreationDTO);
        responseSpecification = steps.createResponseSpecificationRegistration(500);
        steps.postRegistrationFail("Login is required", requestSpecification, responseSpecification);
    }

}
