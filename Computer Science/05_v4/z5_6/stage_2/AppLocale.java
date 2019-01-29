import java.util.*;

public class AppLocale {
	private static final String strMsg = "Msg";
	private static Locale loc = Locale.getDefault();
	private static ResourceBundle res = 
			ResourceBundle.getBundle( AppLocale.strMsg, AppLocale.loc );
	
	static Locale get() {
		return AppLocale.loc;
	}
	
	static void set( Locale loc ) {
		AppLocale.loc = loc;
		res = ResourceBundle.getBundle( AppLocale.strMsg, AppLocale.loc );
	}
	
	static ResourceBundle getBundle() {
		return AppLocale.res;
	}
	
	static String getString( String key ) {
		return AppLocale.res.getString(key);
	}
	
	// Resource keys:
	
	public static final String instrument="instrument";
	public static final String type="type";
	public static final String info="info";
	public static final String creation="creation";
	public static final String drum="drum";
	public static final String small_drum="small_drum";
	public static final String guitar="guitar";
	public static final String solo_guitar="solo_guitar";
	public static final String rhythm_guitar="rhythm_guitar";
	public static final String bass="bass";
	public static final String double_bass="double_bass";
	public static final String sax="sax";
	public static final String tenor_sax="tenor_sax";
	public static final String the_band="the_band";
}
