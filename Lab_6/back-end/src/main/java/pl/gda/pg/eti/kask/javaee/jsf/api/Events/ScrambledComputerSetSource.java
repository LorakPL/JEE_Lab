package pl.gda.pg.eti.kask.javaee.jsf.api.Events;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Decorator
public class ScrambledComputerSetSource implements ComputerSetSource {

    @Inject
    @Delegate
    @Any
    ComputerSetSource computerSetSource;

    @Override
    public List<ComputerSet> getComputerSets() {
        List<ComputerSet> computerSets = computerSetSource.getComputerSets();
        Collections.shuffle(computerSets);
        return computerSets;
    }
}
