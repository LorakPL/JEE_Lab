package pl.gda.pg.eti.kask.javaee.jsf.api.Events;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;

//@AllArgsConstructor(staticName = "of")
public class ComputerSetEvent {

    @Getter
    @Setter
    private ComputerSet computerSet;

    public ComputerSetEvent(ComputerSet computerSet) {
        this.computerSet = computerSet;
    }
}
