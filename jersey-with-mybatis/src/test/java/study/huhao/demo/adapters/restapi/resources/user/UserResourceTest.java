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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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
}
