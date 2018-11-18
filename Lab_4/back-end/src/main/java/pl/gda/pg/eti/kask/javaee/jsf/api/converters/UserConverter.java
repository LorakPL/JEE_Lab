package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ViewService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.ws.rs.ext.Provider;

@Provider
public class UserConverter extends AbstractEntityConverter<User> {
    public UserConverter() {
        super(User.class, User::getId, ViewService::findUser);
    }
}
