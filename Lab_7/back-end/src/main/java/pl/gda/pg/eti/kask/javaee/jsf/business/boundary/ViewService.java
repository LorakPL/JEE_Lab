package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.Utils.Consts;
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
        TypedQuery<User> query = em.createNamedQuery(Consts.FIND_USER_BY_LOGIN, User.class);
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
