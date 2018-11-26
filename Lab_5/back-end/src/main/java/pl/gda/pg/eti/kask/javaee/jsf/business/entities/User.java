package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = User.Queries.FIND_ALL, query = "SELECT u FROM User u"),
        @NamedQuery(name = User.Queries.FIND_BY_LOGIN, query = "SELECT u FROM User u WHERE u.login = :login")

})
public class User implements Serializable {

    public static class Queries {
        public static final String FIND_ALL = "User.findAll";
        public static final String FIND_BY_LOGIN = "User.findByLogin";
    }

    public static class Roles {
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }

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
    @ElementCollection
    private List<String> roles;

    public User(String login, String password, List<String> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }
}

/*
@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = User.Queries.FIND_ALL, query = "select u from User u"),
        @NamedQuery(name = User.Queries.FIND_BY_NAME, query = "select u from User u WHERE u.name =: name")
})
public class User implements Serializable {
    public static class Queries {
        public static final String FIND_ALL = "USER_FIND_ALL";
        public static final String FIND_BY_NAME = "USER_FIND_BY_NAME";
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, max = 50)
    @NotNull
    private String userName;

    @Size(min = 2, max = 50)
    @NotBlank
    private String name;

    @Size(min = 2, max = 50)
    @NotBlank
    private String secondName;

    public User(String userName, String name, String secondName) {
        this.userName = userName;
        this.name = name;
        this.secondName = secondName;
    }
}
*/