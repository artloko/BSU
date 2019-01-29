import java.applet.*;
import java.awt.*;

public class fonts extends Applet {
   
   Font f1 = new Font("Helvetica", Font.PLAIN, 18);
   Font f2 = new Font("Helvetica", Font.BOLD, 10);
   Font f3 = new Font("Helvetica", Font.ITALIC, 12);
   Font f4 = new Font("Courier",   Font.PLAIN, 12);
   Font f5 = new Font("TimesRoman", Font.BOLD + Font.ITALIC, 14);
   Font f6 = new Font("Dialog", Font.ITALIC, 14);
   
   public void start() {
       repaint();
   }

   public void paint(Graphics g)
     {
       g.setFont(f1); 
       g.drawString("18pt plain Helvetica", 5, 20);
 
       g.setFont(f2); 
       g.drawString("10pt bold Helvetica", 5, 43);
 
       g.setFont(f3); 
       g.drawString("12pt italic Helvetica", 5, 58);
 
       g.setFont(f4); 
       g.drawString("12pt plain Courier", 5, 75);
 
       g.setFont(f5); 
       g.drawString("14pt bold & italic Times Roman", 5, 92);
 
       g.setFont(f6); 
       g.drawString("14pt italic Dialog", 5, 111);
    }
 }

