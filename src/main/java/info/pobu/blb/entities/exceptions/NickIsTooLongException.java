package info.pobu.blb.entities.exceptions;

public class NickIsTooLongException extends Exception {

	/** serialization*/
	private static final long serialVersionUID = 6673546450558023713L;
	
	public NickIsTooLongException(String message) {
		super(message);
	}
}