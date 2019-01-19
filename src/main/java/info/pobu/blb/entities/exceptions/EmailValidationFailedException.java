package info.pobu.blb.entities.exceptions;

public class EmailValidationFailedException extends Exception {

	private static final long serialVersionUID = -181504509543056086L;
	
	public EmailValidationFailedException(String message) {
		super(message);
	}
}
