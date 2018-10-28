package view.converters;

import model.Part;
import service.ViewService;

import javax.enterprise.context.Dependent;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Part.class, managed = true)
@Dependent
public class PartConverter extends AbstractEntityConverter<Part> {

    public PartConverter() {
        super(Part::getId, ViewService::findPart);
    }
}
