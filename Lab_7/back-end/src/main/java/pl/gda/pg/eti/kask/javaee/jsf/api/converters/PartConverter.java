package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.PartService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;

import javax.ws.rs.ext.ParamConverter;

public class PartConverter implements ParamConverter<Part> {
    private PartService partService;

    PartConverter(PartService partService) {
        this.partService = partService;
    }

    @Override
    public Part fromString(String s) {
        return partService.findPart(Integer.parseInt(s));
    }

    @Override
    public String toString(Part part) {
        return Integer.toString(part.getId());
    }
}
