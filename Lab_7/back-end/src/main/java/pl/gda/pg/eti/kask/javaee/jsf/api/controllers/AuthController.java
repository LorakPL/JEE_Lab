package pl.gda.pg.eti.kask.javaee.jsf.api.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.UserService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.UserPass;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.ZonedDateTime.now;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static pl.gda.pg.eti.kask.javaee.jsf.api.CryptUtils.sha256;

@RequestScoped
@Path("")
public class AuthController {

    public static final String SIGNING_KEY = "czNjcjN0";

    @Inject
    UserService userService;

    @POST
    @Path("/token")
    public Response issueToken(UserPass userPass, @Context HttpServletRequest request) {
        try {
            request.login(userPass.getUsername(), userPass.getPassword());
        } catch (ServletException e) {
            throw new NotAuthorizedException(e, "Form");
        }

        User user = userService.findCurrentUser();
        String token = buildJWT(user);

        return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
    }

    @POST
    @Path("/new")
    public Response changePassword(UserPass userPass, @Context HttpServletRequest request) {
        try {
            userService.changeUserPassword(userPass);
        } catch (Exception e) {
            throw new NotAuthorizedException(e, "Form");
        }

        return Response.ok().header(AUTHORIZATION, "Sukces").build();
    }


    @POST
    @Path("/register")
    public Response register(UserPass userPass, @Context HttpServletRequest request) {
        User user = null;

        try {
        } catch (Exception e) { }

        if(user != null) {
            return Response.status(Response.Status.CONFLICT).build();
        } else {
            List<String> list = new ArrayList<>();
            list.add("USER");

            userService.registerUser(new User(userPass.getUsername(), sha256(userPass.getPassword()), userPass.getUsername(), userPass.getUsername(), list));
        }
        return Response.ok().build();
    }

    private String buildJWT(User user) {
        Date issuedAt = new Date();
        Date expirationAt = Date.from(now().plusMinutes(15).toInstant());

        String jwtToken = Jwts.builder()
                .setSubject(user.getLogin())
                .setIssuer("Java EE Example Enterprise App")
                .setIssuedAt(issuedAt)
                .setExpiration(expirationAt)
                .claim("roles", user.getRoles())
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();

        return jwtToken;
    }
}
