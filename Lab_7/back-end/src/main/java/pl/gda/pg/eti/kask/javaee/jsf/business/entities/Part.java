package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.Utils.Consts;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = Consts.FIND_ALL_PARTS, query = "select p from Part p"),
        @NamedQuery(name = Consts.FIND_PART_BY_TYPE, query = "select p from Part p where p.type =: partType"),
        @NamedQuery(name = Consts.FIND_PART_BY_NAME, query = "select p from Part p where p.name =: name")
})
public class Part implements Serializable {
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
