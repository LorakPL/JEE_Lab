package pl.gda.pg.eti.kask.javaee.jsf.business.entities.permissions;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class CrudPermissions implements Serializable {
    @NotNull
    private boolean allObjects;

    @NotNull
    private boolean create;

    @NotNull
    private boolean read;

    @NotNull
    private boolean update;

    @NotNull
    private boolean delete;
}
