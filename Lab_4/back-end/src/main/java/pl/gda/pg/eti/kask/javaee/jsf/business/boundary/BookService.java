package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Author;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;

@ApplicationScoped
public class BookService implements Serializable {

    @PersistenceContext
    EntityManager em;

    public Collection<Book> findAllBooks() {
        TypedQuery<Book> query = em.createNamedQuery(Book.Queries.FIND_ALL, Book.class);
        return query.getResultList();
    }

    public Book findBook(int id) {
        return em.find(Book.class, id);
    }

    @Transactional
    public void removeBook(Book book) {
        book = em.merge(book);
        em.remove(book);
    }

    @Transactional
    public Book saveBook(Book book) {
        if (book.getId() == null) {
            em.persist(book);
        } else {
            book = em.merge(book);
        }

        return book;
    }

    public Collection<Author> findAllAuthors() {
        TypedQuery<Author> query = em.createNamedQuery(Author.Queries.FIND_ALL, Author.class);
        return query.getResultList();
    }

    public Author findAuthor(int id) {
        return em.find(Author.class, id);
    }

}
