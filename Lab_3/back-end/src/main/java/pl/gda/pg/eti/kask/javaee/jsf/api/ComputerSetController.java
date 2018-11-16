package pl.gda.pg.eti.kask.javaee.jsf.api;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.*;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uriWithParams;

@Path("/computersets")
public class ComputerSetController {
    @Inject
    ViewService viewService;

    @GET
    @Path("")
    public ComputerSets getAllComputerSets(@QueryParam("limit") Integer limit, @QueryParam("start") Integer start) {
        Collection<ComputerSet> computerSetsAll = viewService.findAllComputerSets();
        Collection<ComputerSet> computerSets = new LinkedHashSet<>();
        ComputerSets computerSetsObj = new ComputerSets();

        if(limit == null || limit >= computerSetsAll.size()) {
            computerSetsObj.setComputerSets(computerSetsAll);
        } else if(limit < 1 || (start != null && start >= computerSetsAll.size())) {
            computerSetsObj.setComputerSets(new ArrayList<>());
        } else {
            if (limit != null && (start != null && start < computerSetsAll.size())) {
                int returnSize = ((start + limit) > computerSetsAll.size()) ? computerSetsAll.size() : (start + limit);
                Iterator<ComputerSet> iterator = computerSetsAll.iterator();

                while (returnSize > 0 && iterator.hasNext()) {
                    if (returnSize <= start) {
                        computerSets.add(iterator.next());
                    } else {
                        iterator.next();
                    }

                    returnSize--;
                }

                Map<String, String> queryParams = new HashMap<>();
                queryParams.put("limit", limit.toString());
                Integer startPrev = start - limit;
                if (startPrev > 0) {
                    queryParams.put("start", startPrev.toString());
                }
                computerSetsObj.getLinks().add(new Link(uriWithParams(ComputerSetController.class, "getAllComputerSets", queryParams).toString(), "computerSetsPrevPage"));

                queryParams.clear();
                queryParams.put("limit", limit.toString());
                start += limit;
                queryParams.put("start", start.toString());
                computerSetsObj.getLinks().add(new Link(uriWithParams(ComputerSetController.class, "getAllComputerSets", queryParams).toString(), "computerSetsNextPage"));
            } else if (limit != null) {
                int returnSize = (limit > computerSetsAll.size()) ? computerSetsAll.size() : limit;
                Iterator<ComputerSet> iterator = computerSetsAll.iterator();

                while (returnSize > 0 && iterator.hasNext()) {
                    computerSets.add(iterator.next());
                    returnSize--;
                }

                Map<String, String> queryParams = new HashMap<>();
                queryParams.put("limit", limit.toString());
                queryParams.put("start", limit.toString());

                computerSetsObj.getLinks().add(new Link(uriWithParams(ComputerSetController.class, "getAllComputerSets", queryParams).toString(), "computerSetsNextPage"));
            } else {
                computerSets = computerSetsAll;
            }
            computerSetsObj.setComputerSets(computerSets);
        }

        return computerSetsObj;
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

    @GET
    @Path("/user/{user}")
    public Collection<ComputerSet> getAllComputerSetsByUserId(@PathParam("user")User user) {
        return viewService.getComputerSetsByUser(user);
    }

    @DELETE
    @Path("/user/{user}")
    public Response deleteAllComputerSetsByUserId(@PathParam("user")User user) {
        List<ComputerSet> computerSets = viewService.getComputerSetsByUser(user);
        for(ComputerSet computerSet : computerSets) {
            viewService.removeComputerSet(computerSet);
        }
        return noContent().build();
    }

    @GET
    @Path("/part/{part}")
    public Collection<ComputerSet> getAllComputerSetsByPartId(@PathParam("part") Part part) {
        return viewService.getComputerSetsByPart(part);
    }

    @DELETE
    @Path("/part/{part}")
    public Response deleteAllComputerSetsByPartId(@PathParam("part")Part part) {
        List<ComputerSet> computerSets = viewService.getComputerSetsByPart(part);
        for(ComputerSet computerSet : computerSets) {
            viewService.removeComputerSet(computerSet);
        }
        return noContent().build();
    }
}
