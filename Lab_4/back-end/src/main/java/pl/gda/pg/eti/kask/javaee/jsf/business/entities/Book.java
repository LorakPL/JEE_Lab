package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbNillable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = Book.Queries.FIND_ALL, query = "select b from Book b")
})
public class Book implements Serializable {
    public static class Queries {
        public static final String FIND_ALL = "BOOK_FIND_ALL";
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 4, max = 250)
    @NotBlank
    private String title;

    @NotNull
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate publishDate;

    @Size(min = 1)
    @ManyToMany(cascade = {MERGE, REFRESH})
    private List<Author> authors = new ArrayList<>();

    public Book(String title, LocalDate publishDate, List<Author> authors) {
        this.title = title;
        this.publishDate = publishDate;
        this.authors = authors;
    }
}
