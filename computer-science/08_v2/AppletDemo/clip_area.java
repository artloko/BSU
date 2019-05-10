import java.applet.*;
import java.awt.*;

public class clip_area extends Applet {

   public void paint(Graphics g)
     {
       g.clipRect(90, 50, 80, 40); // установка прямоугольника отсечения

       g.setColor(Color.blue);     // вывод нескольких фигур
       g.fillOval(90, 50, 100, 80);

       g.setColor(Color.red);
       g.fillRoundRect(10, 10, 150, 70, 50, 25);

       g.setColor(Color.green);
       g.fillRect(150, 20, 50, 50);
     }
 }

