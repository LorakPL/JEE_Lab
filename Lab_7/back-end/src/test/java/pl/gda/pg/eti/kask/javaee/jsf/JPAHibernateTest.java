package pl.gda.pg.eti.kask.javaee.jsf;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import pl.gda.pg.eti.kask.javaee.jsf.business.Utils.Consts;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.PartType;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static pl.gda.pg.eti.kask.javaee.jsf.api.CryptUtils.sha256;

public class JPAHibernateTest {
    private static EntityManagerFactory entityManagerFactory;
    protected static EntityManager entityManager;

    @BeforeClass
    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("tests_db");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Before
    public void initializeDatabase() {
        List<String> list = new ArrayList<>();
        list.add(Consts.ADMIN);
        User user = new User("admin", sha256("admin"), "Jan", "Nowak", list);

        List<String> list2 = new ArrayList<>();
        list2.add(Consts.USER);

        User user2 = new User("user", sha256("user"), "Piotr", "Zieliński", list2);

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

        List<Part> partList = new ArrayList<>();
        partList.add(part7);
        partList.add(part3);
        partList.add(part1);
        partList.add(part9);
        partList.add(part11);
        partList.add(part15);
        partList.add(part13);
        partList.add(part5);

        List<Part> partList2 = new ArrayList<>();
        partList2.add(part8);
        partList2.add(part4);
        partList2.add(part2);
        partList2.add(part10);
        partList2.add(part12);
        partList2.add(part16);
        partList2.add(part14);
        partList2.add(part6);

        ComputerSet computerSet = new ComputerSet(user, new Date(), partList);
        ComputerSet computerSet2 = new ComputerSet(user2, new Date(), partList2);

        entityManager.getTransaction().begin();

        entityManager.createQuery("DELETE FROM ComputerSet").executeUpdate();
        entityManager.createQuery("DELETE FROM Part").executeUpdate();
        entityManager.createQuery("DELETE FROM User").executeUpdate();

        entityManager.persist(user);
        entityManager.persist(user2);

        entityManager.persist(part1);
        entityManager.persist(part2);
        entityManager.persist(part3);
        entityManager.persist(part4);
        entityManager.persist(part5);
        entityManager.persist(part6);
        entityManager.persist(part7);
        entityManager.persist(part8);
        entityManager.persist(part9);
        entityManager.persist(part10);
        entityManager.persist(part11);
        entityManager.persist(part12);
        entityManager.persist(part13);
        entityManager.persist(part14);
        entityManager.persist(part15);
        entityManager.persist(part16);

        entityManager.persist(computerSet);
        entityManager.persist(computerSet2);

        entityManager.getTransaction().commit();
    }

    @AfterClass
    public static void tearDown(){
        entityManager.clear();
        entityManager.close();
        entityManagerFactory.close();
    }
}
