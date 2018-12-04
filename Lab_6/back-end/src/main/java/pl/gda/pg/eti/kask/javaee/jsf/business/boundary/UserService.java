package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;

    @Inject
    ComputerSetService computerSetService;

    @Resource
    SessionContext sessionCtx;

    public List<User> findAllUsers() {
        return em.createNamedQuery(User.Queries.FIND_ALL, User.class).getResultList();
    }

    public User findUser(String login) {
        return findUserByLogin(login);
    }

    public User findCurrentUser() {
        String login = sessionCtx.getCallerPrincipal().getName();
        return findUserByLogin(login);
    }

    private User findUserByLogin(String login) {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_BY_LOGIN, User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    public Collection<User> findAllUsersByLogin(String login) {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_BY_LOGIN, User.class);
        query.setParameter("login", login);
        return query.getResultList();
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

    @Transactional
    public void removeUser(User user) {
        user = em.merge(user);
        List<ComputerSet> computerSets = new ArrayList<>(computerSetService.findAllComputerSetsByUserId(user.getId()));
        for(ComputerSet computerSet : computerSets) {
            computerSetService.removeComputerSet(computerSet);
        }
        em.remove(user);
    }

    public Collection<User> findAllUsersForComputerSets() {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_ALL, User.class);
        return query.getResultList();
    }

    public boolean checkIfEnoughUsers() {
        List<User> users = new ArrayList<>(findAllUsers());
        if(users.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
