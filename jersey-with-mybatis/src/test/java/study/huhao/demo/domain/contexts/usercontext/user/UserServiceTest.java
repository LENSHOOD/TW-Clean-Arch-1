package study.huhao.demo.domain.contexts.usercontext.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import study.huhao.demo.domain.core.common.excpetions.EntityNotFoundException;

import java.util.Optional;

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

    @Nested
    class saveUser {
        @Test
        void should_save_correctly() {
            User user = mock(User.class);
            when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

            userService.save(user.getId(), "test-user", "test-nick-name", "test-signature", "test@email.com");

            InOrder inOrder = inOrder(user, userRepository);
            inOrder.verify(user).save("test-user", "test-nick-name", "test-signature", "test@email.com");
            inOrder.verify(userRepository).save(user);
        }

        @Test
        void should_throw_EntityNotFoundException_when_user_not_exist() {
            User user = mock(User.class);
            when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

            assertThatThrownBy(() ->
                    userService.save(user.getId(), "test-user", "test-nick-name", "test-signature", "test@email.com"))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("cannot find the user with id " + user.getId());
        }
    }

    @Nested
    class getAllUser {
        @Test
        public void should_get_all_with_pagination() {
            UserCriteria userCriteria = mock(UserCriteria.class);

            userService.query(userCriteria);

            verify(userRepository).findAllWithPagination(userCriteria);
        }
    }
}