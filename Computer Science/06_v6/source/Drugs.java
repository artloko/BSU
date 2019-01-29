import java.io.*;
import java.util.*;

public class Drugs {
    public static void main(String[] args) {
        try {
            if ( args.length >= 1 ) {
                if ( args[0].compareTo( "-a" )== 0 ) {
                    // Append file with new object from System.in
                    appendFile();
                }
                else if ( args[0].compareTo( "-p" )== 0 ) {
                    // Prints data file
                    printFile();
                }
                else if ( args[0].compareTo( "-d" )== 0 ) {
                    // Delete data file
                    deleteFile();
                }
                else {
                    System.err.println( "Option is not realised: " + args[0] );
                    System.exit(1);
                }
            }
            else {
                System.err.println( "Drugs: Nothing to do!" );
            }
        }
        catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            System.exit(1);
        }
        System.out.println( "Drugs finished..." );
        System.exit(0);
    }

    static final String filename = "Drugs.dat";

    static Scanner fin;

    static Drug getDrug() {
        if ( fin.hasNextLine()) {
            return Drug.read(fin);
        }
        return null;
    }

    static void deleteFile() {
        File f = new File( filename );
        f.delete();
    }

    static void appendFile() throws IOException {
        Drug drug;
        fin = new Scanner(new File("drugs.txt"));
        try (RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            while (( drug = getDrug())!= null ) {
                Buffer.writeObject( raf, drug );
            }
        }
        System.out.println("File: " + filename + ", has been just appended with information.");
    }

    static void printFile()
            throws IOException, ClassNotFoundException {
        try ( RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            long pos;
            while (( pos = raf.getFilePointer()) < raf.length() ) {
                Drug drug = (Drug) Buffer.readObject( raf, pos );
                System.out.println( pos + ": " + drug );
            }
        }
    }
}
