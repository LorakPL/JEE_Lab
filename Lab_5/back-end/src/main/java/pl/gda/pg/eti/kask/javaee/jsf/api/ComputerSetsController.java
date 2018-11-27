package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/computersets")
public class ComputerSetsController {

    @Inject
    ViewService viewService;

    @GET
    public Collection<ComputerSet> getAllComputerSets() {
        return viewService.findAllComputerSets();
    }

    @POST
    public Response saveComputerSet(@Valid ComputerSet computerSet) {
        viewService.saveComputerSet(computerSet);
        return created(uri(ComputerSetsController.class, "getComputerSet", computerSet.getId())).build();
    }

    @GET
    @Path("/{computerSet}")
    public ComputerSet getComputerSet(@PathParam("computerSet") ComputerSet computerSet) {
        return computerSet;
    }

    @DELETE
    @Path("/{computerSet}")
    public Response deleteComputerSet(@PathParam("computerSet") ComputerSet computerSet) {
        viewService.removeComputerSet(computerSet);
        return noContent().build();
    }

    @PUT
    @Path("/{computerSet}")
    public Response updateComputerSet(@PathParam("computerSet") ComputerSet originalComputerSet, @Valid ComputerSet updatedComputerSet) {
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
    public boolean checkIfEnoughUsers() {return this.viewService.checkIfEnoughCustomers();}
}
