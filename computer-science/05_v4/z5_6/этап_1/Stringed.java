import java.io.Serializable;

public class Stringed extends Instrument implements Serializable {

	private static final long serialVersionUID = 1L;

	public Stringed( String name ) {
		super( name, Instrument.Type.STRINGED);
	}
	
	public Stringed() {}
}
