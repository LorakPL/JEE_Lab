package view;

import model.User;
import service.ViewService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class EditUser implements Serializable {

    @Inject
    private ViewService viewService;

    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String saveUser() {
        viewService.saveUser(user);
        return "usersList?faces-redirect=true";
    }
}
