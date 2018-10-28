package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.BookService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Book;

import javax.ws.rs.ext.Provider;

@Provider
public class BookConverter extends AbstractEntityConverter2<Book> {

    public BookConverter() {
        super(Book.class, Book::getId, BookService::findBook);
    }
}
