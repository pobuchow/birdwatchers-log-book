package info.pobu.blb.entities;

import org.junit.Assert;
import org.junit.Test;

import info.pobu.blb.entities.exceptions.NickValidationFailedException;

public class NickTest {

	private static final String TOO_SHORT_NICK = "Foo";
	
	private static final String TOO_LONG_NICK = "T00_Long_Nick";
	
	private static final String CORRECT_NICK = "Mr. Correct";

	@Test(expected = NickValidationFailedException.class)
	public void shouldNickHaveAtLeast6Characters() throws NickValidationFailedException{
			new Nick(TOO_SHORT_NICK);
	}
	
	@Test(expected = NickValidationFailedException.class)
	public void shouldNickHaveMax12Characters() throws NickValidationFailedException{
			new Nick(TOO_LONG_NICK);
	}
	
	@Test
	public void shouldCreateNick() throws NickValidationFailedException {
			Nick n = new Nick(CORRECT_NICK);
			Assert.assertNotNull(n);
			Assert.assertEquals("Nick: " + CORRECT_NICK + " should be correct created", CORRECT_NICK, n.getLiteral());
	}
}
