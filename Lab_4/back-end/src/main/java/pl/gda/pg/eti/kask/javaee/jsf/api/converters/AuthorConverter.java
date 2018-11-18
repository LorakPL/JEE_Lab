package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.BookService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Author;

import javax.ws.rs.ext.Provider;

@Provider
public class AuthorConverter extends AbstractEntityConverter2<Author> {

    public AuthorConverter() {
        super(Author.class, Author::getId, BookService::findAuthor);
    }
}
