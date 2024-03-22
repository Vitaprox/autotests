package partTwo.restLesson15.specifiacions;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecifications {

    ResponseSpecification responseSpecification;

    private void createResponseSpecificationRegistration(int status) {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }
}
