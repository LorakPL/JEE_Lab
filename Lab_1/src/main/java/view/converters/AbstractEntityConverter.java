package view.converters;

import service.ViewService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.function.BiFunction;
import java.util.function.Function;

abstract class AbstractEntityConverter<T> implements Converter<T> {

    @Inject
    ViewService viewService;

    private BiFunction<ViewService, Integer, T> retrieveFunction;

    private Function<T, Integer> idExtractor;

    AbstractEntityConverter(Function<T, Integer> idExtractor, BiFunction<ViewService, Integer, T> retrieveFunction) {
        this.retrieveFunction = retrieveFunction;
        this.idExtractor = idExtractor;
    }

    @Override
    public T getAsObject(FacesContext context, UIComponent component, String value) {
        T entity = retrieveFunction.apply(viewService, Integer.parseInt(value));

        if (entity == null) {
            context.getExternalContext().setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
            context.responseComplete();
        }

        return entity;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, T value) {
        Integer id = idExtractor.apply(value);
        return id != null ? id.toString() : null;
    }
}
