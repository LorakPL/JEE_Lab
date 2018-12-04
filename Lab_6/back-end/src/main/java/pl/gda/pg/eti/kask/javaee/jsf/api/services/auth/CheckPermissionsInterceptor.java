package pl.gda.pg.eti.kask.javaee.jsf.api.services.auth;

import pl.gda.pg.eti.kask.javaee.jsf.api.ComputerSetsController;
import pl.gda.pg.eti.kask.javaee.jsf.api.CryptUtils;
import pl.gda.pg.eti.kask.javaee.jsf.api.PartsController;
import pl.gda.pg.eti.kask.javaee.jsf.api.UsersController;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ComputerSet;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.permissions.CrudPermissions;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.permissions.RolePermissions;

import javax.annotation.Priority;
import javax.ejb.EJBAccessException;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;

@Interceptor
@CheckPermissions
@Priority(1000)
public class CheckPermissionsInterceptor implements Serializable {
    @Inject
    HttpServletRequest request;

    @PersistenceContext
    EntityManager em;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        Object parameter = context.getParameters().length != 0 ? context.getParameters()[0] : null;

        ComputerSet computerSet = null;

        if(parameter instanceof ComputerSet) {
            computerSet = (ComputerSet) parameter;
        }

        User user = resolveUser();
        RolePermissions rolePermissions = null;

        if(isAdmin(user)) {
            rolePermissions = getRolePermissions(User.Roles.ADMIN);
            //rolePermissions = getRolePersmissions(ApplicationProperties.ROLE_ADMIN);
        } else if(isUser(user)) {
            rolePermissions = getRolePermissions(User.Roles.USER);
            //rolePermissions = getRolePersmissions(ApplicationProperties.ROLE_USER);
        }

        boolean tmp = hasPermissionForComputerSet(computerSet, rolePermissions, context, user);
        boolean tmp2 = hasGeneralPermission(rolePermissions, context);

        if(computerSet != null) {
            if(tmp) {
                return context.proceed();
            }
        } else {
            if(tmp || tmp2) {
                return context.proceed();
            }
        }


        throw new EJBAccessException("User not authorized for this invocation");
    }

    private boolean isAdmin(User user) {
        //return user.getRoles().contains(ApplicationProperties.ROLE_ADMIN)
        return user.getRoles().contains(User.Roles.ADMIN);
    }

    private boolean isUser(User user) {
        //return user.getRoles().contains(ApplicationProperties.ROLE_USER)
        return user.getRoles().contains(User.Roles.USER);

    }

    private boolean isOwner(User user, ComputerSet computerSet) {
        if(computerSet == null) {
            return false;
        }

        boolean tmp = computerSet.getUser().getLogin().equals(user.getLogin());

        return tmp;
    }

    private RolePermissions getRolePermissions(String role) {
        //TypedQuery<RolePermissions> query = em.createNamedQuery(ApplicationProperties.ROLE_PERMISSIONS_FIND_BY_ROLE, RolePermissions.class);
        //query.setParameter("role", role);
        //return query.getSingleResult();
        TypedQuery<RolePermissions> query = em.createNamedQuery(RolePermissions.Queries.ROLE_PERMISSIONS_FIND_BY_ROLE, RolePermissions.class);
        query.setParameter("role", role);
        return query.getSingleResult();
    }

    private User resolveUser() {
        String auth = request.getHeader("authorization").replace("Bearer ", "").split("[.]")[1];
        String login = CryptUtils.decodeBase64(auth).substring(8).split("\"")[0];

        TypedQuery<User> query = em.createNamedQuery(User.Queries.FIND_BY_LOGIN, User.class);
        query.setParameter("login", login);

        return query.getSingleResult();
    }

    private boolean hasPermissionForComputerSet(ComputerSet computerSet, RolePermissions rolePermissions, InvocationContext context, User user) {
        boolean tmp = checkIfAllowed(rolePermissions, context);
        boolean tmp2 = (isAdmin(user));
        boolean tmp3 = isOwner(user, computerSet);
        return computerSet != null && rolePermissions != null && tmp && (tmp2 || tmp3);
    }

    private boolean hasGeneralPermission(RolePermissions rolePermissions, InvocationContext context) {
        return rolePermissions != null && checkIfAllowed(rolePermissions, context);
    }

    private boolean checkIfAllowed(RolePermissions rolePermissions, InvocationContext context) {
        Class contextClass = context.getMethod().getDeclaringClass();
        Method method = context.getMethod();

        if(contextClass.equals(UsersController.class)) {
            return checkPermission(rolePermissions.getUsersPermissions(), method);
        } else if(contextClass.equals(PartsController.class)) {
            return checkPermission(rolePermissions.getPartsPermissions(), method);
        } else if(contextClass.equals(ComputerSetsController.class)) {
            return checkPermission(rolePermissions.getComputerSetsPermissions(), method);
        }
        return false;
    }

    private boolean checkPermission(CrudPermissions permissions, Method method) {
        if(method.getAnnotation(javax.ws.rs.POST.class) != null) {
            return permissions.isCreate();
        } else if(method.getAnnotation(javax.ws.rs.GET.class) != null) {
            return permissions.isRead();
        } else if(method.getAnnotation(javax.ws.rs.PUT.class) != null) {
            return permissions.isUpdate();
        } else if(method.getAnnotation(javax.ws.rs.DELETE.class) != null) {
            return permissions.isDelete();
        }
        return false;
    }
}
