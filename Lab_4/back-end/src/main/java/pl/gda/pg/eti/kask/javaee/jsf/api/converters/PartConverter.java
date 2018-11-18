package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;

import javax.ws.rs.ext.Provider;

@Provider
public class PartConverter extends AbstractEntityConverter<Part> {
    public PartConverter() {
        super(Part.class, Part::getId, ViewService::findPart);
    }

}
