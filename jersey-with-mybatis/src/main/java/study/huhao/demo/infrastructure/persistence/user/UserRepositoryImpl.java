package study.huhao.demo.infrastructure.persistence.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import study.huhao.demo.domain.contexts.usercontext.user.User;
import study.huhao.demo.domain.contexts.usercontext.user.UserRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * UserRepositoryImpl:
 * @author zhangxuhai
 * @date 2020/1/28
*/
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        if (!existsById(user.getId())) {
            userMapper.insert(UserPO.of(user));
        } else {
            userMapper.update(UserPO.of(user));
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return userMapper.existsById(id.toString());
    }

    @Override
    public void deleteById(UUID id) {
        userMapper.deleteById(id.toString());
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userMapper.findById(id.toString()).map(UserPO::toDomainModel);
    }
}
