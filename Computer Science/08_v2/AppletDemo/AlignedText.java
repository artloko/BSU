import java.awt.*;

public class AlignedText {
   // ����������� �������� ��� ������������ ������
   public static final int LEFT = 0x00;
   public static final int RIGHT = 0x01;
   public static final int CENTER = 0x02;
   public static final int BASELINE = 0x00;
   public static final int TOP = 0x04;
   public static final int BOTTOM = 0x08;

   private int    alignmentType;
 
   public AlignedText()
     {
        alignmentType = LEFT | BASELINE; // ����������� ������������
     }

   public void set_alignment(int alignment_type)
     {
        alignmentType = alignment_type;
     }

   public void drawString(Graphics g, String aString , int x, int y)
     {
        FontMetrics fmetrics = g.getFontMetrics();
        int len =  fmetrics.stringWidth(aString);
 
        if ((alignmentType & RIGHT) != 0) 
          x -= len;     // �������� x ��� ������������ �� ������� ����

        else if ((alignmentType & CENTER) != 0) 
          x -= len/2;   // �������� x ��� ������������ �� ������

        if ((alignmentType & TOP) != 0) 
          y += fmetrics.getAscent();   // �������� y ��� ������������ �� �������� ����
 
        else if((alignmentType & BOTTOM) != 0) 
          y -= fmetrics.getDescent();  // �������� y ��� ������������ �� ������� ����

        g.drawString(aString, x, y);
     }
 }

