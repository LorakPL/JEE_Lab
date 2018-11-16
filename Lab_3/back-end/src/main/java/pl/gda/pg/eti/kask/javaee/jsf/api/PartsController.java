package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.PartType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

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
    public Response savePart(Part part) {
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
        List<ComputerSet> computerSets = viewService.getComputerSetsByPart(part);
        for(ComputerSet computerSet : computerSets) {
            viewService.removeComputerSet(computerSet);
        }
        return noContent().build();
    }

    @PUT
    @Path("/{part}")
    public Response updatePart(@PathParam("part") Part originalPart, Part updatedPart) {
        if (!originalPart.getId().equals(updatedPart.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        List<ComputerSet> computerSets = viewService.getComputerSetsByPart(originalPart);

        if(originalPart.getType() != updatedPart.getType()) {
            for(ComputerSet computerSet : computerSets) {
                viewService.removeComputerSet(computerSet);
            }
        } else {
            for(int i = 0; i < computerSets.size(); i++) {
                for(int j = 0; j < computerSets.get(i).getParts().size(); j++) {
                    if(computerSets.get(i).getParts().get(j).getId() == updatedPart.getId()) {
                        computerSets.get(i).getParts().set(j, updatedPart);
                    }
                }
                viewService.saveComputerSet(computerSets.get(i));
            }
        }

        viewService.savePart(updatedPart);
        return ok().build();
    }
}
