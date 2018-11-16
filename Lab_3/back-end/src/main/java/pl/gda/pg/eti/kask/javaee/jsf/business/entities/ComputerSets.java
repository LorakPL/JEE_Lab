package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComputerSets {
    Collection<ComputerSet> computerSets;
    List<Link> links = new ArrayList<>();
}
