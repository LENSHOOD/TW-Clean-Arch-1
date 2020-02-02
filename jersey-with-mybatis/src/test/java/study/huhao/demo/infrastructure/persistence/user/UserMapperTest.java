package study.huhao.demo.infrastructure.persistence.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.huhao.demo.domain.contexts.usercontext.user.UserCriteria;
import study.huhao.demo.infrastructure.persistence.MapperTest;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest extends MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        UserPO user = insertUser();

        Optional<UserPO> result = userMapper.findById(user.getId());

        assertThat(result).hasValueSatisfying(userPO ->
                assertThat(userPO).isEqualToComparingFieldByField(user)
        );
    }

    @Test
    void existsById() {
        UserPO user = insertUser();

        boolean isExists = userMapper.existsById(user.getId());

        assertThat(isExists).isTrue();
    }

    @Test
    void deleteById() {
        UserPO user = insertUser();

        userMapper.deleteById(user.getId());

        Optional<UserPO> result = userMapper.findById(user.getId());
        assertThat(result).isEmpty();
    }

    @Test
    void update() {
        UserPO user = insertUser();
        UserPO updatedUser = new UserPO(
                user.getId(),
                "test-user",
                "test-nick-name",
                "test-signature",
                "test@email.com",
                Instant.now(),
                Instant.now()
        );

        userMapper.update(updatedUser);

        Optional<UserPO> result = userMapper.findById(user.getId());
        assertThat(result).hasValueSatisfying(userPO ->
                assertThat(userPO).isEqualToComparingFieldByField(updatedUser)
        );
    }

    @Test
    void countTotalByCriteria() {
        for (int i = 0; i < 5; i++) {
            insertUser();
        }

        UserCriteria userCriteria = new UserCriteria(3, 3);
        long result = userMapper.countTotalByCriteria(userCriteria);

        assertThat(result).isEqualTo(5);
    }

    @Test
    void selectAllByCriteria() {
        for (int i = 0; i < 5; i++) {
            insertUser();
        }

        UserCriteria userCriteria = new UserCriteria(3, 3);
        List<UserPO> result = userMapper.selectAllByCriteria(userCriteria);

        assertThat(result).hasSize(2);
    }

    private UserPO insertUser() {
        UserPO userPO = new UserPO(
                UUID.randomUUID().toString(),
                "test-user",
                "test-nick-name",
                "test-signature",
                "test@email.com",
                Instant.now(),
                Instant.now()
        );

        userMapper.insert(userPO);
        return userPO;
    }
}