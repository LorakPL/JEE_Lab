package service;

import model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UserService {

    @Inject
    private ViewService viewService;

    private List<User> users;
    public void setViewService(ViewService viewService) {
        this.viewService = viewService;
    }

    public List<User> getUsers() {
        if (users == null) {
            users = viewService.getAllUsers();
        }
        return users;
    }

    public String removeUser(User user) {
        viewService.removeUser(user);
        return "usersList?faces-redirect=true";
    }
}
