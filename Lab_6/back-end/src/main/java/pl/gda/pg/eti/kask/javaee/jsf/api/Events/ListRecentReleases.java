package pl.gda.pg.eti.kask.javaee.jsf.api.Events;

import lombok.Getter;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ComputerSetService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class ListRecentReleases implements ComputerSetSource {
    @EJB
    ComputerSetService computerSetService;

    @Getter
    List<ComputerSet> computerSets;

    @PostConstruct
    public void init() {
        computerSets = new ArrayList<>(computerSetService.findAllComputerSets());
    }

    public void handleComputerSetEvent(@Observes ComputerSetEvent computerSetEvent) {
        init();
    }
}
