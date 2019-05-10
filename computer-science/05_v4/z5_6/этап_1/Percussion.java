import java.io.Serializable;

public class Percussion extends Instrument implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Percussion( String name ) {
		super( name, Instrument.Type.PERCUSSION);
	}
	public Percussion() {}
}
