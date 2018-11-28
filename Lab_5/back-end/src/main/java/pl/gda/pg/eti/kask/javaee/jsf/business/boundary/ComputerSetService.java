package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
public class ComputerSetService {
    @PersistenceContext
    EntityManager em;

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public Collection<ComputerSet> findAllComputerSets() {
        TypedQuery<ComputerSet> query = em.createNamedQuery(ComputerSet.Queries.FIND_ALL, ComputerSet.class);
        return query.getResultList();
    }

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public Collection<ComputerSet> findAllComputerSetsByUserId(Integer id) {
        TypedQuery<ComputerSet> query = em.createNamedQuery(ComputerSet.Queries.FIND_ALL_BY_USER_ID, ComputerSet.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public Collection<ComputerSet> findAllComputerSetsByCustomerId(Integer id) {
        TypedQuery<ComputerSet> query = em.createNamedQuery(ComputerSet.Queries.FIND_ALL_BY_USER_ID, ComputerSet.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Transactional
    @RolesAllowed(User.Roles.ADMIN)
    public void removeComputerSetsByPart(Part part) {
        List<ComputerSet> ComputerSets = new ArrayList<>(findAllComputerSets());
        for(ComputerSet computerSet : ComputerSets) {
            for(Part partInComputerSetList : computerSet.getParts()) {
                if(partInComputerSetList.getId().equals(part.getId())) {
                    removeComputerSet(computerSet);
                    break;
                }
            }
        }
    }

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public ComputerSet findComputerSet(int id) {
        return em.find(ComputerSet.class, id);
    }

    @Transactional
    @RolesAllowed(User.Roles.ADMIN)
    public void removeComputerSet(ComputerSet computerSet) {
        computerSet = em.merge(computerSet);
        em.remove(computerSet);
    }

    @Transactional
    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public ComputerSet saveComputerSet(ComputerSet computerSet) {
        if (computerSet.getId() == null) {
            em.persist(computerSet);
        } else {
            computerSet = em.merge(computerSet);
        }

        return computerSet;
    }
}
