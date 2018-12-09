package pl.gda.pg.eti.kask.javaee.jsf.api.Events;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;

import javax.enterprise.event.Observes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/listener")
public class ComputerSetListener {

    @GET
    public ComputerSet listener(@Observes ComputerSetEvent computerSetEvent) {
        return computerSetEvent.getComputerSet();
    }
}
