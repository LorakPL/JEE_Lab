package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = ComputerSet.Queries.FIND_ALL, query = "select c from ComputerSet c"),
        @NamedQuery(name = ComputerSet.Queries.FIND_ALL_BY_USER_ID, query = "select c from ComputerSet c where c.customer.id =: id")
})
public class ComputerSet implements Serializable {
    public static class Queries {
        public static final String FIND_ALL = "COMPUTERSET_FIND_ALL";
        public static final String FIND_ALL_BY_USER_ID = "COMPUTERSET_FIND_ALL_BY_USER_ID";
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Valid
    @ManyToOne(cascade = {MERGE, REFRESH})
    private Customer customer;

    @Valid
    @Size(min = 8, max = 8)
    @ManyToMany(cascade = {MERGE, REFRESH})
    List<Part> parts = new ArrayList<>();

    public ComputerSet (Customer customer, List<Part> parts) {
        this.customer = customer;
        this.parts = parts;
    }
}
