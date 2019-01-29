import java.io.Serializable;

@SuppressWarnings("serial")
public class Percussion extends Instrument implements Serializable {
	
	public Percussion( String name ) {
		super( name, Instrument.Type.PERCUSSION);
	}
	public Percussion() {}
}
