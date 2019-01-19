package info.pobu.blb.entities.exceptions;

public class NickValidationFailedException extends Exception {

	/** serialization*/
	private static final long serialVersionUID = 6673546450558023713L;
	
	public NickValidationFailedException(String message) {
		super(message);
	}
}