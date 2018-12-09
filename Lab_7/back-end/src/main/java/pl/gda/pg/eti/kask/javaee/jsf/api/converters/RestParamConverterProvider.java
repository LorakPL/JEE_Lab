package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ComputerSetService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.PartService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.UserService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Part;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.inject.Inject;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class RestParamConverterProvider implements ParamConverterProvider {
    @Inject
    UserService userService;
    @Inject
    PartService partService;
    @Inject
    ComputerSetService computerSetService;

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        if(aClass.equals(User.class)) {
            return (ParamConverter<T>) new UserConverter(userService);
        } else if(aClass.equals(Part.class)) {
            return (ParamConverter<T>) new PartConverter(partService);
        } else if(aClass.equals(ComputerSet.class)) {
            return (ParamConverter<T>) new ComputerSetConverter(computerSetService);
        }
        return null;
    }
}
