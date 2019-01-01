package info.pobu.blb.entities.exceptions;

public class NotValidEmailException extends Exception {

	private static final long serialVersionUID = -181504509543056086L;
	
	public NotValidEmailException(String message) {
		super(message);
	}
}
