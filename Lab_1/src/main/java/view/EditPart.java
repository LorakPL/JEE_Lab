package view;

import model.Part;
import model.PartType;
import service.ViewService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class EditPart implements Serializable {

    @Inject
    private ViewService viewService;

    private Part part = new Part();

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Collection<PartType> getPartTypes() {
        List<PartType> enumValues = new ArrayList<PartType>(EnumSet.allOf(PartType.class));
        return Collections.synchronizedList(enumValues);
    }


    public String savePart() {
        viewService.savePart(part);
        return "partsList?faces-redirect=true";
    }
}
