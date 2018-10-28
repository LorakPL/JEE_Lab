package model;

public class ComputerSet {
    private Integer id;
    private User user;
    private Part motherboard;
    private Part graphicCard;
    private Part hardDrive;
    private Part ram;
    private Part computerCase;
    private Part cooling;
    private Part powerSupply;
    private Part processor;

    public ComputerSet() {}

    public ComputerSet(Integer id, User user, Part motherboard, Part graphicCard, Part hardDrive, Part ram, Part computerCase, Part cooling, Part powerSupply, Part processor) {
        this.id = id;
        this.user = user;
        this.motherboard = motherboard;
        this.graphicCard = graphicCard;
        this.hardDrive = hardDrive;
        this.ram = ram;
        this.computerCase = computerCase;
        this.cooling = cooling;
        this.powerSupply = powerSupply;
        this.processor = processor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Part getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Part motherboard) {
        this.motherboard = motherboard;
    }

    public Part getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(Part graphicCard) {
        this.graphicCard = graphicCard;
    }

    public Part getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(Part hardDrive) {
        this.hardDrive = hardDrive;
    }

    public Part getRam() {
        return ram;
    }

    public void setRam(Part ram) {
        this.ram = ram;
    }

    public Part getComputerCase() {
        return computerCase;
    }

    public void setComputerCase(Part computerCase) {
        this.computerCase = computerCase;
    }

    public Part getCooling() {
        return cooling;
    }

    public void setCooling(Part cooling) {
        this.cooling = cooling;
    }

    public Part getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(Part powerSupply) {
        this.powerSupply = powerSupply;
    }

    public Part getProcessor() {
        return processor;
    }

    public void setProcessor(Part processor) {
        this.processor = processor;
    }
}
