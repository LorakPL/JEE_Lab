package pl.gda.pg.eti.kask.javaee.jsf.business.entities.permissions;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@NamedQuery(name = RolePermissions.Queries.ROLE_PERMISSIONS_FIND_BY_ROLE, query = "SELECT rp FROM RolePermissions rp WHERE rp.role = :role")
public class RolePermissions {

    public static class Queries {
        public static final String ROLE_PERMISSIONS_FIND_BY_ROLE = "RolePermissions.findByRole";
    }

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty
    private String role;

    @Valid
    @NotNull
    private CrudPermissions computerSetsPermissions;

    @Valid
    @NotNull
    private CrudPermissions partsPermissions;

    @Valid
    @NotNull
    private CrudPermissions usersPermissions;

    public RolePermissions(@NotEmpty String role, @Valid @NotNull CrudPermissions computerSetsPermissions, @Valid @NotNull CrudPermissions partsPermissions, @Valid @NotNull CrudPermissions usersPermissions) {
        this.role = role;
        this.computerSetsPermissions = computerSetsPermissions;
        this.partsPermissions = partsPermissions;
        this.usersPermissions = usersPermissions;
    }
}
