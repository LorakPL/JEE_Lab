package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.api.filters.Authorize;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ComputerSetService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.CustomerService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.PartService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.annotation.security.RolesAllowed;
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
    ComputerSetService computerSetService;

    @Inject
    CustomerService customerService;

    @Inject
    PartService partService;

    @GET
    @Authorize
    public Collection<ComputerSet> getAllComputerSets() {
        return computerSetService.findAllComputerSets();
    }

    @POST
    @Authorize
    public Response saveComputerSet(@Valid ComputerSet computerSet) {
        computerSetService.saveComputerSet(computerSet);
        return created(uri(ComputerSetsController.class, "getComputerSet", computerSet.getId())).build();
    }

    @GET
    @Path("/{computerSet}")
    @Authorize
    public ComputerSet getComputerSet(@PathParam("computerSet") ComputerSet computerSet) {
        return computerSet;
    }

    @DELETE
    @Path("/{computerSet}")
    @Authorize
    public Response deleteComputerSet(@PathParam("computerSet") ComputerSet computerSet) {
        computerSetService.removeComputerSet(computerSet);
        return noContent().build();
    }

    @PUT
    @Path("/{computerSet}")
    @Authorize
    public Response updateComputerSet(@PathParam("computerSet") ComputerSet originalComputerSet, @Valid ComputerSet updatedComputerSet) {
        if (!originalComputerSet.getId().equals(updatedComputerSet.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        computerSetService.saveComputerSet(updatedComputerSet);
        return ok().build();
    }

    @GET
    @Path("/checkIfEnoughParts")
    @Authorize
    public boolean checkIfEnoughParts() {return this.partService.checkIfEnoughParts();}

    @GET
    @Path("/checkIfEnoughUsers")
    @Authorize
    public boolean checkIfEnoughUsers() {return this.customerService.checkIfEnoughCustomers();}
}
