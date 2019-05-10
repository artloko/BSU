import java.io.Serializable;

public class Instrument implements Serializable {
	
	private String name;
	public String getName() {
		return name;
	}
	
	public enum Type { PERCUSSION, STRINGED, WIND };
	
	private Type type;
	public Type getType() {
		return type;
	}
	
	private String info = "";
	public String getInfo() {
		return info;
	}
	public void setInfo( String info ) {
		this.info = info;
	}
	
	public String toString() {
		return new String( "Instrument: " + name + 
				           " type: " + type.toString() +  
				           " info: "  + info );
	}
	
	public Instrument() {}
	
	protected Instrument( String name, Type type ) {
		this.name = name;
		this.type = type;
	}
}
