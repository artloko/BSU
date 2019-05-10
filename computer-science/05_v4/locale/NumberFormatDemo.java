
import java.util.*;
import java.text.*;

public class NumberFormatDemo {

   static public void displayNumber(Locale currentLocale) {

      Integer quantity = new Integer(123456);
      Double amount = new Double(345987.246);
      NumberFormat numberFormatter;
      String quantityOut;
      String amountOut;

      numberFormatter = NumberFormat.getNumberInstance(currentLocale);
      quantityOut = numberFormatter.format(quantity);
      amountOut = numberFormatter.format(amount);
      System.out.println(quantityOut + "   " + currentLocale.toString());
      System.out.println(amountOut + "   " + currentLocale.toString());
   }

   static public void displayCurrency(Locale currentLocale) {

      Double currency = new Double(9876543.21);
      NumberFormat currencyFormatter;
      String currencyOut;

      currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
      currencyOut = currencyFormatter.format(currency);
      System.out.println(currencyOut + "   " + currentLocale.toString());
   }

   static public void displayPercent(Locale currentLocale) {

      Double percent = new Double(0.75);
      NumberFormat percentFormatter;
      String percentOut;

      percentFormatter = NumberFormat.getPercentInstance(currentLocale);
      percentOut = percentFormatter.format(percent);
      System.out.println(percentOut + "   " + currentLocale.toString());
   }

   static public void main(String[] args) {

      Locale[] locales = {
          new Locale("fr","FR"),
          new Locale("de","DE"),
          new Locale("ru","RU"),
          new Locale("be","BY"),
          new Locale("en","US")
      };

      for (int i = 0; i < locales.length; i++) {
         System.out.println();
         displayNumber(locales[i]);
         displayCurrency(locales[i]);
         displayPercent(locales[i]);
      }
   }

}

