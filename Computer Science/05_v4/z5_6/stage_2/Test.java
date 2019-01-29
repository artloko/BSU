import java.util.*;
import java.io.*;

public class Test {
	
	public static Instrument[] createBand() {
		Instrument[] band = new Instrument[5];
		
		band[0] = new Percussion( AppLocale.getString( AppLocale.drum ));
		band[0].setInfo( AppLocale.getString( AppLocale.small_drum ));
		band[1] = new Stringed( AppLocale.getString( AppLocale.guitar ));
		band[1].setInfo( AppLocale.getString( AppLocale.solo_guitar ));
		band[2] = new Stringed( AppLocale.getString(AppLocale.guitar ));
		band[2].setInfo(AppLocale.getString( AppLocale.rhythm_guitar ));
		band[3] = new Stringed( AppLocale.getString( AppLocale.bass )); 
		band[3].setInfo( AppLocale.getString(AppLocale.double_bass ));
		band[4] = new Wind( AppLocale.getString( AppLocale.sax ) );
		band[4].setInfo( AppLocale.getString( AppLocale.tenor_sax ));
		
		return band;
	}
	
	static Locale createLocale( String[] args )	{
		if ( args.length == 2 ) {
			return new Locale( args[0], args[1] );
		} else if( args.length == 4 ) {
			return new Locale( args[2], args[3] );
		}
		return null;
	}
	
	static void setupConsole(String[] args) {
		if ( args.length >= 2 ) {
			if ( args[0].compareTo("-encoding")== 0 ) {
				try {
					System.setOut( new PrintStream( System.out, true, args[1] ));
				} catch ( UnsupportedEncodingException ex ) {
					System.err.println( "Unsupported encoding: " + args[1] );
					System.exit(1);
				}				
			}
		}
	}

	public static void main(String[] args) {
		
		try {
			setupConsole( args );
			Locale loc = createLocale( args );
			if ( loc == null ) {
				System.err.println( 
						"Invalid argument(s)\n" +
				        "Syntax: [-encoding ENCODING_ID] language country\n" +
						"Example: -encoding Cp855 be BY" );
				System.exit(1);
			}
			AppLocale.set( loc );
			Connector con = new Connector("band.dat");	
			con.write( createBand());
			Instrument[] band = con.read();
			System.out.println( 
					AppLocale.getString( AppLocale.the_band ) + ":" );
			for ( Instrument n : band ) {
				System.out.println( n );
			}
		}
		catch ( Exception e ) {
			System.err.println(e);
		}
	}
}
