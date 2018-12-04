package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.PartType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Stateless
public class PartService {
    @PersistenceContext
    EntityManager em;

    @Inject
    ComputerSetService computerSetService;

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
        computerSetService.removeComputerSetsByPart(part);
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

    public Part getPartByType(PartType partType) {
        TypedQuery<Part> query = em.createNamedQuery(Part.Queries.FIND_BY_TYPE, Part.class);
        query.setParameter("partType", partType);
        List<Part> parts = new ArrayList<>(query.getResultList());
        return parts.get(ThreadLocalRandom.current().nextInt(0, parts.size()));
    }

    public boolean checkIfEnoughParts() {
        List<Part> parts = new ArrayList<>(findAllParts());
        TreeSet<PartType> partTypes = new TreeSet<>();
        for (Part part: parts) {
            partTypes.add(part.getType());
        }
        if(partTypes.size() < 8) {
            return false;
        } else {
            return true;
        }
    }
}
