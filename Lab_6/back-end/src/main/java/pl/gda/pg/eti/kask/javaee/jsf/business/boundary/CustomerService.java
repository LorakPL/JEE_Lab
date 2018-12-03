package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Customer;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.UserPass;

import javax.annotation.security.RolesAllowed;
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

@Stateless
public class CustomerService {
    @PersistenceContext
    EntityManager em;

    @Inject
    ComputerSetService computerSetService;

    @RolesAllowed(User.Roles.ADMIN)
    public Collection<Customer> findAllCustomers() {
        TypedQuery<Customer> query = em.createNamedQuery(Customer.Queries.FIND_ALL, Customer.class);
        return query.getResultList();
    }

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public Collection<Customer> findAllCustomersForComputerSets() {
        TypedQuery<Customer> query = em.createNamedQuery(Customer.Queries.FIND_ALL, Customer.class);
        return query.getResultList();
    }

    @RolesAllowed(User.Roles.ADMIN)
    public Collection<Customer> findAllCustomersByName(String name) {
        TypedQuery<Customer> query = em.createNamedQuery(Customer.Queries.FIND_BY_NAME, Customer.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @RolesAllowed(User.Roles.ADMIN)
    public Customer findCustomer(int id) {
        return em.find(Customer.class, id);
    }

    @Transactional
    @RolesAllowed(User.Roles.ADMIN)
    public void removeCustomer(Customer customer) {
        customer = em.merge(customer);
        List<ComputerSet> computerSets = new ArrayList<>(computerSetService.findAllComputerSetsByCustomerId(customer.getId()));
        for(ComputerSet computerSet : computerSets) {
            computerSetService.removeComputerSet(computerSet);
        }
        em.remove(customer);
    }

    @Transactional
    @RolesAllowed(User.Roles.ADMIN)
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

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public boolean checkIfEnoughCustomers() {
        List<Customer> customers = new ArrayList<>(findAllCustomers());
        if(customers.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public void changePassword(UserPass userPass) {
        String tmp = "";
    }
}
