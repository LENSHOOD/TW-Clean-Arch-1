package study.huhao.demo.infrastructure.persistence.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import study.huhao.demo.domain.contexts.usercontext.user.UserCriteria;

import java.util.List;
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

    void update(@Param("user") UserPO user);

    long countTotalByCriteria(@Param("criteria") UserCriteria userCriteria);

    List<UserPO> selectAllByCriteria(@Param("criteria") UserCriteria userCriteria);
}
