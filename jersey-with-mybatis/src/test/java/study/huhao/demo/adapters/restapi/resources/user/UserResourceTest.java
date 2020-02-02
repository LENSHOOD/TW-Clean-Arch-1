package study.huhao.demo.adapters.restapi.resources.user;

import com.google.common.collect.ImmutableMap;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import study.huhao.demo.adapters.restapi.resources.ResourceTest;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * UserResourceTest:
 * @author zhangxuhai
 * @date 2020/1/29
*/
@DisplayName("/user")
public class UserResourceTest extends ResourceTest {
    @Nested
    @DisplayName("POST /user")
    class createUser {
        @Test
        void should_create_user() {
            createUser("test-name", "test-nick-name", "test-signature", "test@email.com")
                    .then()
                    .body(notNullValue())
                    .statusCode(HttpStatus.CREATED.value())
                    .contentType(ContentType.JSON)
                    .body("name", is("test-name"))
                    .body("displayName", is("test-nick-name"))
                    .body("signature", is("test-signature"))
                    .body("email", is("test@email.com"));
        }
    }

    private Response createUser(String userName, String displayName, String signature, String email) {
        return given()
                .contentType(ContentType.JSON)
                .body(ImmutableMap.of(
                        "name", userName,
                        "displayName", displayName,
                        "signature", signature,
                        "email", email))
                .when()
                .post("/user");
    }

    @Nested
    @DisplayName("DELETE /user/{id}")
    class deleteUser {
        @Test
        void should_delete_user() {
            UUID userId = createUser("test-name", "test-nick-name", "test-signature", "test@email.com")
                    .jsonPath()
                    .getUUID("id");

            given()
                    .when()
                    .delete("/user/" + userId)
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }
    }

    @Nested
    @DisplayName("POST /user")
    class updateUser {
        @Test
        void should_update_user() {
            UUID userId = createUser("test-name", "test-nick-name", "test-signature", "test@email.com")
                    .jsonPath()
                    .getUUID("id");

            given()
                    .contentType(ContentType.JSON)
                    .body(ImmutableMap.of(
                            "name", "test-n",
                            "displayName", "test-n",
                            "signature", "test-s",
                            "email", "t@email.com"))
                    .when()
                    .put("/user/" + userId)
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());

            given()
                    .when()
                    .get("/user/" + userId)
                    .then()
                    .contentType(ContentType.JSON)
                    .body(notNullValue())
                    .body("id", is(userId.toString()))
                    .body("name", is("test-n"))
                    .body("displayName", is("test-n"))
                    .body("signature", is("test-s"))
                    .body("email", is("t@email.com"));
        }

        @Test
        void should_return_empty_when_not_found() {
            UUID id = UUID.randomUUID();
            given()
                    .contentType(ContentType.JSON)
                    .body(ImmutableMap.of(
                            "name", "test-n",
                            "displayName", "test-n",
                            "signature", "test-s",
                            "email", "t@email.com"))
                    .when()
                    .put("/user/" + id)
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .contentType(ContentType.JSON)
                    .body("message", equalTo("cannot find the user with id " + id));
        }
    }

    @Nested
    @DisplayName("GET /user")
    class getUser {
        @Test
        void should_get_user() {
            UUID userId = createUser("test-name", "test-nick-name", "test-signature", "test@email.com")
                    .jsonPath()
                    .getUUID("id");

            given()
                    .when()
                    .get("/user/" + userId)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .contentType(ContentType.JSON)
                    .body(notNullValue())
                    .body("id", is(userId.toString()))
                    .body("name", is("test-name"))
                    .body("displayName", is("test-nick-name"))
                    .body("signature", is("test-signature"))
                    .body("email", is("test@email.com"));
        }
    }

    @Nested
    @DisplayName("GET /user?limit={limit}&offset={offset}")
    class allUser {
        @Test
        void should_get_user_with_pagination() {
            createUser("test-name-1", "test-nick-name-1", "test-signature", "test@email.com");
            createUser("test-name-2", "test-nick-name-2", "test-signature", "test@email.com");
            createUser("test-name-3", "test-nick-name-3", "test-signature", "test@email.com");
            createUser("test-name-4", "test-nick-name-4", "test-signature", "test@email.com");
            createUser("test-name-5", "test-nick-name-5", "test-signature", "test@email.com");

            given()
                    .when()
                    .get("/user?limit=3&offset=3")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .contentType(ContentType.JSON)
                    .body("results", hasSize(2))
                    .body("limit", equalTo(3))
                    .body("offset", equalTo(3))
                    .body("total", equalTo(5));
        }

        @Test
        void should_get_empty_results_when_no_user_found() {
            given()
                    .when()
                    .get("/user?limit=3&offset=4")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .contentType(ContentType.JSON)
                    .body("results", hasSize(0))
                    .body("limit", equalTo(3))
                    .body("offset", equalTo(4))
                    .body("total", equalTo(0));
        }
    }
}
