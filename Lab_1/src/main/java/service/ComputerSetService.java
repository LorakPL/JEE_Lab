package service;

import model.ComputerSet;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ComputerSetService {

    @Inject
    private ViewService viewService;

    private List<ComputerSet> computerSets;
    public void setViewService(ViewService viewService) {
        this.viewService = viewService;
    }

    public List<ComputerSet> getComputerSets() {
        if (computerSets == null) {
            computerSets = viewService.getAllComputerSets();
        }
        return computerSets;
    }

    public String removeComputerSet(ComputerSet computerSet) {
        viewService.removeComputerSet(computerSet);
        return "computerSetsList?faces-redirect=true";
    }
}
