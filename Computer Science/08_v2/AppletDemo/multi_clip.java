import java.applet.*;
import java.awt.*;

public class multi_clip extends Applet {

   void draw_shapes(Graphics g)
     {
       /* вывод нескольких фигур */
       g.setColor(Color.blue);
       g.fillOval(90, 50, 100, 80);
       g.setColor(Color.red);
       g.fillRoundRect(10, 10, 150, 70, 50, 25);
       g.setColor(Color.green);
       g.fillRect(150, 20, 50, 50);
     }

   public void paint(Graphics g)
     {
       /* установка прямоугольника отсечения */
       Graphics g1 = g.create();
       g1.clipRect(90, 50, 80, 40);
       draw_shapes(g1);
       g1.dispose();
      
       /* установка еще одного прямоугольника отсечения */
       Graphics g2 = g.create();
       g2.clipRect(40, 0, 50, 50);
       draw_shapes(g2);
       g2.dispose();
      
       /* установка третьего прямоугольника отсечения */
       Graphics g3 = g.create();
       g3.clipRect(160, 20, 80, 40);
       draw_shapes(g3);
       g3.dispose();
     }
 }

