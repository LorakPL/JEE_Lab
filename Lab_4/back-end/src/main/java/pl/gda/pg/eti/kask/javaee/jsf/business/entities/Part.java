package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = Part.Queries.FIND_ALL, query = "select p from Part p"),
        @NamedQuery(name = Part.Queries.FIND_BY_TYPE, query = "select p from Part p where p.type =: partType")
})
public class Part implements Serializable {
    public static class Queries {
        public static final String FIND_ALL = "PART_FIND_ALL";
        public static final String FIND_BY_TYPE = "PART_BY_TYPE";
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, max = 100)
    @NotNull
    private String name;

    @Pattern(regexp = "\\d+")
    @NotNull
    private String price;

    @NotNull
    private PartType type;

    public Part(String name, String price, PartType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
}
