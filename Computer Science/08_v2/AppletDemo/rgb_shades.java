import java.applet.*;
import java.awt.*;

public class rgb_shades extends Applet {

   public void paint(Graphics g)
     {
       // оттенки красного
       for(int i=1; i<=8; i++)
         {
           Color color = new Color(i*32 - 1, 0, 0);
           g.setColor(color);
           g.fillRect(i*30 + 2, 2, 28, 28);
         }

      // оттенки зеленого
      for(int i=1; i<=8; i++)
        {
          Color color = new Color(0, i*32 - 1, 0);
          g.setColor(color);
          g.fillRect(i*30 + 2, 32, 28, 28);
        }

      // оттенки синего
      for(int i=1; i<=8; i++)
        {
          Color color = new Color(0, 0, i*32 - 1);
          g.setColor(color);
          g.fillRect(i*30 + 2, 62, 28, 28);
        }
     }
 }

