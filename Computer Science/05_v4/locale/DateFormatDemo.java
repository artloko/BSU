
import java.util.*;
import java.text.*;

public class DateFormatDemo {

   static public void displayDate(Locale currentLocale) {

      Date today;
      String dateOut;
      DateFormat dateFormatter;

      dateFormatter = 
         DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);
      today = new Date();
      dateOut = dateFormatter.format(today);

      System.out.println(dateOut + "   " + currentLocale.toString());
   }

   static int[] styles = {
         DateFormat.DEFAULT,
         DateFormat.SHORT, 
         DateFormat.MEDIUM,
         DateFormat.LONG,
         DateFormat.FULL
      };

   static public void showBothStyles(Locale currentLocale) {

      Date today;
      String result;
      DateFormat formatter;

      System.out.println();
      System.out.println("Locale: " + currentLocale.toString());
      System.out.println();

      today = new Date();

      for (int k = 0; k < styles.length; k++) {
         formatter = DateFormat.getDateTimeInstance(
                     styles[k], styles[k], currentLocale);
         result = formatter.format(today);
         System.out.println(result);
      }
   }

   static public void showDateStyles(Locale currentLocale) {

      Date today = new Date();
      String result;
      DateFormat formatter;

      System.out.println();
      System.out.println("Locale: " + currentLocale.toString());
      System.out.println();

      for (int k = 0; k < styles.length; k++) {
         formatter = 
            DateFormat.getDateInstance(styles[k], currentLocale);
         result = formatter.format(today);
         System.out.println(result);
      }
   }

   static public void showTimeStyles(Locale currentLocale) {

      Date today = new Date();
      String result;
      DateFormat formatter;

      System.out.println();
      System.out.println("Locale: " + currentLocale.toString());
      System.out.println();

      for (int k = 0; k < styles.length; k++) {
         formatter = 
            DateFormat.getTimeInstance(styles[k], currentLocale);
         result = formatter.format(today);
         System.out.println(result);
      }
   }

   static public void main(String[] args) {

      Locale[] locales = {
          new Locale("be","BY"),
          new Locale("ru","RU"),
          new Locale("fr","FR"),
          new Locale("de","DE"),
          new Locale("en","US")
      };
     
      for (int i = 0; i < locales.length; i++) {
         displayDate(locales[i]);
      }

      showDateStyles(new Locale("en","US"));
      showDateStyles(new Locale("ru","RU"));
      showDateStyles(new Locale("be","BY"));

      showTimeStyles(new Locale("en","US"));
      showTimeStyles(new Locale("ru","RU"));
      showTimeStyles(new Locale("be","BY"));
 
      showBothStyles(new Locale("en","US"));
      showBothStyles(new Locale("ru","RU"));
      showBothStyles(new Locale("be","BY"));

   }
}
