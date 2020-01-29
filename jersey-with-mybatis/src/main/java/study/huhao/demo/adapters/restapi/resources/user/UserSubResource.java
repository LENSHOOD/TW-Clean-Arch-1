package study.huhao.demo.adapters.restapi.resources.user;

import study.huhao.demo.application.EditUserUseCase;

import javax.ws.rs.DELETE;
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
    private final EditUserUseCase editUserUseCase;
    private final UUID id;

    UserSubResource(UUID id, EditUserUseCase editUserUseCase) {
        this.id = id;
        this.editUserUseCase = editUserUseCase;
    }

    @DELETE
    public void delete() {
        editUserUseCase.delete(id);
    }
}
