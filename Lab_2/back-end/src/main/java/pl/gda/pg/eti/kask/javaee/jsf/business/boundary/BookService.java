package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Author;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.Arrays.asList;

@ApplicationScoped
public class BookService implements Serializable {

    private final SortedMap<Integer, Book> books = new TreeMap<>();

    private final SortedMap<Integer, Author> authors = new TreeMap<>();

    @PostConstruct
    public void init() {
        Author a1 = new Author(1, "Dmitrij", "Glukhovsky");
        Author a2 = new Author(2, "Maja", "Kossakowska");

        Book book1 = new Book(1, "Metro 2033", LocalDate.of(2010, 2, 24), asList(a1));
        Book book2 = new Book(2, "Grillbar Galaktyka", LocalDate.of(2011, 10, 22), asList(a2));

        books.put(book1.getId(), book1);
        books.put(book2.getId(), book2);

        authors.put(a1.getId(), a1);
        authors.put(a2.getId(), a2);
    }

    public Collection<Book> findAllBooks() {
        return books.values();
    }

    public Book findBook(int id) {
        return books.get(id);
    }

    public void removeBook(Book book) {
        books.remove(book.getId());
    }

    public void saveBook(Book book) {
        if (book.getId() == null) {
            book.setId(books.lastKey() + 1);
        }

        books.put(book.getId(), book);
    }

    public Collection<Author> findAllAuthors() {
        return authors.values();
    }

    public Author findAuthor(int id) {
        return authors.get(id);
    }

}
