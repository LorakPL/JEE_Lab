package pl.gda.pg.eti.kask.javaee.jsf.api.Events;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;

import java.io.Serializable;
import java.util.List;

public interface ComputerSetSource extends Serializable {
    List<ComputerSet> getComputerSets();
}
