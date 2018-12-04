package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.*;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static pl.gda.pg.eti.kask.javaee.jsf.api.CryptUtils.sha256;

@ApplicationScoped
public class ViewService implements Serializable {

    @PersistenceContext
    EntityManager em;

    public User findUser(int id) {
        return em.find(User.class, id);
    }


    public Collection<User> findAllUsers() {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_ALL, User.class);
        return query.getResultList();
    }

    public User getRandomUser() {
        List<User> users = new ArrayList<>(findAllUsers());
        return em.find(User.class, ThreadLocalRandom.current().nextInt(1, users.size() + 1));

    }

    public Part findPart(int id) {
        return em.find(Part.class, id);
    }

    public Part getPartByType(PartType partType) {
        TypedQuery<Part> query = em.createNamedQuery(Part.Queries.FIND_BY_TYPE, Part.class);
        query.setParameter("partType", partType);
        List<Part> parts = new ArrayList<>(query.getResultList());
        return parts.get(ThreadLocalRandom.current().nextInt(0, parts.size()));
    }

    public ComputerSet findComputerSet(int id) {
        return em.find(ComputerSet.class, id);
    }

    @Transactional
    public void changePassword(UserPass userPass) {
        User user = findUser2(userPass.getUsername());
        user.setPassword(sha256(userPass.getPassword()));
        em.merge(user);
    }

    public User findUser2(String login) {
        return findUserByLogin2(login);
    }

    private User findUserByLogin2(String login) {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_BY_LOGIN, User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    @Transactional
    public void saveUser(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }
}
