import java.applet.*;
import java.awt.*;

public class background extends Applet {

   public void init()
     {
       setBackground(Color.black);
     }

   public void paint(Graphics g)
     {
       g.setColor(Color.white);
       g.drawString("White text on black background", 5, 50);
     }
 }

