import java.applet.*;
import java.awt.*;

public class xor_mode extends Applet {

   public void paint (Graphics g)
     {
       g.setColor (Color.yellow);
       g.fillRect (0, 0, 150, 100);  
       g.setXORMode (Color.red);
       g.fillRect (50, 25, 200, 50);
     }
 }

