import java.applet.*;
import java.awt.*;

public class ColorConstants extends Applet {

   public void paint(Graphics g)
     {
       g.setColor(Color.yellow);
       g.drawString("Yellow Yellow Yellow", 5, 30);
     
       g.setColor(Color.blue);
       g.drawString("Blue Blue Blue", 5, 60);
     
       g.setColor(Color.green);
       g.drawString("Green Green Green", 5, 90);
     }
 }

