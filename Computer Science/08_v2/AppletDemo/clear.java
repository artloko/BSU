import java.applet.*;
import java.awt.*;

public class clear extends Applet {

   public void paint(Graphics g)
     {
       /* отображение нескольких фигур */
       g.setColor(Color.red);
       g.fillRoundRect(10, 10, 160, 80, 50, 25);
       g.setColor(Color.blue);
       g.fillOval(80, 40, 100, 80);
       g.setColor(Color.green);
       g.fillRect(150, 20, 50, 50);
       /* очистка прямоугольной области */
       g.clearRect(90, 50, 80, 40);
     }
 }

