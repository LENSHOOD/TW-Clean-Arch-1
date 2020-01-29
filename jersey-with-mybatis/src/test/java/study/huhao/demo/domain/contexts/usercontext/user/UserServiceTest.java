package study.huhao.demo.domain.contexts.usercontext.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import study.huhao.demo.domain.core.common.excpetions.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Nested
    class deleteUser {
        @Test
        void should_delete_correctly() {
            User user = mock(User.class);
            when(userRepository.existsById(user.getId())).thenReturn(true);

            userService.delete(user.getId());

            verify(userRepository).deleteById(user.getId());
        }

        @Test
        void should_throw_EntityNotFoundException_when_user_not_exist() {
            User user = mock(User.class);
            when(userRepository.existsById(user.getId())).thenReturn(false);

            assertThatThrownBy(() -> userService.delete(user.getId()))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("cannot find the user with id " + user.getId());
        }
    }
}