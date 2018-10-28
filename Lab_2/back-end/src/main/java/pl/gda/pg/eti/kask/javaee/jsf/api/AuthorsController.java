package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.BookService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Author;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Collection;

@Path("/authors")
public class AuthorsController {

    @Inject
    BookService bookService;

    @GET
    public Collection<Author> getAllAuthors() {
        return bookService.findAllAuthors();
    }

    @GET
    @Path("/{author}")
    public Author getAuthor(@PathParam("author") Author author) {
        return author;
    }
}
