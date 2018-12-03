package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.api.filters.Authorize;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.CustomerService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Customer;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.UserPass;

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
    CustomerService customerService;

    @PUT
    @Path("/newPass")
    @Authorize
    public Response changePassword(UserPass userPass) {
        customerService.changePassword(userPass);
        return Response.ok().build();
    }

    @GET
    @Authorize
    public Collection<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GET
    @Path("/cs")
    @Authorize
    public Collection<Customer> getAllCustomersForComputerSets() {
        return customerService.findAllCustomersForComputerSets();
    }


    @GET
    @Path("/findByName/{name}")
    @Authorize
    public Collection<Customer> getAllCustomersByName(@PathParam("name") String name) {
        return customerService.findAllCustomersByName(name);
    }

    @POST
    @Authorize
    public Response saveCustomer(@Valid Customer customer) {
        customerService.saveCustomer(customer);
        return created(uri(CustomersController.class, "getCustomer", customer.getId())).build();
    }

    @GET
    @Path("/{customer}")
    @Authorize
    public Customer getCustomer(@PathParam("customer") Customer customer) {
        return customer;
    }

    @DELETE
    @Path("/{customer}")
    @Authorize
    public Response deleteCustomer(@PathParam("customer") Customer customer) {
        customerService.removeCustomer(customer);
        return noContent().build();
    }

    @PUT
    @Path("/{customer}")
    @Authorize
    public Response updateCustomer(@PathParam("customer") Customer originalCustomer, @Valid Customer updatedCustomer) {
        if (!originalCustomer.getId().equals(updatedCustomer.getId())) {
            return status(Response.Status.BAD_REQUEST).build();
        }

        customerService.saveCustomer(updatedCustomer);
        return ok().build();
    }
}
