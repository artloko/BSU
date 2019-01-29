package account;

public class ArgException extends Exception {
    private static final long serialVersionUID = 1L;

    ArgException( String arg ) {
        super( "Invalid argument: " + arg );
    }
}