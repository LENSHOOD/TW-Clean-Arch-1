package study.huhao.demo.infrastructure.persistence.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.huhao.demo.infrastructure.persistence.MapperTest;

import java.time.Instant;
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