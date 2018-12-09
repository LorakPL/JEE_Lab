package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.api.Events.ComputerSetCreation;
import pl.gda.pg.eti.kask.javaee.jsf.api.Events.ComputerSetEvent;
import pl.gda.pg.eti.kask.javaee.jsf.api.Events.ComputerSetModification;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Stateless
public class ComputerSetService {
    @PersistenceContext
    EntityManager em;

    @Inject
    private Event<ComputerSetEvent> computerSetEventEvent;

    public Collection<ComputerSet> findAllComputerSets() {
        TypedQuery<ComputerSet> query = em.createNamedQuery(ComputerSet.Queries.FIND_ALL, ComputerSet.class);
        List<ComputerSet> list = query.getResultList();
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public Collection<ComputerSet> findAllComputerSetsByUserId(Integer id) {
        TypedQuery<ComputerSet> query = em.createNamedQuery(ComputerSet.Queries.FIND_ALL_BY_USER_ID, ComputerSet.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Transactional
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

    public ComputerSet findComputerSet(int id) {
        return em.find(ComputerSet.class, id);
    }

    @Transactional
    public void removeComputerSet(ComputerSet computerSet) {
        computerSet = em.merge(computerSet);
        em.remove(computerSet);
    }

    @Transactional
    public ComputerSet saveComputerSet(ComputerSet computerSet) {
        if (computerSet.getId() == null) {
            em.persist(computerSet);
            //computerSetEventEvent.select(ComputerSetCreation.Literal).fire(ComputerSetEvent.of(computerSet));
            computerSetEventEvent.fire(new ComputerSetEvent(computerSet));
        } else {
            computerSet = em.merge(computerSet);
            //computerSetEventEvent.select(ComputerSetModification.Literal).fire(ComputerSetEvent.of(computerSet));
            computerSetEventEvent.fire(new ComputerSetEvent(computerSet));
        }
        return computerSet;
    }
}
