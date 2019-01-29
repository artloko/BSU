package org.bsu.source;

import java.io.*;
import java.util.*;

public class Drugs {

    static String filename    = "Drugs.dat";
    static String filenameBak = "Drugs.~dat";
    static String idxname     = "Drugs.idx";
    static String idxnameBak  = "Drugs.~idx";

    private static String encoding = "Cp866";
    private static PrintStream stream = System.out;

    static Drug readDrug(Scanner fin) throws IOException{
        return Drug.nextRead(fin, stream) ? Drug.read(fin, stream) : null;
    }

    private static void deleteBackUp(){
        new File(filenameBak).delete();
        new File(idxnameBak).delete();
    }

    public static void deleteFile(){
        deleteBackUp();
        new File(filename).delete();
        new File(idxname).delete();
    }

    private static void backUp(){
        deleteBackUp();
        new File(filename).renameTo(new File(filenameBak));
        new File(idxname).renameTo(new File(idxnameBak));
    }

    public static boolean deleteFile(String[] args) throws ClassNotFoundException, IOException, KeyNotUniqueException{
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

    public static void appendFile(String[] args, Boolean zipped)
            throws FileNotFoundException, IOException, ClassNotFoundException, KeyNotUniqueException{
        if (args.length >= 2){
            FileInputStream inputStream = new FileInputStream(args[1]);
            System.setIn(inputStream);
            if (args.length == 3)
                encoding = args[2];
            stream = new PrintStream("nul");
            appendFile(zipped);
        }
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
        for(long pos : poss){
            System.out.print("*** Key: " + key + " point to");
            printRecord(accessFile, pos);
        }
    }

    public static void printFile() throws FileNotFoundException, IOException, ClassNotFoundException{
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

    public static boolean printFile( String[] args, boolean reverse )
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

    public static boolean findByKey( String[] args )
            throws ClassNotFoundException, IOException {
        if ( args.length != 3 ) {
            System.err.println( "Invalid number of arguments" );
            return false;
        }
        try ( Index idx = Index.load( idxname );
              RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            IndexBase pidx = indexByArg( args[1], idx );
            if ( !pidx.contains(args[2]) ) {
                System.err.println( "Key not found: " + args[2] );
                return false;
            }
            printRecord( raf, args[2], pidx );
        }
        return true;
    }

    public static boolean findByKey( String[] args, Comparator<String> comp )
            throws ClassNotFoundException, IOException {
        if ( args.length != 3 ) {
            System.err.println( "Invalid number of arguments" );
            return false;
        }
        try ( Index idx = Index.load( idxname );
              RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            IndexBase pidx = indexByArg( args[1], idx );
            if ( !pidx.contains(args[2]) ) {
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
