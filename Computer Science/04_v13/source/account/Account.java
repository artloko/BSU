package account;

import java.util.Arrays;
import java.util.Iterator;

public class Account implements Comparable<Account>, Iterator<String>, Iterable<String> {

    private static final int YEAR = 365;
    private String[] areas = null;

    public static final String[] names =
            {
                    "userName",
                    "bankAccountNumber",
                    "currency",
                    "percentPerYear",
                    "value"
            };

    private static int sortBy = 0;
    public static int getSortBy(){
        return sortBy;
    }
    public static void setSortBy(int value){
        if (value >= names.length || value < 0){
            throw new IndexOutOfBoundsException();
        }
        sortBy = value;
    }
    public static String getSortByField(){
        return Account.names[getSortBy()];
    }

    protected boolean validName(String str) {
        return str != null && str.length() > 0;
    }
    protected boolean validBankAccountNumber(String str) {
        return str != null && str.length() > 0 && str.length() < 21;
    }
    protected boolean validCurrency( String str) {
        return str != null && str.length() > 0;
    }
    protected boolean validPercentPerYear( String str ) {
        return str != null && Integer.parseInt(str) > 0 && Integer.parseInt(str) < 100;
    }
    protected boolean validValue( String str ) {
        return str != null && Integer.parseInt(str) > 0;
    }


    public int length(){
        return areas.length;
    }

    public int getAreasLength(){
        return areas.length;
    }

    public String getArea(int idx){
        if (idx >= length() || idx < 0){
            throw new IndexOutOfBoundsException();
        }
        return areas[idx];
    }

    public void setArea(int idx, String value) throws ArgException{
        if (idx >= length() || idx < 0){
            throw new IndexOutOfBoundsException();
        }
        if ((idx == 0 && !validName(value))||
                (idx == 1 && !validBankAccountNumber(value)) ||
                (idx == 2 && !validCurrency(value)) ||
                (idx == 3 && !validPercentPerYear(value)) ||
                (idx == 4 && !validValue(value))) {
            throw new ArgException(value);
        }
        areas[idx] = value;
    }

    @Override
    public Iterator<String> iterator() {
        reset();
        return this;
    }

    private int iterator_idx = 0;
    public void reset(){
        iterator_idx = 0;
    }

    public boolean hasNext(){
        return iterator_idx >= areas.length ? false : true;
    }

    @Override
    public String next() {
        if (iterator_idx < areas.length){
            return areas[iterator_idx++];
        }
        reset();
        return null;
    }

    @Override
    public int compareTo(Account o) {
        return areas[getSortBy()].compareTo(o.areas[Account.getSortBy()]);
    }

    @Override
    public String toString() {
        if (areas == null){
            return " | | | | | ";
        }
        String res = areas[0];
        for(int i = 1; i < areas.length; i++){
            res += "|" + areas[i];
        }
        return res;
    }

    private void setup(String[] args) throws ArgException {
        if (args == null){
            throw new ArgException("Null pointer passed for args");
        }
        if (args.length < 2 || args.length > names.length){
            throw new ArgException(Arrays.toString(args));
        }
        areas = new String[names.length];
        for(int i = 0; i < args.length; i++){
            setArea(i, args[i]);
        }
    }

    public Account(String account) throws ArgException {
        if (account == null){
            throw new ArgException("Null pointer passed for account info");
        }
        String[] fields;
        fields = account.split("[|]");
        setup(fields);
    }

    public boolean withdraw(double value){
        if (Double.compare(value, 0) < 0 || Double.compare(Double.parseDouble(areas[areas.length - 1]), value) < 0){
            return false;
        }
        areas[areas.length - 1] = ((Object)(Double.parseDouble(areas[areas.length - 1]) - value)).toString();
        return true;
    }

    public boolean add(double value){
        if (Double.compare(value, 0) < 0 || Double.compare(Double.parseDouble(areas[areas.length - 1]), value) < 0){
            return false;
        }
        areas[areas.length - 1] = ((Object)(Double.parseDouble(areas[areas.length - 1]) + value)).toString();
        return true;
    }

    public boolean addPercent(double numOfDays){
        if (numOfDays < 0 || numOfDays > 365){
            return false;
        }
        areas[areas.length - 1] = ((Object)(Double.parseDouble(areas[areas.length - 1]) +
                numOfDays / YEAR * Double.parseDouble(areas[areas.length - 2]) / 100
                        * Double.parseDouble(areas[areas.length - 1]))).toString();
        return true;
    }

}
