package tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import models.CreateTodoRequest;
import models.TodoResponse;
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

    @Test
    void shouldNotAllowedDuplicateTodoId() {
        int id = faker.number().numberBetween(1000, 2000);
        String text = faker.lorem().sentence(3);

        CreateTodoRequest request = new CreateTodoRequest(id, text);
        TodoResponse response = new TodoResponse();

        //первый проходит успешно
        given()
                .contentType(ContentType.JSON)
                .body(request)
        .when()
                .post("/todos")
        .then()
                .statusCode(201);

        //второй пост с тем же id должен вернуть ошибку
        given()
                .contentType(ContentType.JSON)
                .body(request)
        .when()
                .post("/todos")
        .then()
                .statusCode(400)
                .log().all();
    }
}