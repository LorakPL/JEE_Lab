package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.gda.pg.eti.kask.javaee.jsf.business.Utils.Consts;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = Consts.FIND_ALL_USERS, query = "SELECT u FROM User u"),
        @NamedQuery(name = Consts.FIND_USER_BY_LOGIN, query = "SELECT u FROM User u WHERE u.login = :login")

})
public class User implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Column(nullable = false, unique = true)
    private String login;

    @Getter
    @Setter
    @JsonIgnore
    private String password;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String secondName;

    @Getter
    @Setter
    @ElementCollection
    private List<String> roles;

    public User(String login, String password, String name, String secondName, List<String> roles) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.secondName = secondName;
        this.roles = roles;
    }
}