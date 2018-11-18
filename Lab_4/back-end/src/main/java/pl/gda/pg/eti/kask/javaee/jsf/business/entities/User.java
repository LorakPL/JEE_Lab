package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
