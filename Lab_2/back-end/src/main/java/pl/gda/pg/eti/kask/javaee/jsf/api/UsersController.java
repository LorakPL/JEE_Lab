package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/users")
public class UsersController {
    @Inject
    ViewService viewService;

    @GET
    public Collection<User> getAllUsers() {
        return viewService.findAllUsers();
    }

    @POST
    public Response saveUser(User user) {
        viewService.saveUser(user);
        return created(uri(UsersController.class, "getUser", user.getId())).build();
    }

    @GET
    @Path("/{user}")
    public User getUser(@PathParam("user") User user) {
        return user;
    }

    @DELETE
    @Path("/{user}")
    public Response deleteUser(@PathParam("user") User user) {
        viewService.removeUser(user);
        List<ComputerSet> computerSets = viewService.getComputerSetsByUser(user);
        for(ComputerSet computerSet : computerSets) {
            viewService.removeComputerSet(computerSet);
        }
        return noContent().build();
    }

    @PUT
    @Path("/{user}")
    public Response updateUser(@PathParam("user") User originalUser, User updatedUser) {
        if (!originalUser.getId().equals(updatedUser.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        viewService.saveUser(updatedUser);
        return ok().build();
    }
}
