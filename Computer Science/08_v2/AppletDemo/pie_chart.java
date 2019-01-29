import java.applet.*;
import java.awt.*;

public class pie_chart extends Applet {
   
   final static int data_set[] = { 20, 40, 32, 8 };

   final static String data_label[] =
     {"Apple","Orange","Banana","Other"};

   final static Color data_color[] = 
      {Color.red, Color.blue, Color.yellow, Color.green};

   int Graph_offset = 20;       // смещение от начала координат
   int Graph_Diameter = 150;
   
   public void paint(Graphics g)
     {
       int start_angle, pie_size;
       int sub_total = 0;
      
       start_angle = 0;
       for(int i=0; i < data_set.length; i++)
         {
           // вывод сектора
           sub_total += data_set[i];      
           pie_size = sub_total * 360 / 100 - start_angle;
           g.setColor(data_color[i]);
           g.fillArc(Graph_offset,  Graph_offset, Graph_Diameter,
                     Graph_Diameter, start_angle,  pie_size);

           start_angle += pie_size;
         
           // вывод легенды
           g.fillRect(Graph_offset + Graph_Diameter + 10,
                       Graph_offset + i * 20, 15, 15);
           g.setColor(Color.black);
           g.drawString(data_label[i], Graph_offset +
                        Graph_Diameter + 10 + 20,
                        Graph_offset + i * 20 + 15);           
        }
     }
  }

