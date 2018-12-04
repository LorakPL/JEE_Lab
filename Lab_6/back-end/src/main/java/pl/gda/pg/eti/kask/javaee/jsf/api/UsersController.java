package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.api.filters.Authorize;
import pl.gda.pg.eti.kask.javaee.jsf.api.services.auth.CheckPermissions;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.UserService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/users")
public class UsersController {

    @Inject
    UserService userService;

    @GET
    @CheckPermissions
    //@Authorize
    //@RolesAllowed(User.Roles.ADMIN)
    public Collection<User> getAllUsers() {
        return userService.findAllUsers();
    }


    @GET
    @Path("/findByName/{name}")
    //@Authorize
    @CheckPermissions
    //@RolesAllowed(User.Roles.ADMIN)
    public Collection<User> getAllUsersByLogin(@PathParam("name") String name) {
        return userService.findAllUsersByLogin(name);
    }

    @POST
    //@Authorize
    @CheckPermissions
    //@RolesAllowed(User.Roles.ADMIN)
    public Response saveUser(@Valid User user) {
        userService.saveUser(user);
        return created(uri(UsersController.class, "getUser", user.getId())).build();
    }

    @GET
    @Path("/{user}")
    //@Authorize
    @CheckPermissions
    //@RolesAllowed(User.Roles.ADMIN)
    public User getUser(@PathParam("user") User user) {
        return user;
    }

    @DELETE
    @Path("/{user}")
    //@Authorize
    @CheckPermissions
    //@RolesAllowed(User.Roles.ADMIN)
    public Response deleteUser(@PathParam("user") User user) {
        userService.removeUser(user);
        return noContent().build();
    }

    @PUT
    @Path("/{user}")
    //@Authorize
    @CheckPermissions
    //@RolesAllowed(User.Roles.ADMIN)
    public Response updateUser(@PathParam("user") User originalUser, @Valid User updatedUser) {
        if (!originalUser.getId().equals(updatedUser.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        userService.saveUser(updatedUser);
        return ok().build();
    }

    @GET
    @Path("/cs")
    //@Authorize
    @CheckPermissions
    public Collection<User> getAllUsersForComputerSets() {
        return userService.findAllUsersForComputerSets();
    }
}
