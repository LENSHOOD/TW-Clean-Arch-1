package study.huhao.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.huhao.demo.domain.contexts.usercontext.user.User;
import study.huhao.demo.domain.contexts.usercontext.user.UserRepository;
import study.huhao.demo.domain.contexts.usercontext.user.UserService;

import java.util.UUID;

/**
 * EditUserUseCase:
 * @author zhangxuhai
 * @date 2020/1/28
*/
@Component
public class EditUserUseCase {
    private final UserService userService;

    @Autowired
    public EditUserUseCase(UserRepository userRepository) {
        this.userService = new UserService(userRepository);
    }

    @Transactional
    public User create(String userName, String displayName, String signature, String email) {
        return userService.create(userName, displayName, signature, email);
    }

    public void delete(UUID id) {
        userService.delete(id);
    }
}
