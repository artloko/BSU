import java.applet.*;
import java.awt.*;

public class color_textline extends Applet {

   public void paint(Graphics g)
     {
       // ����� ������� ������
       g.setColor(Color.black);
       g.drawString("A line of text in black", 5, 30);
     
       // ����� �������� ������
       g.setColor(Color.red);
       g.drawString("A line of text in red", 5, 60);
     
       // ����� ������� ������
       g.setColor(Color.magenta);
       g.drawString("A line of text in magenta", 5, 90);
     }
 }

