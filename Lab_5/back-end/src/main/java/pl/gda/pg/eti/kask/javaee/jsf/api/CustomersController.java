package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.CustomerService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Customer;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/customers")
public class CustomersController {

    @Inject
    ViewService viewService;

    @Inject
    CustomerService customerService;

    @GET
    public Collection<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }


    @GET
    @Path("/findByName/{name}")
    public Collection<Customer> getAllCustomersByName(@PathParam("name") String name) {
        return viewService.findAllCustomersByName(name);
    }

    @POST
    public Response saveCustomer(@Valid Customer customer) {
        viewService.saveCustomer(customer);
        return created(uri(CustomersController.class, "getCustomer", customer.getId())).build();
    }

    @GET
    @Path("/{customer}")
    public Customer getCustomer(@PathParam("customer") Customer customer) {
        return customer;
    }

    @DELETE
    @Path("/{customer}")
    public Response deleteCustomer(@PathParam("customer") Customer customer) {
        viewService.removeCustomer(customer);
        return noContent().build();
    }

    @PUT
    @Path("/{customer}")
    public Response updateCustomer(@PathParam("customer") Customer originalCustomer, @Valid Customer updatedCustomer) {
        if (!originalCustomer.getId().equals(updatedCustomer.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        viewService.saveCustomer(updatedCustomer);
        return ok().build();
    }
}
