
public class Test {
	
	public static Instrument[] createBand() {
		Instrument[] band = new Instrument[5];
		
		band[0] = new Percussion( "Drum" );
		band[0].setInfo( "Small drum" );
		band[1] = new Stringed( "Guitar" );
		band[1].setInfo("Solo guitar");
		band[2] = new Stringed( "Guitar" );
		band[2].setInfo("Rythm guitar");
		band[3] = new Stringed( "Bass" );
		band[3].setInfo("Double bass");
		band[4] = new Wind( "Saxophone" );
		band[4].setInfo("Tenor saxophone");
		
		return band;
	}

	public static void main(String[] args) {
		
		try {
			Connector con = new Connector("band.dat");	
			con.write( createBand());
			Instrument[] band = con.read();
			System.out.println( "The band: ");
			for ( Instrument n : band ) {
				System.out.println( n );
			}
		}
		catch ( Exception e ) {
			System.err.println(e);
		}

	}

}
