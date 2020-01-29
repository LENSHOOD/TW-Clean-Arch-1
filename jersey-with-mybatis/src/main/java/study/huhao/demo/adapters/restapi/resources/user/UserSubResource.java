package study.huhao.demo.adapters.restapi.resources.user;

import study.huhao.demo.application.EditUserUseCase;
import study.huhao.demo.application.QueryUserUseCase;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

/**
 * UserSubResource:
 *
 * @author zhangxuhai
 * @date 2020/1/29
 */
@Produces(MediaType.APPLICATION_JSON)
public class UserSubResource {
    private final UUID id;
    private final EditUserUseCase editUserUseCase;
    private final QueryUserUseCase queryUserUseCase;

    UserSubResource(UUID id, EditUserUseCase editUserUseCase, QueryUserUseCase queryUserUseCase) {
        this.id = id;
        this.editUserUseCase = editUserUseCase;
        this.queryUserUseCase = queryUserUseCase;
    }

    @DELETE
    public void delete() {
        editUserUseCase.delete(id);
    }

    @PUT
    public void put(UserSaveRequest request) {
        editUserUseCase.save(id, request.name, request.displayName, request.signature, request.email);
    }

    @GET
    public UserDto get() {
        return UserDto.of(queryUserUseCase.get(id));
    }
}
