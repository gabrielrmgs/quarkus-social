package io.github.gabrielrmgs.quarkussoscial.rest;

import java.util.Set;

import io.github.gabrielrmgs.quarkussoscial.domain.repository.UserRepository;
import io.github.gabrielrmgs.quarkussoscial.domain.model.User;
import io.github.gabrielrmgs.quarkussoscial.rest.dto.CreateUserRequest;
import io.github.gabrielrmgs.quarkussoscial.rest.dto.ErrorResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
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

    private Validator validator;
    private UserRepository userRepository;

    @Inject
    public UserResource(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest) {

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);

        if (!violations.isEmpty()) {
            ErrorResponse erro = ErrorResponse.createFromValidation(violations);
            return Response.status(Status.BAD_REQUEST).entity(erro).build();
        }

        User newUser = new User();
        newUser.name(userRequest.name()).age(userRequest.age());
        userRepository.persist(newUser);
        return Response.ok(newUser).build();

        // --------------------------------------
        //forma que eu fazia antigamente abaixo:
        // ErrorResponse erro = new ErrorResponse();
        // if (userRequest.name().trim().isEmpty() || userRequest.name() == null) {
        // return Response.status(Status.BAD_REQUEST)
        // .entity(erro.errorMessage("Nome
        // inválido!").status(Status.BAD_REQUEST.getStatusCode())).build();
        // }
        // if (userRequest.age().equals(0) || userRequest.age() >= 100 ||
        // userRequest.age() == null) {
        // return Response.status(Status.BAD_REQUEST)
        // .entity(erro.errorMessage("Idade
        // inválida!").status(Status.BAD_REQUEST.getStatusCode())).build();
        // }
        // --------------------------------
        
    }

    @GET
    public Response listAllUsers() {
        PanacheQuery<User> allUsers = userRepository.findAll();
        return Response.ok(allUsers.list()).build();
    }
}
