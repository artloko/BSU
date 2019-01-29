import java.io.Serializable;

public class Wind extends Instrument implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Wind( String name ) {
		super( name, Instrument.Type.WIND);
	}
	
	public Wind() {}
}
