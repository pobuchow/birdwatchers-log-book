package info.pobu.blb.controllers.exceptions;

public class ObservationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -536904567973869619L;
    
    public ObservationNotFoundException(String message) {
        
        super(message);
    }

}
