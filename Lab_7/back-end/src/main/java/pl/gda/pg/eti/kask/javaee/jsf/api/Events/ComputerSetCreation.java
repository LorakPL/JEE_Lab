package pl.gda.pg.eti.kask.javaee.jsf.api.Events;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ComputerSetCreation {
    Annotation Literal = new AnnotationLiteral<ComputerSetCreation>() { };
}
