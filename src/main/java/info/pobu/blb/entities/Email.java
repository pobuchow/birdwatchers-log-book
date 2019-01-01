package info.pobu.blb.entities;

import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import info.pobu.blb.entities.exceptions.NotValidEmailException;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Email implements LiteralEntity {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(".+@.+\\..+");

	@Id
	@GeneratedValue
	private int id;
	
	@Getter @Setter
	private String literal;
	
	@OneToOne(mappedBy="email")
	private User user;

	public Email(String email) throws NotValidEmailException {
		validate(email);
		this.literal = email;
	}
	
	public Email() {}

	private void validate(String email) throws NotValidEmailException {
		if (email == null) throw new NotValidEmailException("Email can not be null");
		if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches())
			throw new NotValidEmailException(email + "does not matches " + VALID_EMAIL_ADDRESS_REGEX + " pattern.");
	}
}
