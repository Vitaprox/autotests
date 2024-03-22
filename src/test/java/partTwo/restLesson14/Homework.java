package partTwo.restLesson14;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class Homework {

    private String token;

    private ArrayList<Map<String, String>> arrayList;

    private Map<String,String> request;

    @Before
    public void before() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", "Dtest");
        formParams.put("username", "Dtest");
        JsonPath response = RestAssured.given()
                .formParams(formParams)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();
        token = response.get("access_token");
    }

    @Test
    public void task1_1() {
        arrayList = new ArrayList<>();
        request = new HashMap<>();
        request.put("name","name21412");
        arrayList.add(request);

        RestAssured.given(getCreateNoteRequestSpecification(arrayList))
                .post()
                .then().spec(getResponseSpecificationWithCode(500));
    }

    @Test
    public void task1_2() {
        arrayList = new ArrayList<>();
        request = new HashMap<>();
        request.put("name","name21412");
        request.put("content","content21412");
        arrayList.add(request);

        RestAssured.given(getCreateNoteRequestSpecification(arrayList))
                .post()
                .then().spec(getResponseSpecificationWithCode(500));
    }

    @Test
    public void task1_3() {
        arrayList = new ArrayList<>();
        request = new HashMap<>();
        request.put("name","name21412");
        request.put("content","content21412");
        request.put("color","#fbbc04");
        request.put("priority","0");
        arrayList.add(request);

        RestAssured.given(getCreateNoteRequestSpecification(arrayList))
                .post()
                .then().spec(getResponseSpecificationWithCode(201));
    }

    private RequestSpecification getCreateNoteRequestSpecification(ArrayList<Map<String, String>> arrayList) {
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setContentType("application/json")
                .setBaseUri("http://172.24.120.5:8081")
                .setBasePath("/api/users/Dtest/notes")
                .setBody(arrayList)
                .build();
    }

    private ResponseSpecification getResponseSpecificationWithCode(int code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
    }

//    @Test
//    public void task1() {
//        RequestSpecification requestSpecificationNote = new RequestSpecBuilder()
//                .addHeader("Authorization", "Bearer " + token)
//                .setContentType("application/json")
//                .setBaseUri("http://172.24.120.5:8081")
//                .setBasePath("/api/users/Dtest/notes")
//                .build();
//
//        ResponseSpecification responseSpecification201 = new ResponseSpecBuilder()
//                .expectStatusCode(201)
//                .build();
//
//        arrayList = new ArrayList<>();
//        request = new HashMap<>();
//        request.put("name","name21412");
//        arrayList.add(request);
//
//        RestAssured.given(requestSpecificationNote)
//                .body(arrayList)
//                .post()
//                .then().spec(responseSpecification201);
//
//        arrayList = new ArrayList<>();
//        request = new HashMap<>();
//        request.put("name","name21412");
//        request.put("content","content21412");
//        arrayList.add(request);
//
//        RestAssured.given(requestSpecificationNote)
//                .body(arrayList)
//                .post()
//                .then().spec(responseSpecification201);
//
//        arrayList = new ArrayList<>();
//        request = new HashMap<>();
//        request.put("name","name21412");
//        request.put("content","content21412");
//        request.put("color","#fbbc04");
//        request.put("priority","0");
//        arrayList.add(request);
//
//        RestAssured.given(requestSpecificationNote)
//                .body(arrayList)
//                .post()
//                .then().spec(responseSpecification201);
//    }


    @Test
    public void classwork() {
        RequestSpecification requestSpecificationMe = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri("http://172.24.120.5:8081")
                .setBasePath("/api/users/me")
                .build();

        ResponseSpecification responseSpecificationUser = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("id", equalTo(6))
                .expectBody("login", equalTo("Dtest"))
                .expectBody("email", equalTo(""))
                .build();

        RestAssured.given(requestSpecificationMe)
                .get()
                .then().spec(responseSpecificationUser);

//        RestAssured.given()
//                .header("Authorization", "Bearer " + token)
//                .baseUri("http://172.24.120.5:8081")
//                .basePath("/api/users/me")
//                .get()
//                .then()
//                .statusCode(200)
//                .body("id", equalTo(6))
//                .body("login", equalTo("Dtest"))
//                .body("email", equalTo(""));
    }


}
