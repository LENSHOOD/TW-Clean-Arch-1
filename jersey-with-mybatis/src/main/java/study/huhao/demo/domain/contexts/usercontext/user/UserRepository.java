package study.huhao.demo.domain.contexts.usercontext.user;

import study.huhao.demo.domain.core.concepts.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository {
    void save(User user);

    boolean existsById(UUID id);

    void deleteById(UUID id);

    Optional<User> findById(UUID id);
}
