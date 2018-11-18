package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.PartType;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@ApplicationScoped
public class ViewService implements Serializable {

    @PersistenceContext
    EntityManager em;

    public Collection<User> findAllUsers() {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_ALL, User.class);
        return query.getResultList();
    }

    public Collection<User> findAllUsersByName(String name) {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_BY_NAME, User.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public User findUser(int id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void removeUser(User user) {
        user = em.merge(user);
        em.remove(user);
    }

    @Transactional
    public User saveUser(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            user = em.merge(user);
        }

        return user;
    }

    public Collection<Part> findAllParts() {
        TypedQuery<Part> query = em.createNamedQuery(Part.Queries.FIND_ALL, Part.class);
        return query.getResultList();
    }

    public Part findPart(int id) {
        return em.find(Part.class, id);
    }

    @Transactional
    public void removePart(Part part) {
        part = em.merge(part);
        em.remove(part);
    }

    @Transactional
    public Part savePart(Part part) {
        if (part.getId() == null) {
            em.persist(part);
        } else {
            part = em.merge(part);
        }

        return part;
    }

    public Collection<PartType> getAllPartTypes() {
        List<PartType> enumValues = new ArrayList<PartType>(EnumSet.allOf(PartType.class));
        return Collections.synchronizedList(enumValues);
    }
}
