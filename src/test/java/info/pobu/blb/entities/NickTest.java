package info.pobu.blb.entities;

import org.junit.Assert;
import org.junit.Test;

import info.pobu.blb.entities.exceptions.NickIsTooLongException;
import info.pobu.blb.entities.exceptions.NickIsTooShortException;

public class NickTest {

	private static final String TOO_SHORT_NICK = "Foo";
	
	private static final String TOO_LONG_NICK = "T00_Long_Nick";
	
	private static final String CORRECT_NICK = "Mr. Correct";

	@Test(expected = NickIsTooShortException.class)
	public void shouldNickHaveAtLeast6Characters() throws NickIsTooShortException {
		try {
			new Nick(TOO_SHORT_NICK);
		} catch (NickIsTooLongException e) {
			Assert.fail();
		}
	}
	
	@Test(expected = NickIsTooLongException.class)
	public void shouldNickHaveMax12Characters() throws NickIsTooLongException{
		try {
			new Nick(TOO_LONG_NICK);
		} catch (NickIsTooShortException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void shouldCreateNick() {
		try {
			Nick n = new Nick(CORRECT_NICK);
			Assert.assertEquals("Nick: " + CORRECT_NICK + "should be correct.", n.getLiteral(), CORRECT_NICK);
		} catch (NickIsTooShortException | NickIsTooLongException e) {
			Assert.fail();
		}
	}
}
