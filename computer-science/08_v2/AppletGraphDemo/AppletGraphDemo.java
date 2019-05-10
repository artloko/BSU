import java.applet.Applet;
import java.awt.*;
import java.util.*;

class Drawing extends Canvas {

    Color clr;
    Dimension dim = new Dimension( 20, 20 );

    public Drawing( Color fc, Color bc ) {
        super();
        clr = fc;
        setBackground( bc );
        setMaximumSize( dim );
        setBounds( 0, 0, dim.width, dim.height );
    }

    public void paint(Graphics g) {
    	prepareDraw( g );
    	draw( g );
    }

    void prepareDraw( Graphics g ) {
    	g.translate( 10, 10 );
    	g.setColor( clr );
    }

    void draw( Graphics g ) {
        g.drawLine( -10, -10, 10, 10 );
        g.drawLine( 10, -10, -10, 10 );
    }

    public Dimension getMinimumSize() {
    	return dim;
    }

    public Dimension getPreferredSize() {
    	return dim;
    }
}

class DrawingImage extends Canvas {

    Dimension dim;
    Image img;
    Applet par;

    public DrawingImage( Image m, Applet parent ) {
        super();
        img = m;
        par = parent;
        dim = new Dimension( 20,20 );
        setBackground( null );
        setMaximumSize( dim );
        setBounds( 0, 0, dim.width, dim.height );
    }

    public void paint(Graphics g) {
    	g.drawImage(img, 0, 0, par );
    }

    public Dimension getMinimumSize() {
    	return dim;
    }

    public Dimension getPreferredSize() {
    	return dim;
    }
}

public class AppletGraphDemo extends Applet {

    static final int CX = 400, CY = 400;
    Canvas c, ci[] = new Canvas[15];

    public Color getHtmlColor( String strRGB, Color def ) {
        // in form #RRGGBB
        if ( strRGB != null && strRGB.charAt(0)== '#' ) {
            try {
                return new Color(
                    Integer.parseInt( strRGB.substring( 1 ), 16 ) );
            } catch ( NumberFormatException e ) {
                return def;
            }
        }
        return def;
    }

    public void init() {
        setSize( CX, CY );
        setLayout( null );
        Color col = getHtmlColor(
        		getParameter( "AppBkColor" ), new Color( 90, 90, 160 ));
        setBackground( col );
        Color colx = getHtmlColor(
        		getParameter( "DrawBkColor" ), new Color( 64, 64, 64 ));
        col = getHtmlColor(
        		getParameter( "DrawColor" ), Color.WHITE );
        c = new Drawing( col, colx );
        add(c);

        String s = getParameter( "DrawImage" );
        Image im = this.getImage( getCodeBase(), s == null ? "star.gif" : s );
        for ( int i = 0; i < ci.length; i++ ) {
            ci[i] = new DrawingImage( im, this );
            add( ci[i] );
        }
    }

    public void start() {
    	startThread();
    }

    public void stop() {
    	stopThread();
    }

    public void destroy() {
    	stopThread();
    }

    private AppletThread t = null;
    private void createThread() {
        if ( t == null ) {
            t = new AppletThread( this );
        }
    }

    private void startThread() {
    	createThread();
        t.start();
    }

    private void stopThread() {
        if ( t != null ) {
            t.interrupt();
            t = null;
        }
    }
}

class AppletThread extends Thread {
    AppletGraphDemo pa = null;
    Random r = new Random( new Date().getTime());
    volatile int ni = 0;

    public AppletThread( AppletGraphDemo pa ) {
    	super();
    	this.pa = pa;
    }

    public void run() {
    	while ( true ) {
            try {
                Thread.sleep( 100 );
                doMove();
            } catch ( InterruptedException e ) {
                break;
            }
        }
    }

    public synchronized void doMove() {
        // random coordinates:
      	pa.c.setLocation( r.nextInt(AppletGraphDemo.CX), r.nextInt(AppletGraphDemo.CY) );
      	pa.c.repaint();
      	pa.ci[ni].setLocation( r.nextInt(AppletGraphDemo.CX), r.nextInt(AppletGraphDemo.CY) );
      	pa.ci[ni].repaint();
        ni += 1;
        if ( ni >= pa.ci.length )
        	ni = 0;
    }
}
