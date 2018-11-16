package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;

import javax.ws.rs.ext.Provider;

@Provider
public class ComputerSetConverter extends AbstractEntityConverter<ComputerSet> {
    public ComputerSetConverter() {
        super(ComputerSet.class, ComputerSet::getId, ViewService::findComputerSet);
    }
}