package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.*;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Link {
    private String link;
    private String rel;
}
