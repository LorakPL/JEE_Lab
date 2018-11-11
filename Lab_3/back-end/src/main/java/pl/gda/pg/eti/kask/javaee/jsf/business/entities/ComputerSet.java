package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComputerSet implements Serializable {
    private Integer id;
    private User user;
    List<Part> parts;
}
