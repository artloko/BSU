import java.awt.*;
import java.applet.*;

public class aligntext_test extends Applet {

   AlignedText aligned_txt = new AlignedText();

   public void paint(Graphics g)
    {
      aligned_txt = new AlignedText();
 
      g.setColor(Color.black); 
      g.drawLine(50, 0, 50, 100);
      g.setColor(Color.blue); 
      aligned_txt.set_alignment(aligned_txt.LEFT);
      aligned_txt.drawString(g, "left", 50, 30);

      aligned_txt.set_alignment(aligned_txt.CENTER);
      aligned_txt.drawString(g, "center", 50, 60);
      aligned_txt.set_alignment(aligned_txt.RIGHT);
      aligned_txt.drawString(g, "right",  50, 90);

      g.setColor(Color.black); 
      g.drawLine(100, 50, 300, 50); 
      g.setColor(Color.blue); 
      aligned_txt.set_alignment(aligned_txt.BOTTOM);
      aligned_txt.drawString(g, "bottom", 100, 50);

      aligned_txt.set_alignment(aligned_txt.BASELINE);
      aligned_txt.drawString(g, "baseline", 160, 50);
      aligned_txt.set_alignment(aligned_txt.TOP);
      aligned_txt.drawString(g, "top", 220, 50);
    }
}

