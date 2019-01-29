import java.applet.*;
import java.awt.*;

public class poly_object extends Applet {
   int xpoints[] = {30, 90,140, 30, 53, 23};
   int ypoints[] = {32, 75, 30, 72,107, 80};
   // получение числа точек
   int num_pts = ypoints.length ;
      
   Polygon a_poly = new Polygon(xpoints, ypoints, num_pts ) ;
   Polygon b_poly = new Polygon() ;
   
   public void init () {
      // добавление точек ко второму полигону (b_poly )
      b_poly.addPoint( 200, 20) ;
      b_poly.addPoint( 220, 40 ) ;
      b_poly.addPoint( 205, 180) ; 
      b_poly.addPoint( 215, 160 ) ;
   }

   public void paint (Graphics g) {
      // отображение полигонов
      g.setColor (Color.yellow);
      g.fillPolygon(a_poly);

      g.setColor ( Color.green);
      g.fillPolygon (b_poly);
   }

}
