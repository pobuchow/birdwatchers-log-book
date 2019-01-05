package info.pobu.blb.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import info.pobu.blb.entities.exceptions.NickIsTooLongException;
import info.pobu.blb.entities.exceptions.NickIsTooShortException;
import info.pobu.blb.entities.exceptions.NotValidEmailException;

public class UserTest {

	private final static String EMAIL_ADDRESS = "test@mail.xyz";
	private final static String NEW_EMAIL_ADDRESS = "new@mail.xyz";
	private final static String NICK_ADDRESS = "test_Nick";
	private final static String NEW_NICK_ADDRESS = "new_Nick";
	
	@Mock
	Nick testNick;
	
	@Mock
	Email testEmail;
	
	@Mock
	Nick newNick;
	
	@Mock
	Email newEmail;
		
	@Before
	public void prepareMocks() {
		try {
			testEmail = new Email(EMAIL_ADDRESS);
			newEmail = new Email(NEW_EMAIL_ADDRESS);
		} catch (NotValidEmailException e) {
			Assert.fail("error while creating Email Mock");
			e.printStackTrace();
		}
		try {
			testNick = new Nick(NICK_ADDRESS);
			newNick = new Nick(NEW_NICK_ADDRESS);
		} catch (NickIsTooShortException | NickIsTooLongException e) {
			Assert.fail("error while creating Nick Mock");
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldCreateUserWithEmail() {
		User user = new User(testNick, testEmail);
		Assert.assertNotNull(user.getEmail());
		Assert.assertEquals(testEmail, user.getEmail());
	}
	
	@Test
	public void shouldCreateUserWithNick() {
		User user = new User(testNick, testEmail);
		Assert.assertNotNull(user.getNick());
		Assert.assertEquals(testNick, user.getNick());
	}
	
	@Test
	public void shouldSetNewUsersNick() {
		User user = new User(testNick, testEmail);
		Assert.assertNotNull(user.getNick());
		Assert.assertEquals(testNick, user.getNick());
		user.setNick(newNick);
		Assert.assertNotNull(user.getNick());
		Assert.assertEquals(newNick, user.getNick());
	}
	
	@Test
	public void shouldSetNewUsersMail() {
		User user = new User(testNick, testEmail);
		Assert.assertNotNull(user.getEmail());
		Assert.assertEquals(testEmail, user.getEmail());
		user.setEmail(newEmail);
		Assert.assertNotNull(user.getEmail());
		Assert.assertEquals(newEmail, user.getEmail());
	}
}