import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


//изменить на коллекцию

class KeyComp implements Comparator<String>{
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

class KeyCompReverse implements Comparator<String>{
    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}

interface IndexBase{
    String[] getKeys(Comparator<String> comparator);
    void put(String key, long value);
    boolean contains(String key);
    LinkedList<Long> get(String key);
}

class IndexOne2One implements Serializable, IndexBase{

    private static final long serialVersionUID = 1L;

    private TreeMap<String, Long> map;

    public IndexOne2One(){
        map = new TreeMap<>();
    }

    @Override
    public String[] getKeys(Comparator<String> comparator) {
        String[] result = map.keySet().toArray(new String[0]);
        Arrays.sort(result, comparator);
        return result;
    }

    @Override
    public void put(String key, long value) {
        map.put(key, value);
    }

    @Override
    public boolean contains(String key) {
        return map.containsKey(key);
    }

    @Override
    public LinkedList<Long> get(String key) {
        long pos = map.get(key);
        LinkedList<Long> arr = new LinkedList<>();
        arr.add(pos);
        return arr;
    }
}

class IndexOne2N implements Serializable, IndexBase{

    private static final long serialVersionUID = 1L;

    private TreeMap<String, LinkedList<Long>> map;

    public IndexOne2N(){
        map = new TreeMap<>();
    }

    @Override
    public String[] getKeys(Comparator<String> comparator) {
        String[] result = map.keySet().toArray(new String[0]);
        Arrays.sort(result, comparator);
        return result;
    }

    @Override
    public void put(String key, long value) {
        LinkedList<Long> arr = map.get(key);
        if (arr == null) {
            arr = new LinkedList<>();
        }
        arr.add(value);
        map.put(key, arr);
    }

    @Override
    public boolean contains(String key) {
        return map.containsKey(key);
    }

    @Override
    public LinkedList<Long> get(String key) {
        return map.get(key);
    }
}

class KeyNotUniqueException extends Exception{

    private static final long serialVersionUID = 1L;

    public KeyNotUniqueException(String key){
        super("Key is Not Unique: " + key);
    }
}

public class Index implements Serializable, Closeable{

    private static final long serialVersionUID = 1L;

    IndexOne2N drugStores;
    IndexOne2One name;
    IndexOne2N dateOfReceipt;
    IndexOne2N shelfDate;

    public void test(Drug drug) throws KeyNotUniqueException{
        assert (drug != null);
        if (name.contains(drug.name))
            throw new KeyNotUniqueException(drug.name);
        if (dateOfReceipt.contains(drug.dateOfReceipt.toString()))
            throw new KeyNotUniqueException(drug.dateOfReceipt.toString());
        if (shelfDate.contains(drug.shelfLife.toString()))
            throw new KeyNotUniqueException(drug.shelfLife.toString());
    }

    public void put( Drug drug, long value ) throws KeyNotUniqueException {
        test(drug);
        drugStores.put( drug.drugStore, value );
        name.put( drug.name, value);
        dateOfReceipt.put( drug.dateOfReceipt.toString(), value);
        shelfDate.put(drug.shelfLife.toString(), value);
    }

    public Index(){
        drugStores = new IndexOne2N();
        name = new IndexOne2One();
        dateOfReceipt = new IndexOne2N();
        shelfDate = new IndexOne2N();
    }

    public static Index load( String name )
            throws IOException, ClassNotFoundException {
        Index obj = null;
        try {
            FileInputStream file = new FileInputStream( name );
            try( ZipInputStream zis = new ZipInputStream( file )) {
                ZipEntry zen = zis.getNextEntry();
                if (!zen.getName().equals( Buffer.zipEntryName )) {
                    throw new IOException("Invalid block format");
                }
                try ( ObjectInputStream ois = new ObjectInputStream( zis )) {
                    obj = (Index) ois.readObject();
                }
            }
        } catch ( FileNotFoundException e ) {
            obj = new Index();
        }
        if ( obj != null ) {
            obj.save( name );
        }
        return obj;
    }

    private transient String filename = null;

    public void save( String name ) {
        filename = name;
    }

    public void saveAs( String name ) throws IOException {
        FileOutputStream file = new FileOutputStream( name );
        try ( ZipOutputStream zos = new ZipOutputStream( file )) {
            zos.putNextEntry( new ZipEntry( Buffer.zipEntryName ));
            zos.setLevel( ZipOutputStream.DEFLATED );
            try ( ObjectOutputStream oos = new ObjectOutputStream( zos )) {
                oos.writeObject( this );
                oos.flush();
                zos.closeEntry();
                zos.flush();
            }
        }
    }

    public void close() throws IOException {
        saveAs( filename );
    }
}