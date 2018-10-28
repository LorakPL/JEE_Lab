package pl.gda.pg.eti.kask.javaee.jsf.api;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/computersets")
public class ComputerSetController {
    @Inject
    ViewService viewService;

    @GET
    public Collection<ComputerSet> getAllComputerSets() {
        return viewService.findAllComputerSets();
    }

    @POST
    public Response saveComputerSet(ComputerSet computerSet) {
        viewService.saveComputerSet(computerSet);
        return created(uri(ComputerSetController.class, "getComputerSet", computerSet.getId())).build();
    }

    @GET
    @Path("/{computerset}")
    public ComputerSet getComputerSet(@PathParam("computerset") ComputerSet computerSet) {
        return computerSet;
    }

    @DELETE
    @Path("/{computerset}")
    public Response deleteComputerSet(@PathParam("computerset") ComputerSet computerSet) {
        viewService.removeComputerSet(computerSet);
        return noContent().build();
    }

    @PUT
    @Path("/{computerset}")
    public Response updateComputerSet(@PathParam("computerset") ComputerSet originalComputerSet, ComputerSet updatedComputerSet) {
        if (!originalComputerSet.getId().equals(updatedComputerSet.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        viewService.saveComputerSet(updatedComputerSet);
        return ok().build();
    }

    @GET
    @Path("/checkIfEnoughParts")
    public boolean checkIfEnoughParts() {return this.viewService.checkIfEnoughParts();}

    @GET
    @Path("/checkIfEnoughUsers")
    public boolean checkIfEnoughUsers() {return this.viewService.checkIfEnoughUsers();}
}
