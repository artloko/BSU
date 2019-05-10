import java.applet.*;
import java.awt.*;

public class bright_dark extends Applet {
 
   Color dark_red = new Color(4, 0, 0);
   Color light_gray = new Color(250, 250, 250);
   
   public void paint(Graphics g)
     {
       Color color;
       // €ркие цвета
       color = dark_red;
       for (int i = 0; i < 16; i++)
         {
           g.setColor(color);
           g.fillRect(0, i*5, 300, 5);
           color = color.brighter();
         }

       // темные цвета
       color = light_gray;
       for(int i = 16; i < 32; i++)
         {
           g.setColor(color);
           g.fillRect(0, i*5, 300, 5);
           color = color.darker();
         }
     }
 }

