package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = Author.Queries.FIND_ALL, query = "select a from Author a")
})
public class Author implements Serializable {
    public static class Queries {
        public static final String FIND_ALL = "AUTHOR_FIND_ALL";
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String surname;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
