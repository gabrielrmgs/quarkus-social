package io.github.gabrielrmgs.quarkussoscial.rest;

import java.util.List;

import io.github.gabrielrmgs.quarkussoscial.model.User;
import io.github.gabrielrmgs.quarkussoscial.rest.dto.CreateUserRequest;
import io.github.gabrielrmgs.quarkussoscial.rest.dto.ErrorResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest) {
        User newUser = new User();
        ErrorResponse erro = new ErrorResponse();
        if (userRequest.name().trim().isEmpty() || userRequest.name() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(erro.errorMessage("Nome inválido!").status(Status.BAD_REQUEST.getStatusCode())).build();
        }
        if (userRequest.age().equals(0) || userRequest.age() >= 100 || userRequest.age() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(erro.errorMessage("Idade inválida!").status(Status.BAD_REQUEST.getStatusCode())).build();
        }
        newUser.name(userRequest.name()).age(userRequest.age());
        newUser.persist();
        return Response.ok(newUser).build();
    }

    @GET
    public Response listAllUsers() {
        PanacheQuery<User> allUsers = User.findAll();
        return Response.ok(allUsers.list()).build();
    }
}
