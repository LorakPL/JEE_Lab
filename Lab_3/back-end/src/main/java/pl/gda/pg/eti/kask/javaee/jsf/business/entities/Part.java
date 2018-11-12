package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Part {
    private Integer id;
    private String name;
    private String price;
    private PartType type;
    private List<Link> links;

}
