package service;

import model.ComputerSet;
import model.Part;
import model.PartType;
import model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class ViewService implements Serializable {

    private final List<User> users = new ArrayList<User>();
    private final List<Part> parts = new ArrayList<Part>();
    private final List<ComputerSet> computerSets = new ArrayList<ComputerSet>();

    @PostConstruct
    public void init() {
        User user1 = new User(1, "jan.nowak@gmail.com", "123456", "Jan", "Nowak");
        User user2 = new User(2, "adam.kowalski@gmail.com", "123456", "Adam", "Kowalski");
        User user3 = new User(3, "piotr.wozniak@gmail.com", "123456", "Piotr", "Woźniak");

        users.add(user1);
        users.add(user2);
        users.add(user3);

        Part part1 = new Part(1, "Kingston 120GB 2,5\" SATA SSD A400", "99", PartType.HARD_DRIVE);
        Part part2 = new Part(2, "Samsung 500GB 2,5\" SATA SSD 860 EVO", "395", PartType.HARD_DRIVE);
        Part part3 = new Part(3, "MSI GeForce GTX 1050 TI GAMING X 4GB GDDR5", "859", PartType.GRAPHIC_CARD);
        Part part4 = new Part(4, "Gigabyte GeForce GTX 1060 WindForce II OC 6GB GDDR5", "1199", PartType.GRAPHIC_CARD);
        Part part5 = new Part(5, "Intel i7-7700 3.60GHz 8MB BOX", "1599", PartType.PROCESSOR);
        Part part6 = new Part(6, "Intel i7-8700K 3.70GHz 12MB", "2349", PartType.PROCESSOR);
        Part part7 = new Part(7, "Gigabyte Z370 AORUS Gaming K3", "529", PartType.MOTHERBOARD);
        Part part8 = new Part(8, "ASRock Z370 Pro4", "439", PartType.MOTHERBOARD);
        Part part9 = new Part(9, "HyperX 8GB 2400MHz Fury Black CL15", "319", PartType.RAM);
        Part part10 = new Part(10, "G.SKILL 16GB 3000MHz Aegis CL16 (2x8GB)", "589", PartType.RAM);
        Part part11 = new Part(11, "Zalman Z3 PLUS USB 3.0 czarna", "159", PartType.CASE);
        Part part12 = new Part(12, "SilentiumPC Regnum RG4T RGB Pure Black", "229", PartType.CASE);
        Part part13 = new Part(13, "SilentiumPC 600W Vero L2 Bronze", "209", PartType.POWER_SUPPLY);
        Part part14 = new Part(14, "be quiet! System Power 9 500W", "209", PartType.POWER_SUPPLY);
        Part part15 = new Part(15, "Thermalright HR-02 - Macho Rev. B", "209", PartType.COOLING);
        Part part16 = new Part(16, "SilentiumPC Fortis 3 HE1425 v2", "149", PartType.COOLING);

        parts.add(part1);
        parts.add(part2);
        parts.add(part3);
        parts.add(part4);
        parts.add(part5);
        parts.add(part6);
        parts.add(part7);
        parts.add(part8);
        parts.add(part9);
        parts.add(part10);
        parts.add(part11);
        parts.add(part12);
        parts.add(part13);
        parts.add(part14);
        parts.add(part15);
        parts.add(part16);

        ComputerSet computerSet1 = new ComputerSet(1, getRandomUser(), getPartByType(PartType.MOTHERBOARD), getPartByType(PartType.GRAPHIC_CARD), getPartByType(PartType.HARD_DRIVE), getPartByType(PartType.RAM), getPartByType(PartType.CASE), getPartByType(PartType.COOLING), getPartByType(PartType.POWER_SUPPLY), getPartByType(PartType.PROCESSOR));
        ComputerSet computerSet2 = new ComputerSet(2, getRandomUser(), getPartByType(PartType.MOTHERBOARD), getPartByType(PartType.GRAPHIC_CARD), getPartByType(PartType.HARD_DRIVE), getPartByType(PartType.RAM), getPartByType(PartType.CASE), getPartByType(PartType.COOLING), getPartByType(PartType.POWER_SUPPLY), getPartByType(PartType.PROCESSOR));
        ComputerSet computerSet3 = new ComputerSet(3, getRandomUser(), getPartByType(PartType.MOTHERBOARD), getPartByType(PartType.GRAPHIC_CARD), getPartByType(PartType.HARD_DRIVE), getPartByType(PartType.RAM), getPartByType(PartType.CASE), getPartByType(PartType.COOLING), getPartByType(PartType.POWER_SUPPLY), getPartByType(PartType.PROCESSOR));
        ComputerSet computerSet4 = new ComputerSet(4, getRandomUser(), getPartByType(PartType.MOTHERBOARD), getPartByType(PartType.GRAPHIC_CARD), getPartByType(PartType.HARD_DRIVE), getPartByType(PartType.RAM), getPartByType(PartType.CASE), getPartByType(PartType.COOLING), getPartByType(PartType.POWER_SUPPLY), getPartByType(PartType.PROCESSOR));
        ComputerSet computerSet5 = new ComputerSet(5, getRandomUser(), getPartByType(PartType.MOTHERBOARD), getPartByType(PartType.GRAPHIC_CARD), getPartByType(PartType.HARD_DRIVE), getPartByType(PartType.RAM), getPartByType(PartType.CASE), getPartByType(PartType.COOLING), getPartByType(PartType.POWER_SUPPLY), getPartByType(PartType.PROCESSOR));
        ComputerSet computerSet6 = new ComputerSet(6, getRandomUser(), getPartByType(PartType.MOTHERBOARD), getPartByType(PartType.GRAPHIC_CARD), getPartByType(PartType.HARD_DRIVE), getPartByType(PartType.RAM), getPartByType(PartType.CASE), getPartByType(PartType.COOLING), getPartByType(PartType.POWER_SUPPLY), getPartByType(PartType.PROCESSOR));

        computerSets.add(computerSet1);
        computerSets.add(computerSet2);
        computerSets.add(computerSet3);
        computerSets.add(computerSet4);
        computerSets.add(computerSet5);
        computerSets.add(computerSet6);
    }

    private User getRandomUser() {
        Random random = new Random();
        int randomNum = random.nextInt(users.size());
        return users.get(randomNum);
    }

    private Part getPartByType(final PartType partType) {
        List<Part> partsByType = new ArrayList<Part>();

        for(Part part : parts) {
            if(part.getType().equals(partType)) {
                partsByType.add(part);
            }
        }
        Random rand = new Random();
        int randomNum = rand.nextInt(partsByType.size());

        return partsByType.get(randomNum);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public List<Part> getAllParts() {
        return parts;
    }

    public List<ComputerSet> getAllComputerSets() {
        return computerSets;
    }

    public void saveUser(User user) {
        if(user.getId() == null) {
            user.setId(users.size() + 1);
            users.add(user);
        } else {
            users.set(user.getId() - 1, user);
        }
    }

    public void savePart(Part part) {
        if(part.getId() == null) {
            part.setId(parts.size() + 1);
            parts.add(part);
        } else {
            parts.set(part.getId() - 1, part);
        }
    }

    public void saveComputerSet(ComputerSet computerSet) {
        if(computerSet.getId() == null) {
            computerSet.setId(computerSets.size() + 1);
            computerSets.add(computerSet);
        } else {
            computerSets.set(computerSet.getId() - 1, computerSet);
        }
    }

    public User findUser(Integer id) {
        return users.get(id-1);
    }

    public Part findPart(Integer id) {
        return parts.get(id-1);
    }

    public ComputerSet findComputerSet(Integer id) {
        return computerSets.get(id-1);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void removePart(Part part) {
        parts.remove(part);
    }

    public void removeComputerSet(ComputerSet computerSet) {
        computerSets.remove(computerSet);
    }

    public List<Part> getPartsByType(PartType partType) {
        List<Part> selectedParts = new ArrayList<>();
        for(Part part : parts) {
            if(part.getType().equals(partType)) {
                selectedParts.add(part);
            }
        }
        return selectedParts;
    }
}
