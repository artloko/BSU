import java.applet.*;
import java.awt.*;

public class bar_chart extends Applet {
   final static int data_set[] = { 50, 85, 32, 65 };
   final static String data_label[] = { "93", "94", "95", "96" };
 
   int Graph_offset = 20;             // �������� �� ������ ���������
   int Graph_Height = 150;
   int Y_Tick_Height = Graph_Height / 5; // ����������� ������� �� ��� Y
   int Graph_Width = 240;
   int X_Tick_Width = Graph_Width / data_set.length; // ����������� �������
                                                     // �� ��� X

   public void paint(Graphics g)
     {
       g.setColor(Color.black);

       // ����������� ��� y
       g.drawLine(Graph_offset + Graph_Width, Graph_offset,
          Graph_offset + Graph_Width, Graph_offset + Graph_Height);

       // ����������� ����� ��� y
       for(int i=0; i<=5; i++)
         {
           g.drawString(String.valueOf(i*20),  // 20 ����� �� �����
              Graph_offset + Graph_Width + 10, 
              Graph_offset + Graph_Height -(i*Y_Tick_Height));

           // ����������� ����� �����
           g.drawLine(Graph_offset,
              Graph_offset + Graph_Height -(i*Y_Tick_Height), 
              Graph_offset + Graph_Width + 5, 
              Graph_offset + Graph_Height -(i*Y_Tick_Height));
         }

        // ����������� ��� x
        g.drawLine(Graph_offset, Graph_offset + Graph_Height, 
                  Graph_offset + Graph_Width, Graph_offset +
                  Graph_Height);

        // ����������� ����� ��� x
        for(int i=0; i<data_set.length; i++)
          {
             g.drawString(data_label[i],
               Graph_offset + X_Tick_Width*i + X_Tick_Width/2,
               Graph_offset + Graph_Height + 20);

             // ����������� ����� �� ��� x
             g.drawLine(Graph_offset + X_Tick_Width*i +
                X_Tick_Width/2,
                Graph_offset + Graph_Height,
                Graph_offset + X_Tick_Width*i + X_Tick_Width/2,
                Graph_offset + Graph_Height + 5);
          }
      
        // ����� ������
        g.setColor(Color.red);
        for(int i=0; i<data_set.length; i++)
          {
            int bar_height = data_set[i] * Graph_Height / 100;

            g.fillRect(Graph_offset + X_Tick_Width*i +
               X_Tick_Width/4,
               Graph_offset + Graph_Height - bar_height,
               X_Tick_Width/2,  bar_height);
          }
     }
 }

