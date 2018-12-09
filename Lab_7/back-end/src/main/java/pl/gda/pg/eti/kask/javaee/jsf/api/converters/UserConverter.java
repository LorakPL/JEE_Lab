package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.UserService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.ws.rs.ext.ParamConverter;

public class UserConverter implements ParamConverter<User> {
    private UserService userService;

    UserConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User fromString(String s) {
        return userService.findUserById(Integer.parseInt(s));
    }

    @Override
    public String toString(User user) {
        return Integer.toString(user.getId());
    }
}
