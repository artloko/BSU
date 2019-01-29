import java.io.*;

public class Connector {
	
	private String filename;
	
	public Connector( String filename ) {
		this.filename = filename;
	}
	
	public void write( Instrument[] band) throws IOException {
		FileOutputStream fos = new FileOutputStream (filename);
		try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
			oos.writeInt( band.length );
			for ( int i = 0; i < band.length; i++) {
				oos.writeObject( band[i] );		
			}
			oos.flush();
		}
	}
	
	public Instrument[] read() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
			int length = oin.readInt();
			Instrument[] result = new Instrument[length];
			for ( int i = 0; i < length; i++ ) {
				result[i] = (Instrument) oin.readObject();
			}
			return result;	
		}
	}
	
}
