package study.huhao.demo.domain.contexts.usercontext.user;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    @Nested
    class constructor {
        @Test
        void should_initialize_correctly() {
            User user = new User(
                    "test-user",
                    "test-nick-name",
                    "test-signature",
                    "test@email.com");

            assertThat(user.getId()).isNotNull();
            assertThat(user.getName()).isEqualTo("test-user");
            assertThat(user.getDisplayName()).isEqualTo("test-nick-name");
            assertThat(user.getSignature()).isEqualTo("test-signature");
            assertThat(user.getEmail()).isEqualTo("test@email.com");
            assertThat(user.getCreatedAt()).isNotNull();
            assertThat(user.getSavedAt()).isEqualTo(user.getCreatedAt());
        }

        @Test
        void should_throw_IllegalArgumentException_when_the_name_is_null_or_no_content() {
            assertThatThrownBy(() ->
                    new User(null, "test-nick-name", "test-signature", "test@email.com"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("the user name cannot be null or no content");
            assertThatThrownBy(() ->
                    new User("  ", "test-nick-name", "test-signature", "test@email.com"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("the user name cannot be null or no content");
        }

        @Test
        void should_throw_IllegalArgumentException_when_the_email_is_invalid() {
            assertThatThrownBy(() ->
                    new User("test-user", "test-nick-name", "test-signature", "test"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("the email is invalid");
        }
    }
}