package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.api.filters.Authorize;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.UserPass;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/users")
public class UsersController {

    @Inject
    ViewService viewService;

    @GET
    @Authorize
    //@RolesAllowed(User.Roles.ADMIN)
    public Collection<User> getAllUsers() {
        return viewService.findAllUsers();
    }


    @GET
    @Path("/findByName/{name}")
    @Authorize
    //@RolesAllowed(User.Roles.ADMIN)
    public Collection<User> getAllUsersByName(@PathParam("name") String name) {
        return viewService.findAllUsersByName(name);
    }

    @POST
    @Authorize
    //@RolesAllowed(User.Roles.ADMIN)
    public Response saveUser(@Valid User user) {
        viewService.saveUser(user);
        return created(uri(UsersController.class, "getUser", user.getId())).build();
    }

    @GET
    @Path("/{user}")
    @Authorize
    //@RolesAllowed(User.Roles.ADMIN)
    public User getUser(@PathParam("user") User user) {
        return user;
    }

    @DELETE
    @Path("/{user}")
    @Authorize
    //@RolesAllowed(User.Roles.ADMIN)
    public Response deleteUser(@PathParam("user") User user) {
        viewService.removeUser(user);
        return noContent().build();
    }

    @PUT
    @Path("/{user}")
    @Authorize
    //@RolesAllowed(User.Roles.ADMIN)
    public Response updateUser(@PathParam("user") User originalUser, @Valid User updatedUser) {
        if (!originalUser.getId().equals(updatedUser.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        viewService.saveUser(updatedUser);
        return ok().build();
    }
}
