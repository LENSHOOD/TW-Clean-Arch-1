package study.huhao.demo.domain.contexts.usercontext.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Nested
    class createUser {
        @Test
        void should_create_correctly() {
            User user =
                    userService.create("test-user", "test-nick-name", "test-signature", "test@email.com");

            verify(userRepository).save(any(User.class));
            assertThat(user.getId()).isNotNull();
        }
    }
}