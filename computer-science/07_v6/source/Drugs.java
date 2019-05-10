import java.io.*;
import java.util.*;

public class Drugs {
    public static void main(String[] args) {
        try {
            if ( args.length >= 1 ) {
                if ( args[0].equals("-?") || args[0].equals("-h")) {
                    System.out.println(
                            "Syntax:\n" +
                                    "\t-a  [file [encoding]]    - append data\n" +
                                    "\t-az [file [encoding]]    - append data, compress every record\n" +
                                    "\t-d                       - clear all data\n" +
                                    "\t-dk  {ds|n|dr|sd} key    - clear data by key\n" +
                                    "\t-p                       - print data unsorted\n" +
                                    "\t-ps  {ds|n|dr|sd}        - print data sorted by drugstore/name/dateofreceipt/shelfdate\n" +
                                    "\t-psr {ds|n|dr|sd}        - print data reverse sorted by drugstore/name/dateofreceipt/shelfdate\n" +
                                    "\t-f   {ds|n|dr|sd} key    - find record by key\n"+
                                    "\t-fr  {ds|n|dr|sd} key    - find records > key\n"+
                                    "\t-fl  {ds|n|dr|sd} key    - find records < key\n"+
                                    "\t-?, -h                   - command line syntax\n"
                    );
                }
                else if ( args[0].equals( "-a" )) {
                    // Append file with new object from System.in
                    // -a [file [encoding]]
                    appendFile( args, false );
                }
                else if ( args[0].equals( "-az" )) {
                    // Append file with compressed new object from System.in
                    // -az [file [encoding]]
                    appendFile( args, true );
                }
                else if ( args[0].equals( "-p" )) {
                    // Prints data file
                    printFile();
                }
                else if ( args[0].equals( "-ps" )) {
                    // Prints data file sorted by key
                    if ( !printFile( args, false ) ) {
                        System.exit(1);
                    }
                }
                else if ( args[0].equals( "-psr" )) {
                    // Prints data file reverse-sorted by key
                    if ( !printFile( args, true ) ) {
                        System.exit(1);
                    }
                }
                else if ( args[0].equals( "-d" )) {
                    // delete files
                    if ( args.length != 1 ) {
                        System.err.println("Invalid number of arguments");
                        System.exit(1);
                    }
                    deleteFile();
                }
                else if ( args[0].equals( "-dk" )) {
                    // Delete records by key
                    if ( deleteFile( args )== false ) {
                        System.exit(1);
                    }
                }
                else if ( args[0].equals( "-f" )) {
                    // Find record(s) by key
                    if ( findByKey( args )== false ) {
                        System.exit(1);
                    }
                }
                else if ( args[0].equals( "-fr" )) {
                    // Find record(s) by key large then key
                    if ( findByKey( args, new KeyCompReverse() )== false ) {
                        System.exit(1);
                    }
                }
                else if ( args[0].equals( "-fl" )) {
                    // Find record(s) by key less then key
                    if ( findByKey( args, new KeyComp() )== false ) {
                        System.exit(1);
                    }
                }
                else {
                    System.err.println( "Option is not realised: " + args[0] );
                    System.exit(1);
                }
            }
            else {
                System.err.println( "Drugs: Nothing to do! Enter -? for options" );
            }
        }
        catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println( "Drugs finished..." );
        System.exit(0);
    }

    static final String filename    = "Drugs.dat";
    static final String filenameBak = "Drugs.~dat";
    static final String idxname     = "Drugs.idx";
    static final String idxnameBak  = "Drugs.~idx";

    private static String encoding = "Cp866";
    private static PrintStream stream = System.out;

    static Drug readDrug(Scanner fin) throws IOException{
        return Drug.nextRead(fin, stream) ? Drug.read(fin, stream) : null;
    }

    private static void deleteBackUp(){
        new File(filenameBak).delete();
        new File(idxnameBak).delete();
    }

    static void deleteFile(){
        deleteBackUp();
        new File(filename).delete();
        new File(idxname).delete();
    }

    private static void backUp(){
        deleteBackUp();
        new File(filename).renameTo(new File(filenameBak));
        new File(idxname).renameTo(new File(idxnameBak));
    }

    static boolean deleteFile(String[] args) throws ClassNotFoundException, IOException, KeyNotUniqueException{
        if (args.length != 3){
            System.err.println("Invalid number of arguments");
            return false;
        }
        LinkedList<Long> poss = null;
        try(Index index = Index.load(idxname)){
            IndexBase pidx = indexByArg(args[1], index);
            if (pidx == null)
                return false;
            if (!pidx.contains(args[2])){
                System.err.println("Key not found: " + args[2]);
                return false;
            }
            poss = pidx.get(args[2]);
        }
        backUp();
        Collections.sort(poss);
        try(Index index = Index.load(idxname);
            RandomAccessFile fileBak = new RandomAccessFile(filenameBak, "rw");
            RandomAccessFile file = new RandomAccessFile( filename, "rw")) {
            boolean[] wasZipped = new boolean[]{false};
            long pos;
            while ((pos = fileBak.getFilePointer()) < fileBak.length()){
                Drug drug = (Drug)Buffer.readObject(fileBak, pos, wasZipped);
                if (Collections.binarySearch(poss, pos) < 0){
                    long ptr = Buffer.writeObject(file, drug, wasZipped[0]);
                    index.put(drug, ptr);
                }
            }
        }
        return true;
    }

