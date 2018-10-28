package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.PartType;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class ViewService implements Serializable {

    private final SortedMap<Integer, User> users = new TreeMap<>();
    private final TreeSet<Integer> deletedUserId = new TreeSet<>();

    private final SortedMap<Integer, Part> parts = new TreeMap<>();
    private final TreeSet<Integer> deletedPartId = new TreeSet<>();

    private final SortedMap<Integer, ComputerSet> computerSets = new TreeMap<>();
    private final TreeSet<Integer> deletedComputerSetId = new TreeSet<>();

    @PostConstruct
    public void init() {
        //User user1 = new User(1, "jan.nowak@gmail.com", "12345");
        //User user2 = new User(1, "jan.nowak@gmail.com", "12345");
        //User user3 = new User(1, "jan.nowak@gmail.com", "12345");

        User user1 = new User(1, "jan.nowak@gmail.com", "Jan", "Nowak");
        User user2 = new User(2, "adam.kowalski@gmail.com", "Adam", "Kowalski");
        User user3 = new User(3, "piotr.zielinski@gmail.com", "Piotr", "Zieliński");

        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
        users.put(user3.getId(), user3);

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

        parts.put(part1.getId(), part1);
        parts.put(part2.getId(), part2);
        parts.put(part3.getId(), part3);
        parts.put(part4.getId(), part4);
        parts.put(part5.getId(), part5);
        parts.put(part6.getId(), part6);
        parts.put(part7.getId(), part7);
        parts.put(part8.getId(), part8);
        parts.put(part9.getId(), part9);
        parts.put(part10.getId(), part10);
        parts.put(part11.getId(), part11);
        parts.put(part12.getId(), part12);
        parts.put(part13.getId(), part13);
        parts.put(part14.getId(), part14);
        parts.put(part15.getId(), part15);
        parts.put(part16.getId(), part16);


        for(int i = 0; i < 10; i++) {
            List<Part> partList = new ArrayList<>();
            partList.add(getPartByType(PartType.MOTHERBOARD));
            partList.add(getPartByType(PartType.GRAPHIC_CARD));
            partList.add(getPartByType(PartType.HARD_DRIVE));
            partList.add(getPartByType(PartType.RAM));
            partList.add(getPartByType(PartType.CASE));
            partList.add(getPartByType(PartType.COOLING));
            partList.add(getPartByType(PartType.POWER_SUPPLY));
            partList.add(getPartByType(PartType.PROCESSOR));
            ComputerSet computerSet = new ComputerSet(i + 1, getRandomUser(), partList);
            computerSets.put(computerSet.getId(), computerSet);

        }
    }

    private User getRandomUser() {
        return users.get(ThreadLocalRandom.current().nextInt(1, users.size() + 1));
    }

    private Part getPartByType(final PartType partType) {
        List<Part> partsByType = new ArrayList<Part>();

        for(Part part : parts.values()) {
            if(part.getType().equals(partType)) {
                partsByType.add(part);
            }
        }
        Random rand = new Random();
        int randomNum = rand.nextInt(partsByType.size());

        return partsByType.get(randomNum);
    }

    public Collection<User> findAllUsers() {
        return users.values();
    }

    public Collection<Part> findAllParts() {
        return parts.values();
    }

    public Collection<ComputerSet> findAllComputerSets() {
        return computerSets.values();
    }

    public User findUser(int id) {
        return users.get(id);
    }

    public Part findPart(int id) {
        return parts.get(id);
    }

    public ComputerSet findComputerSet(int id) {
        return computerSets.get(id);
    }

    public void saveUser(User user) {
        if (user.getId() == null) {
            if (users.isEmpty()) {
                user.setId(deletedUserId.last() + 1);
            } else if(deletedUserId.isEmpty())  {
                user.setId(users.lastKey() + 1);
            } else {
                if ((deletedUserId.last() > users.lastKey()) && !deletedUserId.isEmpty()) {
                    user.setId(deletedUserId.last() + 1);
                } else {
                    user.setId(users.lastKey() + 1);
                }
            }
        }

        users.put(user.getId(), user);
    }

    public void savePart(Part part) {
        if (part.getId() == null) {
            if (parts.isEmpty()) {
                part.setId(deletedPartId.last() + 1);
            } else if(deletedPartId.isEmpty())  {
                part.setId(parts.lastKey() + 1);
            } else {
                if ((deletedPartId.last() > parts.lastKey()) && !deletedPartId.isEmpty()) {
                    part.setId(deletedPartId.last() + 1);
                } else {
                    part.setId(parts.lastKey() + 1);
                }
            }
        }

        parts.put(part.getId(), part);
    }

    public void saveComputerSet(ComputerSet computerSet) {
        if (computerSet.getId() == null) {
            if (computerSets.isEmpty()) {
                computerSet.setId(deletedComputerSetId.last() + 1);
            } else if(deletedComputerSetId.isEmpty())  {
                computerSet.setId(computerSets.lastKey() + 1);
            } else {
                if ((deletedComputerSetId.last() > computerSets.lastKey()) && !deletedComputerSetId.isEmpty()) {
                    computerSet.setId(deletedComputerSetId.last() + 1);
                } else {
                    computerSet.setId(computerSets.lastKey() + 1);
                }
            }
        }

        computerSets.put(computerSet.getId(), computerSet);
    }

    public void removeUser(User user) {
        users.remove(user.getId());
        deletedUserId.add(user.getId());
    }

    public void removePart(Part part) {
        parts.remove(part.getId());
        deletedPartId.add(part.getId());
    }

    public void removeComputerSet(ComputerSet computerSet) {
        computerSets.remove(computerSet.getId());
        deletedComputerSetId.add(computerSet.getId());
    }

    public Collection<PartType> getAllPartTypes() {
        List<PartType> enumValues = new ArrayList<PartType>(EnumSet.allOf(PartType.class));
        return Collections.synchronizedList(enumValues);
    }

    public boolean checkIfEnoughParts() {
        TreeSet<PartType> partTypes = new TreeSet<>();
        for (Part part: parts.values()) {
            partTypes.add(part.getType());
        }
        if(partTypes.size() < 8) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkIfEnoughUsers() {
        if(users.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<ComputerSet> getComputerSetsByUser(User user) {
        List<ComputerSet> computerSets = new ArrayList<>();
        for(ComputerSet computerSet : this.computerSets.values()) {
            if(computerSet.getUser() == user) {
                computerSets.add(computerSet);
            }
        }
        return computerSets;
    }

    public List<ComputerSet> getComputerSetsByPart(Part part) {
        List<ComputerSet> computerSets = new ArrayList<>();
        for(ComputerSet computerSet : this.computerSets.values()) {
            for(Part partInComputerSetList : computerSet.getParts()) {
                if(partInComputerSetList == part) {
                    computerSets.add(computerSet);
                    break;
                }
            }
        }
        return computerSets;
    }


}
