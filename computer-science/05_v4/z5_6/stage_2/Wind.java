import java.io.Serializable;

@SuppressWarnings("serial")
public class Wind extends Instrument implements Serializable {
	
	public Wind( String name ) {
		super( name, Instrument.Type.WIND);
	}
	
	public Wind() {}
}
