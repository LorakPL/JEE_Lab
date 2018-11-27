package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.*;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class ViewService implements Serializable {

    @PersistenceContext
    EntityManager em;

    public Collection<User> findAllUsers() {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_ALL, User.class);
        return query.getResultList();
    }

    public Collection<User> findAllUsersByName(String name) {
        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_BY_LOGIN, User.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public User findUser(int id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void removeUser(User user) {
        user = em.merge(user);
        List<ComputerSet> computerSets = new ArrayList<>(findAllComputerSetsByUserId(user.getId()));
        for(ComputerSet computerSet : computerSets) {
            removeComputerSet(computerSet);
        }
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

    public User getRandomUser() {
        List<User> users = new ArrayList<>(findAllUsers());
        return em.find(User.class, ThreadLocalRandom.current().nextInt(1, users.size() + 1));
    }

    public Collection<Customer> findAllCustomers() {
        TypedQuery<Customer> query = em.createNamedQuery(Customer.Queries.FIND_ALL, Customer.class);
        return query.getResultList();
    }

    public Collection<Customer> findAllCustomersByName(String name) {
        TypedQuery<Customer> query = em.createNamedQuery(Customer.Queries.FIND_BY_NAME, Customer.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public Customer findCustomer(int id) {
        return em.find(Customer.class, id);
    }

    @Transactional
    public void removeCustomer(Customer customer) {
        customer = em.merge(customer);
        List<ComputerSet> computerSets = new ArrayList<>(findAllComputerSetsByCustomerId(customer.getId()));
        for(ComputerSet computerSet : computerSets) {
            removeComputerSet(computerSet);
        }
        em.remove(customer);
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        if (customer.getId() == null) {
            em.persist(customer);
        } else {
            customer = em.merge(customer);
        }

        return customer;
    }

    public Customer getRandomCustomer() {
        List<Customer> customers = new ArrayList<>(findAllCustomers());
        return em.find(Customer.class, ThreadLocalRandom.current().nextInt(1, customers.size() + 1));
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
        removeComputerSetsByPart(part);
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

    public Collection<ComputerSet> findAllComputerSets() {
        TypedQuery<ComputerSet> query = em.createNamedQuery(ComputerSet.Queries.FIND_ALL, ComputerSet.class);
        return query.getResultList();
    }

    public Collection<ComputerSet> findAllComputerSetsByUserId(Integer id) {
        TypedQuery<ComputerSet> query = em.createNamedQuery(ComputerSet.Queries.FIND_ALL_BY_USER_ID, ComputerSet.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public Collection<ComputerSet> findAllComputerSetsByCustomerId(Integer id) {
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
        } else {
            computerSet = em.merge(computerSet);
        }

        return computerSet;
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

    public boolean checkIfEnoughCustomers() {
        List<Customer> customers = new ArrayList<>(findAllCustomers());
        if(customers.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
    public boolean checkIfEnoughUsers() {
        List<User> users = new ArrayList<>(findAllUsers());
        if(users.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    */
}
