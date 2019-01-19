package info.pobu.blb.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import info.pobu.blb.entities.exceptions.NickValidationFailedException;
import lombok.Getter;

@Entity
@Table(name = "nick")
public class Nick implements LiteralEntity {
	
	private static final int MIN_NICK_LENGTH = 6;
	private static final int MAX_NICK_LENGTH = 12;
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@Getter 
	@Size(min = MIN_NICK_LENGTH, max = MAX_NICK_LENGTH)
	private String literal;
	
	@OneToOne(mappedBy = "nick")
	private User user;

	public Nick(String nick) throws  NickValidationFailedException {
		validate(nick);
		this.literal = nick;
	}
	
	public Nick() {}

	private void validate(String str) throws NickValidationFailedException {
		if (str == null)
			throw new NickValidationFailedException("Nick can not be null");
		if ( str.length() > MAX_NICK_LENGTH )
			throw new NickValidationFailedException("Nick should contain not more then " + MAX_NICK_LENGTH + "characters");
		if ( str.length() < MIN_NICK_LENGTH )
			throw new NickValidationFailedException("Nick should contain at least " + MIN_NICK_LENGTH + "characters");
	}
}