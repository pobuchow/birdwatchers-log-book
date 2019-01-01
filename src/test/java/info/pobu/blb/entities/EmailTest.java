package info.pobu.blb.entities;

import org.junit.Assert;
import org.junit.Test;

import info.pobu.blb.entities.exceptions.NotValidEmailException;

public class EmailTest {
	
	public static final String VALID_MAIL = "xyz@qwe.abc";
	
	public static final String INVALID_MAIL_WITHOUT_AT = "xyzqwe.abc";
	
	public static final String INVALID_MAIL_WITHOUT_DOMAIN = "xyz@";
	
	public static final String INVALID_MAIL_WITHOUT_ADDRESS = "@qwe.abc";
	
	@Test
	public void shouldCreateValidEmail() {
		Email email = null;
		try {
			email = new Email (VALID_MAIL);
		} catch (NotValidEmailException e) {
			Assert.fail();
		}
		Assert.assertEquals("Email: " + VALID_MAIL + " should be correct created", VALID_MAIL, email.getLiteral());
	}
	
	@Test(expected = NotValidEmailException.class)
	public void shouldThrowExceptionWhenMailHasNoAt() throws NotValidEmailException {
		new Email (INVALID_MAIL_WITHOUT_AT);
	}
	
	@Test(expected = NotValidEmailException.class)
	public void shouldThrowExceptionWhenMailHasNoDomain() throws NotValidEmailException {
		new Email (INVALID_MAIL_WITHOUT_DOMAIN);
	}
	
	@Test(expected = NotValidEmailException.class)
	public void shouldThrowExceptionWhenMailHasNoAddress() throws NotValidEmailException {
		new Email (INVALID_MAIL_WITHOUT_ADDRESS);
	}
	
	@Test(expected = NotValidEmailException.class)
	public void shouldThrowExceptionWhenMailIsNull() throws NotValidEmailException {
		new Email (null);
	}
}
