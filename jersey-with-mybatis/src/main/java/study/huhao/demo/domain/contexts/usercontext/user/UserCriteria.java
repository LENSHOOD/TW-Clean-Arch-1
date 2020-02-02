package study.huhao.demo.domain.contexts.usercontext.user;

import study.huhao.demo.domain.core.common.Criteria;

/**
 * UserCriteria:
 * @author zhangxuhai
 * @date 2020/2/2
*/
public class UserCriteria extends Criteria {
    public UserCriteria(int limit, long offset) {
        super(limit, offset);
    }
}
