package info.pobu.blb.entities.exceptions;

public class NickIsTooShortException extends Exception {

	/** serialization*/
	private static final long serialVersionUID = -5855834345889052319L;

	public NickIsTooShortException(String message) {
		super(message);
	}

}
