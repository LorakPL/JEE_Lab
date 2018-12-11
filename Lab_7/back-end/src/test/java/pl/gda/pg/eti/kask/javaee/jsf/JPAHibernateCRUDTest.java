package pl.gda.pg.eti.kask.javaee.jsf;

import org.junit.Test;
import pl.gda.pg.eti.kask.javaee.jsf.business.Utils.Consts;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.PartType;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static pl.gda.pg.eti.kask.javaee.jsf.api.CryptUtils.sha256;

public class JPAHibernateCRUDTest extends JPAHibernateTest {
    @Test
    public void testUserExists() {
        User user = entityManager.find(User.class, 1);
        assertNotNull(user);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = entityManager.createNamedQuery(Consts.FIND_ALL_USERS, User.class).getResultList();

        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testCreateUser() {
        List<User> usersBefore = entityManager.createNamedQuery(Consts.FIND_ALL_USERS, User.class).getResultList();
        List<String> list2 = new ArrayList<>();
        list2.add(Consts.USER);
        User user = new User("karol", sha256("karol"), "Karol", "Karol", list2);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        List<User> usersAfter = entityManager.createNamedQuery(Consts.FIND_ALL_USERS, User.class).getResultList();

        assertNotNull(usersAfter);
        assertEquals(usersBefore.size()+1, usersAfter.size());
    }

    @Test
    public void testDeleteUser() {
        List<User> usersBefore = entityManager.createNamedQuery(Consts.FIND_ALL_USERS, User.class).getResultList();
        TypedQuery<User> query = entityManager.createNamedQuery(Consts.FIND_USER_BY_LOGIN, User.class);
        query.setParameter("login", "user");
        User user = query.getSingleResult();

        TypedQuery<ComputerSet> queryComputerSets = entityManager.createNamedQuery(Consts.FIND_ALL_COMPUTER_SETS_BY_USER_ID, ComputerSet.class);
        queryComputerSets.setParameter("id", user.getId());
        List<ComputerSet> computerSets = queryComputerSets.getResultList();

        entityManager.getTransaction().begin();
        for(ComputerSet computerSet : computerSets) {
            entityManager.remove(computerSet);
        }
        entityManager.remove(user);
        entityManager.getTransaction().commit();

        List<User> usersAfter = entityManager.createNamedQuery(Consts.FIND_ALL_USERS, User.class).getResultList();

        assertNotNull(usersAfter);
        assertEquals(usersBefore.size()-1, usersAfter.size());
    }

    @Test
    public void testUpdateUser() {
        TypedQuery<User> query = entityManager.createNamedQuery(Consts.FIND_USER_BY_LOGIN, User.class);
        query.setParameter("login", "user");
        User user = query.getSingleResult();

        assertEquals("Piotr", user.getName());

        user.setName("Karol");
        entityManager.merge(user);

        user = query.getSingleResult();

        assertEquals("Karol", user.getName());
    }

    @Test
    public void testGetAllParts() {
        List<Part> parts = entityManager.createNamedQuery(Consts.FIND_ALL_PARTS, Part.class).getResultList();

        assertNotNull(parts);
        assertEquals(16, parts.size());
    }

    @Test
    public void testCreatePart() {
        List<Part> partsBefore = entityManager.createNamedQuery(Consts.FIND_ALL_PARTS, Part.class).getResultList();
        Part part = new Part("Intel Core i9", "1999", PartType.PROCESSOR);
        entityManager.getTransaction().begin();
        entityManager.persist(part);
        entityManager.getTransaction().commit();

        List<Part> partsAfter = entityManager.createNamedQuery(Consts.FIND_ALL_PARTS, Part.class).getResultList();

        assertNotNull(partsAfter);
        assertEquals(partsBefore.size()+1, partsAfter.size());
    }

    @Test
    public void testDeletePart() {
        List<Part> partsBefore = entityManager.createNamedQuery(Consts.FIND_ALL_PARTS, Part.class).getResultList();
        TypedQuery<Part> query = entityManager.createNamedQuery(Consts.FIND_PART_BY_TYPE, Part.class);
        query.setParameter("partType", PartType.PROCESSOR);
        List<Part> parts = query.getResultList();

        List<ComputerSet> ComputerSets = entityManager.createNamedQuery(Consts.FIND_ALL_COMPUTER_SETS, ComputerSet.class).getResultList();
        entityManager.getTransaction().begin();
        for(ComputerSet computerSet : ComputerSets) {
            for(Part partInComputerSetList : computerSet.getParts()) {
                if(partInComputerSetList.getId().equals(parts.get(0).getId())) {
                    entityManager.remove(computerSet);
                    break;
                }
            }
        }

        entityManager.remove(parts.get(0));
        entityManager.getTransaction().commit();

        List<Part> partsAfter = entityManager.createNamedQuery(Consts.FIND_ALL_PARTS, Part.class).getResultList();

        assertNotNull(partsAfter);
        assertEquals(partsBefore.size()-1, partsAfter.size());
    }

    @Test
    public void testUpdatePart() {
        TypedQuery<Part> query = entityManager.createNamedQuery(Consts.FIND_PART_BY_NAME, Part.class);
        query.setParameter("name", "MSI GeForce GTX 1050 TI GAMING X 4GB GDDR5");
        Part part = query.getSingleResult();

        assertEquals("859", part.getPrice());

        part.setPrice("11111");
        entityManager.merge(part);

        part = query.getSingleResult();

        assertEquals("11111", part.getPrice());
    }

    @Test
    public void testGetAllComputerSets() {
        List<ComputerSet> computerSets = entityManager.createNamedQuery(Consts.FIND_ALL_COMPUTER_SETS, ComputerSet.class).getResultList();

        assertNotNull(computerSets);
        assertEquals(2, computerSets.size());
    }

    @Test
    public void testCreateComputerSet() {
        List<ComputerSet> computerSetsBefore = entityManager.createNamedQuery(Consts.FIND_ALL_COMPUTER_SETS, ComputerSet.class).getResultList();

        TypedQuery<User> query = entityManager.createNamedQuery(Consts.FIND_USER_BY_LOGIN, User.class);
        query.setParameter("login", "user");
        User user = query.getSingleResult();

        List<Part> partList = new ArrayList<>();
        TypedQuery<Part> partTypedQuery = entityManager.createNamedQuery(Consts.FIND_PART_BY_NAME, Part.class);
        partTypedQuery.setParameter("name", "Gigabyte Z370 AORUS Gaming K3");
        partList.add(partTypedQuery.getSingleResult());
        partTypedQuery.setParameter("name", "Gigabyte GeForce GTX 1060 WindForce II OC 6GB GDDR5");
        partList.add(partTypedQuery.getSingleResult());
        partTypedQuery.setParameter("name", "Kingston 120GB 2,5\" SATA SSD A400");
        partList.add(partTypedQuery.getSingleResult());
        partTypedQuery.setParameter("name", "G.SKILL 16GB 3000MHz Aegis CL16 (2x8GB)");
        partList.add(partTypedQuery.getSingleResult());
        partTypedQuery.setParameter("name", "Zalman Z3 PLUS USB 3.0 czarna");
        partList.add(partTypedQuery.getSingleResult());
        partTypedQuery.setParameter("name", "SilentiumPC Fortis 3 HE1425 v2");
        partList.add(partTypedQuery.getSingleResult());
        partTypedQuery.setParameter("name", "SilentiumPC 600W Vero L2 Bronze");
        partList.add(partTypedQuery.getSingleResult());
        partTypedQuery.setParameter("name", "Intel i7-8700K 3.70GHz 12MB");
        partList.add(partTypedQuery.getSingleResult());

        ComputerSet computerSet = new ComputerSet(user, new Date(), partList);

        entityManager.getTransaction().begin();
        entityManager.persist(computerSet);
        entityManager.getTransaction().commit();

        List<ComputerSet> computerSetsAfter = entityManager.createNamedQuery(Consts.FIND_ALL_COMPUTER_SETS, ComputerSet.class).getResultList();

        assertNotNull(computerSetsAfter);
        assertEquals(computerSetsBefore.size()+1, computerSetsAfter.size());
    }

    @Test
    public void testDeleteComputerSet() {
        List<ComputerSet> computerSetsBefore = entityManager.createNamedQuery(Consts.FIND_ALL_COMPUTER_SETS, ComputerSet.class).getResultList();

        TypedQuery<User> query = entityManager.createNamedQuery(Consts.FIND_USER_BY_LOGIN, User.class);
        query.setParameter("login", "user");
        User user = query.getSingleResult();

        TypedQuery<ComputerSet> queryComputerSets = entityManager.createNamedQuery(Consts.FIND_ALL_COMPUTER_SETS_BY_USER_ID, ComputerSet.class);
        queryComputerSets.setParameter("id", user.getId());
        List<ComputerSet> computerSets = queryComputerSets.getResultList();

        entityManager.getTransaction().begin();
        entityManager.remove(computerSets.get(0));
        entityManager.getTransaction().commit();

        List<ComputerSet> computerSetsAfter = entityManager.createNamedQuery(Consts.FIND_ALL_COMPUTER_SETS, ComputerSet.class).getResultList();

        assertNotNull(computerSetsAfter);
        assertEquals(computerSetsBefore.size()-1, computerSetsAfter.size());
    }

    @Test
    public void testUpdateComputerSet() {
        TypedQuery<ComputerSet> query = entityManager.createNamedQuery(Consts.FIND_COMPUTER_SET_BY_ID, ComputerSet.class);
        query.setParameter("id", 17);
        ComputerSet computerSet = query.getSingleResult();

        assertEquals("admin", computerSet.getUser().getLogin());

        TypedQuery<User> userTypedQuery = entityManager.createNamedQuery(Consts.FIND_USER_BY_LOGIN, User.class);
        userTypedQuery.setParameter("login", "user");
        User user = userTypedQuery.getSingleResult();

        computerSet.setUser(user);
        entityManager.merge(computerSet);

        computerSet = query.getSingleResult();

        assertEquals("user", computerSet.getUser().getLogin());
    }

    @Test
    public void testFilterUsersByLogin() {
        List<User> usersBefore = entityManager.createNamedQuery(Consts.FIND_ALL_USERS, User.class).getResultList();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        criteriaQuery.where(builder.equal(root.get("login"), "admin"));

        List<User> usersAfter = entityManager.createQuery(criteriaQuery).getResultList();

        assertEquals(usersBefore.size()-1, usersAfter.size());
    }

    @Test
    public void testSortDescUsersById() {
        List<User> usersBefore = entityManager.createNamedQuery(Consts.FIND_ALL_USERS, User.class).getResultList();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        criteriaQuery.orderBy(builder.desc(root.get("id")));

        List<User> usersAfter = entityManager.createQuery(criteriaQuery).getResultList();

        assertEquals(usersBefore.get(1).getId(), usersAfter.get(0).getId());
    }
}
