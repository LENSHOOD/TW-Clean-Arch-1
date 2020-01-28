package study.huhao.demo.adapters.restapi.resources.user;

import study.huhao.demo.adapters.restapi.resources.RequestDto;

/**
 * UserCreateRequest:
 * @author zhangxuhai
 * @date 2020/1/29
*/
public class UserCreateRequest implements RequestDto {
    public String name;
    public String displayName;
    public String signature;
    public String email;
}
