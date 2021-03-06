package study.huhao.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.huhao.demo.domain.contexts.usercontext.user.User;
import study.huhao.demo.domain.contexts.usercontext.user.UserCriteria;
import study.huhao.demo.domain.contexts.usercontext.user.UserRepository;
import study.huhao.demo.domain.contexts.usercontext.user.UserService;
import study.huhao.demo.domain.core.common.Page;

import java.util.UUID;

/**
 * QueryUserUseCase:
 * @author zhangxuhai
 * @date 2020/1/30
*/
@Component
public class QueryUserUseCase {
    private final UserService userService;

    @Autowired
    public QueryUserUseCase(UserRepository userRepository) {
        this.userService = new UserService(userRepository);
    }

    public User get(UUID id) {
        return userService.get(id);
    }

    public Page<User> query(int limit, int offset) {
        UserCriteria userCriteria = new UserCriteria(limit, offset);
        return userService.query(userCriteria);
    }
}
