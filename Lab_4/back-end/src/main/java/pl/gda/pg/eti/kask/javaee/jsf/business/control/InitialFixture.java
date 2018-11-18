package pl.gda.pg.eti.kask.javaee.jsf.business.control;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Author;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Book;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Calendar;

import static java.util.Arrays.asList;
import static java.util.Calendar.FEBRUARY;
import static java.util.Calendar.OCTOBER;

@ApplicationScoped
public class InitialFixture {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {

        User user1 = new User("jan.nowak@gmail.com", "Jan", "Nowak");
        User user2 = new User("adam.kowalski@gmail.com", "Adam", "Kowalski");
        User user3 = new User("piotr.zielinski@gmail.com", "Piotr", "Zieliński");

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);

        /*
        Author a1 = new Author("Dmitrij", "Glukhovsky");
        Author a2 = new Author("Maja", "Kossakowska");

        Calendar cal = Calendar.getInstance();
        cal.set(2010, FEBRUARY, 24);
        Book book1 = new Book("Metro 2033", LocalDate.now(), asList(a1));

        cal.set(2011, OCTOBER, 22);
        Book book2 = new Book("Grillbar Galaktyka", LocalDate.now(), asList(a2));

        em.persist(a1);
        em.persist(a2);
        em.persist(book1);
        em.persist(book2);
        */
    }
}
