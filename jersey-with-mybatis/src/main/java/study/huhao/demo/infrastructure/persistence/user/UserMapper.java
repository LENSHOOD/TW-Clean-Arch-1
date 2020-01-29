package study.huhao.demo.infrastructure.persistence.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * UserMapper:
 * @author zhangxuhai
 * @date 2020/1/28
*/
@Component
@Mapper
public interface UserMapper {
    void insert(@Param("user") UserPO user);

    Optional<UserPO> findById(@Param("id") String id);

    boolean existsById(@Param("id") String id);

    void deleteById(@Param("id") String id);
}
