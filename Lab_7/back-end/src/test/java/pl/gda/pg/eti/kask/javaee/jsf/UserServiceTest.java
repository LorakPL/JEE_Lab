package pl.gda.pg.eti.kask.javaee.jsf;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ComputerSetService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.UserService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.User;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class UserServiceTest extends JPAHibernateTest {
    @Mock
    private ComputerSetService computerSetService;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(computerSetService.findAllComputerSetsByUserId(Mockito.anyInt())).thenReturn(new ArrayList<>());

        userService.setEm(entityManager);
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = userService.findAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testFindUserById() {
        User user = userService.findUserById(1);

        assertNotNull(user);
        assertEquals(1, user.getId().intValue());
    }

    @Test
    public void testDeleteUser() {
        User user = userService.findUserById(1);
        assertNotNull(user);
        userService.removeUser(user);
        user = userService.findUserById(1);
        assertNull(user);
    }

    @Test
    public void testUpdateUser() {
        User user = userService.findUserById(2);
        user.setName("aaaaaa");
        userService.saveUser(user);

        User newUser = userService.findUserById(2);

        assertNotNull(newUser);
        assertEquals(user.getName(), newUser.getName());
    }
}
