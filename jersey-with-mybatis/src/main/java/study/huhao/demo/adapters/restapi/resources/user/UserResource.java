package study.huhao.demo.adapters.restapi.resources.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.huhao.demo.application.EditUserUseCase;
import study.huhao.demo.application.QueryUserUseCase;
import study.huhao.demo.domain.contexts.usercontext.user.User;
import study.huhao.demo.domain.core.common.Page;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.UUID;

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
    private final QueryUserUseCase queryUserUseCase;

    @Autowired
    public UserResource(EditUserUseCase editUserUseCase, QueryUserUseCase queryUserUseCase) {
        this.editUserUseCase = editUserUseCase;
        this.queryUserUseCase = queryUserUseCase;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Page<UserDto> get(@QueryParam("limit") int limit, @QueryParam("offset") int offset) {
        return queryUserUseCase.query(limit, offset).map(UserDto::of);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(UserCreateRequest request) {
        User user = editUserUseCase.create(request.name, request.displayName, request.signature, request.email);

        URI uri = UriBuilder.fromResource(UserResource.class).path("{id}").build(user.getId());
        return Response.created(uri).entity(UserDto.of(user)).build();
    }

    @Path("{id}")
    public UserSubResource userSubResource(@PathParam("id") UUID id) {
        return new UserSubResource(id, editUserUseCase, queryUserUseCase);
    }
}
