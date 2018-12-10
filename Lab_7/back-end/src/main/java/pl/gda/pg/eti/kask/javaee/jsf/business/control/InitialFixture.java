package pl.gda.pg.eti.kask.javaee.jsf.business.control;

import pl.gda.pg.eti.kask.javaee.jsf.business.Utils.Consts;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.PartService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.UserService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.*;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.permissions.CrudPermissions;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.permissions.RolePermissions;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static pl.gda.pg.eti.kask.javaee.jsf.api.CryptUtils.sha256;

@ApplicationScoped
public class InitialFixture {

    @PersistenceContext
    EntityManager em;

    @Inject
    PartService partService;

    @Inject
    UserService userService;

    @Transactional
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {

        List<String> list = new ArrayList<>();
        list.add(Consts.ADMIN);

        User user = new User("admin", sha256("admin"), "Jan", "Nowak", list);

        em.persist(user);

        List<String> list2 = new ArrayList<>();
        list2.add(Consts.USER);

        User user2 = new User("user", sha256("user"), "Piotr", "Zieliński", list2);

        em.persist(user2);

        User user3 = new User("use3", sha256("user3"), "Adam", "Klos", list2);
        User user4 = new User("user4", sha256("user4"), "Michał", "Ptak", list2);
        User user5 = new User("user5", sha256("user5"), "Piotr", "Woźniak", list2);
        User user6 = new User("user6", sha256("user6"), "Michał", "Wąs", list2);
        User user7 = new User("user7", sha256("user7"), "Cezary", "Kowalski", list2);
        User user8 = new User("user8", sha256("user8"), "Hubert", "Dowbor", list2);

        em.persist(user3);
        em.persist(user4);
        em.persist(user5);
        em.persist(user6);
        em.persist(user7);
        em.persist(user8);


        Part part1 = new Part("Kingston 120GB 2,5\" SATA SSD A400", "99", PartType.HARD_DRIVE);
        Part part2 = new Part("Samsung 500GB 2,5\" SATA SSD 860 EVO", "395", PartType.HARD_DRIVE);
        Part part3 = new Part("MSI GeForce GTX 1050 TI GAMING X 4GB GDDR5", "859", PartType.GRAPHIC_CARD);
        Part part4 = new Part("Gigabyte GeForce GTX 1060 WindForce II OC 6GB GDDR5", "1199", PartType.GRAPHIC_CARD);
        Part part5 = new Part("Intel i7-7700 3.60GHz 8MB BOX", "1599", PartType.PROCESSOR);
        Part part6 = new Part("Intel i7-8700K 3.70GHz 12MB", "2349", PartType.PROCESSOR);
        Part part7 = new Part("Gigabyte Z370 AORUS Gaming K3", "529", PartType.MOTHERBOARD);
        Part part8 = new Part("ASRock Z370 Pro4", "439", PartType.MOTHERBOARD);
        Part part9 = new Part("HyperX 8GB 2400MHz Fury Black CL15", "319", PartType.RAM);
        Part part10 = new Part("G.SKILL 16GB 3000MHz Aegis CL16 (2x8GB)", "589", PartType.RAM);
        Part part11 = new Part("Zalman Z3 PLUS USB 3.0 czarna", "159", PartType.CASE);
        Part part12 = new Part("SilentiumPC Regnum RG4T RGB Pure Black", "229", PartType.CASE);
        Part part13 = new Part("SilentiumPC 600W Vero L2 Bronze", "209", PartType.POWER_SUPPLY);
        Part part14 = new Part("be quiet! System Power 9 500W", "209", PartType.POWER_SUPPLY);
        Part part15 = new Part("Thermalright HR-02 - Macho Rev. B", "209", PartType.COOLING);
        Part part16 = new Part("SilentiumPC Fortis 3 HE1425 v2", "149", PartType.COOLING);

        em.persist(part1);
        em.persist(part2);
        em.persist(part3);
        em.persist(part4);
        em.persist(part5);
        em.persist(part6);
        em.persist(part7);
        em.persist(part8);
        em.persist(part9);
        em.persist(part10);
        em.persist(part11);
        em.persist(part12);
        em.persist(part13);
        em.persist(part14);
        em.persist(part15);
        em.persist(part16);


        for(int i = 0; i < 10; i++) {
            List<Part> partList = new ArrayList<>();
            partList.add(partService.getPartByType(PartType.MOTHERBOARD));
            partList.add(partService.getPartByType(PartType.GRAPHIC_CARD));
            partList.add(partService.getPartByType(PartType.HARD_DRIVE));
            partList.add(partService.getPartByType(PartType.RAM));
            partList.add(partService.getPartByType(PartType.CASE));
            partList.add(partService.getPartByType(PartType.COOLING));
            partList.add(partService.getPartByType(PartType.POWER_SUPPLY));
            partList.add(partService.getPartByType(PartType.PROCESSOR));
            ComputerSet computerSet = new ComputerSet(userService.getRandomUser(), new Date(), partList);
            em.persist(computerSet);
        }

        RolePermissions rolePermissions = new RolePermissions(Consts.ADMIN,
                new CrudPermissions(true, true, true, true, true),
                new CrudPermissions(true, true, true, true, true),
                new CrudPermissions(true, true, true, true, true));

        em.persist(rolePermissions);

        RolePermissions rolePermissions2 = new RolePermissions(Consts.USER,
                new CrudPermissions(true, true, true, true, true),
                new CrudPermissions(true, false, true, false, false),
                new CrudPermissions(true, false, true, false, false));

        em.persist(rolePermissions2);
    }
}
