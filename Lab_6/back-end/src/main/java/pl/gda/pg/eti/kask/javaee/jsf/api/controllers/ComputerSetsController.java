package pl.gda.pg.eti.kask.javaee.jsf.api.controllers;

import pl.gda.pg.eti.kask.javaee.jsf.api.services.auth.CheckPermissions;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ComputerSetService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.PartService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.UserService;
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
    ComputerSetService computerSetService;

    @Inject
    PartService partService;

    @Inject
    UserService userService;

    @GET
    @CheckPermissions
    public Collection<ComputerSet> getAllComputerSets() {
        return computerSetService.findAllComputerSets();
    }

    @POST
    @CheckPermissions
    public Response saveComputerSet(@Valid ComputerSet computerSet) {
        computerSetService.saveComputerSet(computerSet);
        return created(uri(ComputerSetsController.class, "getComputerSet", computerSet.getId())).build();
    }

    @GET
    @Path("/{computerSet}")
    @CheckPermissions
    public ComputerSet getComputerSet(@PathParam("computerSet") ComputerSet computerSet) {
        return computerSet;
    }

    @DELETE
    @Path("/{computerSet}")
    @CheckPermissions
    public Response deleteComputerSet(@PathParam("computerSet") ComputerSet computerSet) {
        computerSetService.removeComputerSet(computerSet);
        return noContent().build();
    }

    @PUT
    @Path("/{computerSet}")
    @CheckPermissions
    public Response updateComputerSet(@PathParam("computerSet") ComputerSet originalComputerSet, @Valid ComputerSet updatedComputerSet) {
        if (!originalComputerSet.getId().equals(updatedComputerSet.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        computerSetService.saveComputerSet(updatedComputerSet);
        return ok().build();
    }

    @GET
    @Path("/checkIfEnoughParts")
    @CheckPermissions
    public boolean checkIfEnoughParts() {return this.partService.checkIfEnoughParts();}

    @GET
    @Path("/checkIfEnoughUsers")
    @CheckPermissions
    public boolean checkIfEnoughUsers() {return this.userService.checkIfEnoughUsers();}
}
