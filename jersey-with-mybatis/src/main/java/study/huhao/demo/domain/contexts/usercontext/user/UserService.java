package study.huhao.demo.domain.contexts.usercontext.user;

import study.huhao.demo.domain.core.concepts.Service;

/**
 * UserService:
 * @author zhangxuhai
 * @date 2020/1/28
*/
public class UserService implements Service {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String userName, String displayName, String signature, String email) {
        User user = new User(userName, displayName, signature, email);

        userRepository.save(user);
        return user;
    }
}
