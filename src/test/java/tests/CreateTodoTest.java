package tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import models.CreateTodoRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateTodoTest {

    private static final Faker faker = new Faker();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    void shouldCreateSuccessfully() {
        int id = faker.number().numberBetween(150, 1000);
        String text = faker.lorem().sentence(3);

        CreateTodoRequest request = new CreateTodoRequest(id, text);

        given()
                .contentType(ContentType.JSON)
                .body(request)
        .when()
                .post("/todos")
        .then()
                .statusCode(201);

    }
}