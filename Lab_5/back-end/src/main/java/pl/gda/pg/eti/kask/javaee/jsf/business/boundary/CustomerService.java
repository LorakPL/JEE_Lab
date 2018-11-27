package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Customer;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Stateless
public class CustomerService {
    @PersistenceContext
    EntityManager em;

    @RolesAllowed(User.Roles.ADMIN)
    public Collection<Customer> findAllCustomers() {
        TypedQuery<Customer> query = em.createNamedQuery(Customer.Queries.FIND_ALL, Customer.class);
        return query.getResultList();
    }
}
