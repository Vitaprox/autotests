package partTwo.restLesson15.specifiacions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import partTwo.restLesson15.objects.UserCreationDTO;

import static partTwo.restLesson15.Properties.BASE_URI;
import static partTwo.restLesson15.Properties.PATH_REGISTRATION;

public class RequestSpecifications {

    RequestSpecification requestSpecification;

    private void createRequestSpecificationRegistration(UserCreationDTO userCreationDTO) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(PATH_REGISTRATION)
                .setContentType(ContentType.JSON)
                .setBody(userCreationDTO)
                .build();
    }

}
