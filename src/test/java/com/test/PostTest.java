package com.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class PostTest {
    private RequestSpecification spec;

    @BeforeSuite
    public void setUp() {
        String baseURL = "http://jsonplaceholder.typicode.com/";

        spec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void testPost() {
        given().spec(spec)
                .expect()
                .statusCode(200)
                .body("userId", equalTo("1"),
                        "id", equalTo("1"),
                        "title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"),
                        "body", equalTo("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"))
                .when()
                .get("posts/1");
    }

    @Test
    public void testPostWithObjectMapper() {
        Post expectedPost = new Post("1", "1", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        Post actualPost = given().spec(spec)
                .expect()
                .statusCode(200)
                .when()
                .get("posts/1")
                .as(Post.class);
        assertEquals(actualPost, expectedPost);
    }

    @Test
    public void testPostResponseItemsCount() {
        int postResponseItemsCount = given().spec(spec)
                .param("userId", 1)
                .expect()
                .statusCode(200)
                .when()
                .get("posts")
                .jsonPath()
                .getList("posts")
                .size();

        assertEquals(postResponseItemsCount, 10);
    }
}
