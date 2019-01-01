package info.pobu.blb.controllers;

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
	private static final String  EMAIL_LITERAL = "xyz@qwe.abc";
	
	@Before
	public void mock() {
		Mockito.when(emailMock.getLiteral()).thenReturn(EMAIL_LITERAL);
		Mockito.when(nickMock.getLiteral()).thenReturn(NICK_LITERAL);
		Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(userMock);
		Mockito.when(userMock.getEmail()).thenReturn(emailMock);
		Mockito.when(userMock.getNick()).thenReturn(nickMock);
	}

	@Test
    public void contexLoads() throws Exception {
        Assert.assertNotNull(controller);
    }
	
	@Test
	public void shouldAddNewUser() throws UserValidationFailedException {
		//when
		User user = controller.addNewUser(NICK_LITERAL, EMAIL_LITERAL);
		//then
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
}
