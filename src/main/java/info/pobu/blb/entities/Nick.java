package info.pobu.blb.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import info.pobu.blb.entities.exceptions.NickIsTooLongException;
import info.pobu.blb.entities.exceptions.NickIsTooShortException;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Nick implements LiteralEntity {
	
	private static final int MIN_NICK_LENGTH = 6;
	private static final int MAX_NICK_LENGTH = 12;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Getter @Setter
	private String literal;
	
	@OneToOne(mappedBy="nick")
	private User user;

	public Nick(String nick) throws NickIsTooShortException, NickIsTooLongException {
		validate(nick);
		this.literal = nick;
	}

	private void validate(String str) throws NickIsTooShortException, NickIsTooLongException {
		if (str == null)
			throw new NickIsTooLongException("Nick can not be null");
		if ( str.length() > MAX_NICK_LENGTH )
			throw new NickIsTooLongException("Nick should contain not more then " + MAX_NICK_LENGTH + "characters");
		if ( str.length() < MIN_NICK_LENGTH )
			throw new NickIsTooShortException("Nick should contain at least " + MIN_NICK_LENGTH + "characters");
	}
}