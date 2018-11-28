package pl.gda.pg.eti.kask.javaee.jsf.business.control;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Calendar.FEBRUARY;
import static java.util.Calendar.OCTOBER;
import static pl.gda.pg.eti.kask.javaee.jsf.api.CryptUtils.sha256;

@ApplicationScoped
public class InitialFixture {

    @PersistenceContext
    EntityManager em;

    @Inject
    ViewService viewService;

    @Transactional
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {

        //User user1 = new User("jan.nowak@gmail.com", "Jan", "Nowak");
        //User user2 = new User("adam.kowalski@gmail.com", "Adam", "Kowalski");
        //User user3 = new User("piotr.zielinski@gmail.com", "Piotr", "Zieliński");

        Customer customer1 = new Customer("jan.nowak@gmail.com", "Jan", "Nowak");
        Customer customer2 = new Customer("adam.kowalski@gmail.com", "Adam", "Kowalski");
        Customer customer3 = new Customer("piotr.zielinski@gmail.com", "Piotr", "Zieliński");

        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);

        //em.persist(user1);
        //em.persist(user2);
        //em.persist(user3);

        //effcc54ba75fb84cca1aadb6cae302e84c29dcb550e6e19e99c4916b89c69e0b

        List<String> list = new ArrayList<>();
        list.add("ADMIN");

        User user = new User("karol", sha256("karol"), list);

        em.persist(user);

        List<String> list2 = new ArrayList<>();
        list2.add("USER");

        User user2 = new User("karol2", sha256("karol2"), list2);

        em.persist(user2);



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
            partList.add(viewService.getPartByType(PartType.MOTHERBOARD));
            partList.add(viewService.getPartByType(PartType.GRAPHIC_CARD));
            partList.add(viewService.getPartByType(PartType.HARD_DRIVE));
            partList.add(viewService.getPartByType(PartType.RAM));
            partList.add(viewService.getPartByType(PartType.CASE));
            partList.add(viewService.getPartByType(PartType.COOLING));
            partList.add(viewService.getPartByType(PartType.POWER_SUPPLY));
            partList.add(viewService.getPartByType(PartType.PROCESSOR));
            //ComputerSet computerSet = new ComputerSet(viewService.getRandomUser(), partList);
            ComputerSet computerSet = new ComputerSet(viewService.getRandomCustomer(), partList);
            em.persist(computerSet);
        }
    }
}
