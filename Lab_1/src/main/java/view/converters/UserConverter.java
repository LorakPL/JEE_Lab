package view.converters;

import model.User;
import service.ViewService;

import javax.enterprise.context.Dependent;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = User.class, managed = true)
@Dependent
public class UserConverter extends AbstractEntityConverter<User> {

    public UserConverter() {
        super(User::getId, ViewService::findUser);
    }
}
