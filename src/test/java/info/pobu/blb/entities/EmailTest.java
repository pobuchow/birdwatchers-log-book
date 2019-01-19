package info.pobu.blb.entities;

import org.junit.Assert;
import org.junit.Test;

import info.pobu.blb.entities.exceptions.EmailValidationFailedException;

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
		} catch (EmailValidationFailedException e) {
			Assert.fail();
		}
		Assert.assertEquals("Email: " + VALID_MAIL + " should be correct created", VALID_MAIL, email.getLiteral());
	}
	
	@Test(expected = EmailValidationFailedException.class)
	public void shouldThrowExceptionWhenMailHasNoAt() throws EmailValidationFailedException {
		new Email (INVALID_MAIL_WITHOUT_AT);
	}
	
	@Test(expected = EmailValidationFailedException.class)
	public void shouldThrowExceptionWhenMailHasNoDomain() throws EmailValidationFailedException {
		new Email (INVALID_MAIL_WITHOUT_DOMAIN);
	}
	
	@Test(expected = EmailValidationFailedException.class)
	public void shouldThrowExceptionWhenMailHasNoAddress() throws EmailValidationFailedException {
		new Email (INVALID_MAIL_WITHOUT_ADDRESS);
	}
	
	@Test(expected = EmailValidationFailedException.class)
	public void shouldThrowExceptionWhenMailIsNull() throws EmailValidationFailedException {
		new Email (null);
	}
}
