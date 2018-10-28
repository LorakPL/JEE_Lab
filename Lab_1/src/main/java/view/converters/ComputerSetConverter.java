package view.converters;

import model.ComputerSet;
import service.ViewService;

import javax.enterprise.context.Dependent;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = ComputerSet.class, managed = true)
@Dependent
public class ComputerSetConverter extends AbstractEntityConverter<ComputerSet> {

    public ComputerSetConverter() {
        super(ComputerSet::getId, ViewService::findComputerSet);
    }
}
