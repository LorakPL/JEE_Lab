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
        @NamedQuery(name = Customer.Queries.FIND_ALL, query = "select c from Customer c"),
        @NamedQuery(name = Customer.Queries.FIND_BY_NAME, query = "select c from Customer c WHERE c.name =: name")
})
public class Customer implements Serializable {
    public static class Queries {
        public static final String FIND_ALL = "CUSTOMER_FIND_ALL";
        public static final String FIND_BY_NAME = "CUSTOMER_FIND_BY_NAME";
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

    public Customer(String userName, String name, String secondName) {
        this.userName = userName;
        this.name = name;
        this.secondName = secondName;
    }
}