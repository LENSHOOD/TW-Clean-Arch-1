package study.huhao.demo.adapters.restapi.resources.user;

import com.google.common.collect.ImmutableMap;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import study.huhao.demo.adapters.restapi.resources.ResourceTest;

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
            given()
                    .contentType(ContentType.JSON)
                    .body(ImmutableMap.of(
                            "name", "test-name",
                            "displayName", "test-nick-name",
                            "signature", "test-signature",
                            "email", "test@email.com"))
                    .when()
                    .post("/user")
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
}
