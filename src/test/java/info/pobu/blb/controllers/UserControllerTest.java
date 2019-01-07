package info.pobu.blb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import info.pobu.blb.controllers.exceptions.UserValidationFailedException;
import info.pobu.blb.entities.Email;
import info.pobu.blb.entities.Nick;
import info.pobu.blb.entities.User;
import info.pobu.blb.repositories.IUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserController.class)
public class UserControllerTest {

    @MockBean
    private IUserRepository repository;

    @Mock
    private Email emailMock;

    @Mock
    private Nick nickMock;

    @Mock
    private User userMock;

    @Autowired
    private UserController controller;

    private static final String NICK_LITERAL = "Tester";
    private static final String EMAIL_LITERAL = "xyz@qwe.abc";

    public static final Nick USER_A_NICK;
    public static final Nick USER_B_NICK;
    public static final Nick USER_C_NICK;
    static {
        try {
            USER_A_NICK = new Nick("a_test");
            USER_B_NICK = new Nick("b_test");
            USER_C_NICK = new Nick("c_test");
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to create Nick instance in static block.", ex);
        }
    }

    public static final Email USER_A_EMAIL;
    public static final Email USER_B_EMAIL;
    public static final Email USER_C_EMAIL;
    static {
        try {
            USER_A_EMAIL = new Email("a@xyz.abc");
            USER_B_EMAIL = new Email("b@xyz.abc");
            USER_C_EMAIL = new Email("c@xyz.abc");
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to create Email instance in static block.", ex);
        }
    }

    static final List<User> USERS = new ArrayList<User>() {
        /** serialization */
        private static final long serialVersionUID = -4250775630610091530L;
        {
            add(new User(USER_A_NICK, USER_A_EMAIL));
            add(new User(USER_B_NICK, USER_B_EMAIL));
            add(new User(USER_C_NICK, USER_C_EMAIL));
        }
    };

    @Before
    public void mock() {
        Mockito.when(emailMock.getLiteral()).thenReturn(EMAIL_LITERAL);
        Mockito.when(nickMock.getLiteral()).thenReturn(NICK_LITERAL);
        Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(userMock);
        Mockito.when(repository.findAll()).thenReturn(USERS);
        Mockito.when(userMock.getEmail()).thenReturn(emailMock);
        Mockito.when(userMock.getNick()).thenReturn(nickMock);
    }

    @Test
    public void contexLoads() throws Exception {
        Assert.assertNotNull(controller);
    }

    @Test
    public void shouldAddNewUser() throws UserValidationFailedException {
        // when
        User user = controller.addNewUser(NICK_LITERAL, EMAIL_LITERAL);
        // then
        Assert.assertNotNull("Created user should be not null", user);
        Assert.assertEquals("Users nick: " + NICK_LITERAL, NICK_LITERAL, user.getNick().getLiteral());
        Assert.assertEquals("Users email: " + EMAIL_LITERAL, EMAIL_LITERAL, user.getEmail().getLiteral());
    }

    @Test(expected = UserValidationFailedException.class)
    public void shouldThrowExceptionWhenNickIsNull() throws UserValidationFailedException {
        controller.addNewUser(null, EMAIL_LITERAL);
    }

    @Test(expected = UserValidationFailedException.class)
    public void shouldThrowExceptionWhenMailIsNull() throws UserValidationFailedException {
        controller.addNewUser(NICK_LITERAL, null);
    }

    @Test
    public void shouldGetAllUsers() {
        List<User> users = new ArrayList<>();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.isEmpty());
        users = controller.getAllUsers();
        Assert.assertFalse(users.isEmpty());
        Assert.assertEquals(USERS.size(), users.size());
    }

    @Test
    public void shouldFindUserWithExistingNick() {
        String existingNick = USER_A_NICK.getLiteral();

        User user = controller.findByNickLiteral(existingNick);

        Assert.assertNotNull(user);
        Assert.assertEquals(existingNick, user.getNick().getLiteral());
    }

    @Test
    public void shouldNotFingUserWithNotExistingNick() {
        String notExistingNick = "d_test";

        User user = controller.findByNickLiteral(notExistingNick);

        Assert.assertNull(user);
    }
    
    @Test
    public void shouldFindUserWithExistingEmail() {
        String existingEmail = USER_A_EMAIL.getLiteral();

        User user = controller.findByEmailLiteral(existingEmail);

        Assert.assertNotNull(user);
        Assert.assertEquals(existingEmail, user.getEmail().getLiteral());
    }

    @Test
    public void shouldNotFingUserWithNotExistingEmail() {
        String notExistingEmail = "d@qwe.asd";

        User user = controller.findByNickLiteral(notExistingEmail);

        Assert.assertNull(user);
    }
}