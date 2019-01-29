import java.applet.*;
import java.awt.*;

public class draw_char extends Applet {

   final static char mystring[] = 
      {'J','a','v','a',' ','T','i','p','s'};

   public void paint(Graphics g)
     {
       // вывод всей строки
       g.drawChars(mystring, 0, mystring.length, 0, 25);
       // вывод подстроки "Java"
       g.drawChars(mystring, 0, 4, 0, 50);
     }
 }

