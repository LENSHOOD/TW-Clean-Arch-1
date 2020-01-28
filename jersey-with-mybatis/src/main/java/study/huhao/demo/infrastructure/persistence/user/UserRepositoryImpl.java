package study.huhao.demo.infrastructure.persistence.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import study.huhao.demo.domain.contexts.usercontext.user.User;
import study.huhao.demo.domain.contexts.usercontext.user.UserRepository;

/**
 * UserRepositoryimpl:
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
        userMapper.insert(UserPO.of(user));
    }
}
