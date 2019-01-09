package info.pobu.blb.controllers.exceptions;

public class SpeciesNotFoundException extends Exception {

    /** serialization */
    private static final long serialVersionUID = 361316161116061121L;
    
    public SpeciesNotFoundException(String message) {
        super(message);
    }

}
