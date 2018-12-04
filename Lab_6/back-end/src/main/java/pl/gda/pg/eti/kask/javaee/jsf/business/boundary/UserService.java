package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Customer;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.UserPass;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
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
import java.util.concurrent.ThreadLocalRandom;

import static pl.gda.pg.eti.kask.javaee.jsf.api.CryptUtils.sha256;

@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;

    @Inject
    ComputerSetService computerSetService;

    @Resource
    SessionContext sessionCtx;

    //@RolesAllowed(User.Roles.ADMIN)
    public List<User> findAllUsers() {
        return em.createNamedQuery(User.Queries.FIND_ALL, User.class).getResultList();
    }

    //@RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public User findUser(String login) {
        return findUserByLogin(login);
    }

    //@RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public User findCurrentUser() {
        String login = sessionCtx.getCallerPrincipal().getName();
        return findUserByLogin(login);
    }

    //@RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    private User findUserByLogin(String login) {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_BY_LOGIN, User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    /*
    public void changePassword(UserPass userPass) {
        User user = findUserForPasswordChange(userPass.getUsername());
        user.setPassword(sha256(userPass.getPassword()));
        em.merge(user);
    }
    */

    private User findUserForPasswordChange(String login) {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_BY_LOGIN, User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    /*
    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public void changePassword(UserPass userPass) {
        User user = findUser(userPass.getUsername());
        user.setPassword(sha256(userPass.getPassword()));
        em.merge(user);
    }
    */

    //@RolesAllowed(User.Roles.ADMIN)
    public Collection<User> findAllUsersByLogin(String login) {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_BY_LOGIN, User.class);
        query.setParameter("login", login);
        return query.getResultList();
    }

    @Transactional
    //@RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public User saveUser(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            user = em.merge(user);
        }

        return user;
    }

    /*
    @Transactional
    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public void changePassword(UserPass userPass) {
        User user = findUser(userPass.getUsername());
        user.setPassword(sha256(userPass.getPassword()));
        em.merge(user);
    }
    */

    @Transactional
    //@RolesAllowed(User.Roles.ADMIN)
    public void removeUser(User user) {
        user = em.merge(user);
        List<ComputerSet> computerSets = new ArrayList<>(computerSetService.findAllComputerSetsByCustomerId(user.getId()));
        for(ComputerSet computerSet : computerSets) {
            computerSetService.removeComputerSet(computerSet);
        }
        em.remove(user);
    }

    //@RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
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
