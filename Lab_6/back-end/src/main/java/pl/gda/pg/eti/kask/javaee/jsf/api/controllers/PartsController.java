package pl.gda.pg.eti.kask.javaee.jsf.api.controllers;

import pl.gda.pg.eti.kask.javaee.jsf.api.services.auth.CheckPermissions;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ComputerSetService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.PartService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.PartType;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/parts")
public class PartsController {

    @Inject
    PartService partService;

    @Inject
    ComputerSetService computerSetService;

    @GET
    @CheckPermissions
    public Collection<Part> getAllParts() {
        return partService.findAllParts();
    }

    @GET
    @Path("/partType")
    @CheckPermissions
    public Collection<PartType> getAllPartType() {
        return partService.getAllPartTypes();
    }

    @POST
    @CheckPermissions
    public Response savePart(@Valid Part part) {
        partService.savePart(part);
        return created(uri(PartsController.class, "getPart", part.getId())).build();
    }

    @GET
    @Path("/{part}")
    @CheckPermissions
    public Part getPart(@PathParam("part") Part part) {
        return part;
    }

    @DELETE
    @Path("/{part}")
    @CheckPermissions
    public Response deletePart(@PathParam("part") Part part) {
        partService.removePart(part);
        return noContent().build();
    }

    @PUT
    @Path("/{part}")
    @CheckPermissions
    public Response updatePart(@PathParam("part") Part originalPart, @Valid Part updatedPart) {
        if (!originalPart.getId().equals(updatedPart.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        if(originalPart.getType() != updatedPart.getType()) {
            computerSetService.removeComputerSetsByPart(updatedPart);
        }

        partService.savePart(updatedPart);
        return ok().build();
    }
}
