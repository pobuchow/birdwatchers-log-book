package info.pobu.blb.controllers.exceptions;

public class UserValidationFailedException extends Exception {

	private static final long serialVersionUID = 1911994657285537146L;

	public UserValidationFailedException(String message) {
		super(message);
	}
}
