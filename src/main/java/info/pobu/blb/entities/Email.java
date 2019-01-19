package info.pobu.blb.entities;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import info.pobu.blb.entities.exceptions.EmailValidationFailedException;
import lombok.Getter;

@Entity
@Table(name = "email")
public class Email implements LiteralEntity {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(".+@.+\\..+");

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Getter
	private String literal;
	
	@OneToOne(mappedBy = "email")
	private User user;

	public Email(String email) throws EmailValidationFailedException {
		validate(email);
		this.literal = email;
	}
	
	public Email() {}

	private void validate(String email) throws EmailValidationFailedException {
		if (email == null) throw new EmailValidationFailedException("Email can not be null");
		if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches())
			throw new EmailValidationFailedException(email + "does not matches " + VALID_EMAIL_ADDRESS_REGEX + " pattern.");
	}
}
