package pl.gda.pg.eti.kask.javaee.jsf.api.Events;

import javax.enterprise.event.Observes;
import javax.faces.push.PushContext;

//@ApplicationScoped
public class ComputerSetEventPropagator {

    //@Inject
    //@Push
    PushContext computerSetUpdates;

    public void handleComputerSetUpdates(@Observes ComputerSetEvent computerSetEvent) {
        computerSetUpdates.send("update");
    }
}
