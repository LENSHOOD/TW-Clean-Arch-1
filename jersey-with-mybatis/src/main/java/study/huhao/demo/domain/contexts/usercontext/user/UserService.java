package study.huhao.demo.domain.contexts.usercontext.user;

import study.huhao.demo.domain.core.common.excpetions.EntityNotFoundException;
import study.huhao.demo.domain.core.concepts.Service;

import java.util.UUID;

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

    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(User.class, id);
        }
        userRepository.deleteById(id);
    }

    public void save(UUID id, String userName, String displayName, String signature, String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, id));
        user.save(userName, displayName, signature, email);
        userRepository.save(user);
    }

    public User get(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, id));
    }
}
