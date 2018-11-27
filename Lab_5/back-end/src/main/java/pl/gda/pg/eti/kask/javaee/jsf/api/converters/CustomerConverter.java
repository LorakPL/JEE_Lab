package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Customer;

import javax.ws.rs.ext.Provider;

@Provider
public class CustomerConverter extends AbstractEntityConverter<Customer> {
    public CustomerConverter() {
        super(Customer.class, Customer::getId, ViewService::findCustomer);
    }
}
