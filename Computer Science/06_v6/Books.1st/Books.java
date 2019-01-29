import java.io.*;
import java.util.*;

public class Books {

    public static void main(String[] args) {
        try {
            if ( args.length >= 1 ) {
                if ( args[0].compareTo( "-a" )== 0 ) {
                    // Append file with new object from System.in
                    append_file();
                }
                else if ( args[0].compareTo( "-p" )== 0 ) {
                    // Prints data file
                    print_file();
                }
                else if ( args[0].compareTo( "-d" )== 0 ) {
                    // Delete data file
                    delete_file();
                }
                else {
                    System.err.println( "Option is not realised: " + args[0] );
                    System.exit(1);
                }
            }
            else {
                System.err.println( "Books: Nothing to do!" );
            }
        }
        catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            System.exit(1);
        }
        System.out.println( "Books finished..." );	
	System.exit(0);
    }

    static final String filename = "Books.dat";
	
    private static Scanner fin = new Scanner( System.in );

    static Book read_book() {
        if ( fin.hasNextLine()) {
            return Book.read(fin);
        }
        return null;
    }
	
    static void delete_file() {
        File f = new File( filename );
        f.delete();
    }
	
    static void append_file() throws FileNotFoundException, IOException {
        Book book;
        System.out.println( "Enter book data: " );
        try ( RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            while (( book = read_book())!= null ) {
                Buffer.writeObject( raf, book );
	    }
        }
    }

    static void print_file() 
            throws FileNotFoundException, IOException, ClassNotFoundException {
        try ( RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            long pos;
            while (( pos = raf.getFilePointer()) < raf.length() ) {
                Book book = (Book) Buffer.readObject( raf, pos );
                System.out.println( pos + ": " + book );
            }
        }		
    }
}
