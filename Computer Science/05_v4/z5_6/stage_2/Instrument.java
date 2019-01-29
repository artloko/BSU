import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class Instrument implements Serializable {

	public final Date creationDate = new Date();
	public String getCreationDate() {
		DateFormat dateFormatter = DateFormat.getDateTimeInstance(
				DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
	    String dateOut = dateFormatter.format(creationDate);
		return dateOut;
	}
	
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
		return new String( AppLocale.getString( AppLocale.instrument ) + ": " + name + " " +
				           AppLocale.getString( AppLocale.type ) + ": " + type.toString() + " " + 
				           AppLocale.getString( AppLocale.info ) + ": "  + info + " " +
				           AppLocale.getString( AppLocale.creation ) + ": " + getCreationDate() );
	}
	
	public Instrument() {}
	
	protected Instrument( String name, Type type ) {
		this.name = name;
		this.type = type;
	}
}
