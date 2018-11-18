package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.BookService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Book;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/books")
public class BooksController {

    @Inject
    BookService bookService;

    @GET
    public Collection<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @POST
    public Response saveBook(@Valid Book book) {
        bookService.saveBook(book);
        return created(uri(BooksController.class, "getBook", book.getId())).build();
    }

    @GET
    @Path("/{book}")
    public Book getBook(@PathParam("book") Book book) {
        return book;
    }

    @DELETE
    @Path("/{book}")
    public Response deleteBook(@PathParam("book") Book book) {
        bookService.removeBook(book);
        return noContent().build();
    }

    @PUT
    @Path("/{book}")
    public Response updateBook(@PathParam("book") Book originalBook, @Valid Book updatedBook) {
        if (!originalBook.getId().equals(updatedBook.getId())) {
            return status(Status.BAD_REQUEST).build();
        }

        bookService.saveBook(updatedBook);
        return ok().build();
    }
}
