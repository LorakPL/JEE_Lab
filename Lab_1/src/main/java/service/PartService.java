package service;

import model.Part;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PartService {

    @Inject
    private ViewService viewService;

    private List<Part> parts;
    public void setViewService(ViewService viewService) {
        this.viewService = viewService;
    }

    public List<Part> getParts() {
        if (parts == null) {
            parts = viewService.getAllParts();
        }
        return parts;
    }

    public String removePart(Part part) {
        viewService.removePart(part);
        return "partsList?faces-redirect=true";
    }
}
