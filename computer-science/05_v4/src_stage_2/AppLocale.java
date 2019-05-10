import java.util.Locale;
import java.util.ResourceBundle;

public class AppLocale {
    private static final String strMsg = "Msg";
    private static Locale locale = Locale.getDefault();
    private static ResourceBundle bundle = ResourceBundle.getBundle(AppLocale.strMsg, AppLocale.locale);

    public static Locale getLocale(){
        return AppLocale.locale;
    }

    public static void setLocale(Locale locale) {
        AppLocale.locale = locale;
        bundle = ResourceBundle.getBundle(AppLocale.strMsg, AppLocale.locale);
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }

    public static String getString(String key){
        return AppLocale.bundle.getString(key);
    }

    public static final String group = "group";
    public static final String type = "type";
    public static final String name = "name";
    public static final String info = "info";
    public static final String degree = "degree";
    public static final String creation = "creation";
    public static final String marks = "marks";
    public static final String year = "year";

}
