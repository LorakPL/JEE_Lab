package view;

import model.ComputerSet;
import model.Part;
import model.PartType;
import model.User;
import service.ViewService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class EditComputerSet implements Serializable {

    @Inject
    private ViewService viewService;

    private ComputerSet computerSet = new ComputerSet();

    public ComputerSet getComputerSet() {
        return computerSet;
    }

    public void setComputerSet(ComputerSet computerSet) {
        this.computerSet = computerSet;
    }

    public String saveComputerSet() {
        viewService.saveComputerSet(computerSet);
        return "computerSetsList?faces-redirect=true";
    }

    public Collection<User> getUsers() {
        return Collections.synchronizedList(viewService.getAllUsers());
    }

    public Collection<Part> getMotherbaords() {
        return Collections.synchronizedList(viewService.getPartsByType(PartType.MOTHERBOARD));
    }

    public Collection<Part> getGraphicCards() {
        return Collections.synchronizedList(viewService.getPartsByType(PartType.GRAPHIC_CARD));
    }

    public Collection<Part> getHardDrives() {
        return Collections.synchronizedList(viewService.getPartsByType(PartType.HARD_DRIVE));
    }

    public Collection<Part> getRAMs() {
        return Collections.synchronizedList(viewService.getPartsByType(PartType.RAM));
    }

    public Collection<Part> getComputerCases() {
        return Collections.synchronizedList(viewService.getPartsByType(PartType.CASE));
    }

    public Collection<Part> getCoolings() {
        return Collections.synchronizedList(viewService.getPartsByType(PartType.COOLING));
    }

    public Collection<Part> getPowerSupplies() {
        return Collections.synchronizedList(viewService.getPartsByType(PartType.POWER_SUPPLY));
    }

    public Collection<Part> getProcessors() {
        return Collections.synchronizedList(viewService.getPartsByType(PartType.PROCESSOR));
    }
}
