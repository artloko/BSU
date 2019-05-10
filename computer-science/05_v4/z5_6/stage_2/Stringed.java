import java.io.Serializable;

@SuppressWarnings("serial")
public class Stringed extends Instrument  implements Serializable {

	public Stringed( String name ) {
		super( name, Instrument.Type.STRINGED);
	}
	
	public Stringed() {}
}
