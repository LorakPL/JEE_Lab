package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ComputerSetService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;

import javax.ws.rs.ext.ParamConverter;

public class ComputerSetConverter implements ParamConverter<ComputerSet> {
    private ComputerSetService computerSetService;

    ComputerSetConverter(ComputerSetService computerSetService) {
        this.computerSetService = computerSetService;
    }

    @Override
    public ComputerSet fromString(String s) {
        return computerSetService.findComputerSet(Integer.parseInt(s));
    }

    @Override
    public String toString(ComputerSet part) {
        return Integer.toString(part.getId());
    }
}