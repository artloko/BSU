import java.applet.*;
import java.awt.*;

public class draw_poly extends Applet {
   int poly1_x[] = {  40,  80,   0,  40};
   int poly1_y[] = {   5,  45,  45,   5};

   int poly2_x[] = { 140, 180, 180, 140, 100, 100, 140};
   int poly2_y[] = {   5,  25,  45,  65,  45,  25,   5};

   int poly3_x[] = { 240, 260, 220, 260, 220, 240};
   int poly3_y[] = {   5,  65,  85,  25,  25,   5};
   
   public void paint(Graphics g)
     {
       g.drawPolygon(poly1_x, poly1_y, poly1_x.length);
       g.drawPolygon(poly2_x, poly2_y, poly2_x.length);
       g.drawPolygon(poly3_x, poly3_y, poly3_x.length);
     }
 }

