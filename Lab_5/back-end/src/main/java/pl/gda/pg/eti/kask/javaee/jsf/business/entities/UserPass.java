package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPass {
    private String username;
    private String password;
}
