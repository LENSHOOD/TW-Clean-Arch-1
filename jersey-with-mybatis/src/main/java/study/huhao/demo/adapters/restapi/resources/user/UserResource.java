package study.huhao.demo.adapters.restapi.resources.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.huhao.demo.application.EditUserUseCase;
import study.huhao.demo.domain.contexts.usercontext.user.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * UserResource:
 * @author zhangxuhai
 * @date 2020/1/29
*/
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class UserResource {
    private final EditUserUseCase editUserUseCase;

    @Autowired
    public UserResource(EditUserUseCase editUserUseCase) {
        this.editUserUseCase = editUserUseCase;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(UserCreateRequest request) {
        User user = editUserUseCase.create(request.name, request.displayName, request.signature, request.email);

        URI uri = UriBuilder.fromResource(UserResource.class).path("{id}").build(user.getId());
        return Response.created(uri).entity(UserDto.of(user)).build();
    }
}