    static void appendFile(String[] args, Boolean zipped)
            throws FileNotFoundException, IOException, ClassNotFoundException, KeyNotUniqueException{
        if (args.length >= 2){
            FileInputStream inputStream = new FileInputStream(args[1]);
            System.setIn(inputStream);
            if (args.length == 3)
                encoding = args[2];
            stream = new PrintStream("nul");
        }
        appendFile(zipped);
    }

    static void appendFile(Boolean zipped)
            throws FileNotFoundException, IOException, ClassNotFoundException, KeyNotUniqueException{
        Scanner scanner = new Scanner(System.in, encoding);
        stream.println("Enter drug data: " );
        try(Index index = Index.load(idxname);
            RandomAccessFile accessFile = new RandomAccessFile(filename, "rw")){
            for(;;){
                Drug drug = readDrug(scanner);
                if (drug == null)
                    break;
                index.test(drug);
                long pos = Buffer.writeObject(accessFile, drug, zipped);
                index.put(drug, pos);
            }
        }
    }

    private static void printRecord(RandomAccessFile accessFile, long pos)throws ClassNotFoundException, IOException{
        boolean[] wasZipped = new boolean[] {false};
        Drug drug = (Drug) Buffer.readObject(accessFile, pos, wasZipped);
        if (wasZipped[0])
            System.out.print(" compressed");
        System.out.println(" record at position " + pos + ": \n" + drug);
    }

    private static void printRecord(RandomAccessFile accessFile, String key, IndexBase base)
        throws ClassNotFoundException, IOException{
        LinkedList<Long> poss = base.get(key);
        for(int i = 0; i < poss.size(); i++){
            System.out.print("*** Key: " + key + " point to");
            printRecord(accessFile, poss.get(i));
        }
    }

    static void printFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        long pos;
        int rec = 0;
        try(RandomAccessFile accessFile = new RandomAccessFile(filename, "rw")){
            while ((pos = accessFile.getFilePointer()) < accessFile.length()){
                System.out.print("\n#" + (++rec));
                printRecord(accessFile, pos);
            }
            System.out.flush();
        }
    }

    private static IndexBase indexByArg( String arg, Index idx ) {
        IndexBase pidx = null;
        if ( arg.equals("ds")) {
            pidx = idx.drugStores;
        }
        else if ( arg.equals("n")) {
            pidx = idx.name;
        }
        else if ( arg.equals("dr")) {
            pidx = idx.dateOfReceipt;
        }
        else if ( arg.equals("sd")) {
            pidx = idx.shelfDate;
        }
        else {
            System.err.println( "Invalid index specified: " + arg );
        }
        return pidx;
    }

    static boolean printFile( String[] args, boolean reverse )
            throws ClassNotFoundException, IOException {
        if ( args.length != 2 ) {
            System.err.println( "Invalid number of arguments" );
            return false;
        }
        try ( Index idx = Index.load( idxname );
              RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            IndexBase pidx = indexByArg( args[1], idx );
            if ( pidx == null ) {
                return false;
            }
            String[] keys =
                    pidx.getKeys( reverse ? new KeyCompReverse() : new KeyComp() );
            for ( String key : keys ) {
                printRecord( raf, key, pidx );
            }
        }
        return true;
    }

    static boolean findByKey( String[] args )
            throws ClassNotFoundException, IOException {
        if ( args.length != 3 ) {
            System.err.println( "Invalid number of arguments" );
            return false;
        }
        try ( Index idx = Index.load( idxname );
              RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            IndexBase pidx = indexByArg( args[1], idx );
            if ( pidx.contains(args[2])== false ) {
                System.err.println( "Key not found: " + args[2] );
                return false;
            }
            printRecord( raf, args[2], pidx );
        }
        return true;
    }

    static boolean findByKey( String[] args, Comparator<String> comp )
            throws ClassNotFoundException, IOException {
        if ( args.length != 3 ) {
            System.err.println( "Invalid number of arguments" );
            return false;
        }
        try ( Index idx = Index.load( idxname );
              RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            IndexBase pidx = indexByArg( args[1], idx );
            if ( pidx.contains(args[2])== false ) {
                System.err.println( "Key not found: " + args[2] );
                return false;
            }
            String[] keys = pidx.getKeys( comp );
            for ( int i = 0; i < keys.length; i++ ) {
                String key = keys[i];
                if ( key.equals( args[2] )) {
                    break;
                }
                printRecord( raf, key, pidx );
            }
        }
        return true;
    }
}
