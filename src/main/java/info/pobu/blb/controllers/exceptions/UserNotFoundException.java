package info.pobu.blb.controllers.exceptions;

public class UserNotFoundException extends Exception {

    /** serialization */
    private static final long serialVersionUID = 3179096045752279985L;
    
    public UserNotFoundException(String message) {
        super(message);
    }
}
