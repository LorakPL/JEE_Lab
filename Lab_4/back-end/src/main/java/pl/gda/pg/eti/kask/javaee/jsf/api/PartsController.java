package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
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
    ViewService viewService;

    @GET
    public Collection<Part> getAllParts() {
        return viewService.findAllParts();
    }

    @GET
    @Path("/partType")
    public Collection<PartType> getAllPartType() {
        return viewService.getAllPartTypes();
    }

    @POST
    public Response savePart(@Valid Part part) {
        viewService.savePart(part);
        return created(uri(PartsController.class, "getPart", part.getId())).build();
    }

    @GET
    @Path("/{part}")
    public Part getPart(@PathParam("part") Part part) {
        return part;
    }

    @DELETE
    @Path("/{part}")
    public Response deletePart(@PathParam("part") Part part) {
        viewService.removePart(part);
        return noContent().build();
    }

    @PUT
    @Path("/{part}")
    public Response updatePart(@PathParam("part") Part originalPart, @Valid Part updatedPart) {
        if (!originalPart.getId().equals(updatedPart.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        if(originalPart.getType() != updatedPart.getType()) {
            viewService.removeComputerSetsByPart(updatedPart);
        }

        viewService.savePart(updatedPart);
        return ok().build();
    }
}
