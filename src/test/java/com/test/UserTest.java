package com.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.test.TestUtils.getPeople;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class UserTest {
    private RequestSpecification spec;

    @BeforeSuite
    public void setUp() {
        String baseURL = "http://localhost:8080/service/";

        spec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void testGetSingleUser() {
        // Preconditions
        given().spec(spec)
                .expect()
                .statusCode(200)
        // Expected response content
                .body("email", equalTo("test@hascode.com"),
                        "firstName", equalTo("Tim"),
                        "lastName", equalTo("Testerman"),
                        "id", equalTo("1"))
                .when()
                .get("single-user");
    }

    @Test
    public void testGetSingleUserWithObjectMapper() {
        Person expectedPerson = new Person("Tim", "Testerman", 1, "test@hascode.com");

        Person actualPerson = given().spec(spec)
                .expect()
                .statusCode(200)
                .when()
                .get("single-user")
                .as(Person.class);
        assertEquals(actualPerson, expectedPerson);
    }

    @Test
    public void testFindListPerson() {
        List<Person> personsExpected = getPeople();
        String json = given().spec(spec).get("/persons/json").asString();
        JsonPath jp = new JsonPath(json);
        List<Person> personsActual = jp.getList("person", Person.class);
        assertEquals(personsActual, personsExpected);
    }
}
