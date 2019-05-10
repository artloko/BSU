import java.util.*;

class test
{
    static void sortAndPrint( Contact[] pl )  {
            // printout in table
        System.out.println( "----- Sorted by: " + Contact.getSortByName() );
        Arrays.sort( pl );
        System.out.printf( Contact.format() );
        System.out.println();
        for( Contact cnt : pl ) {
            System.out.println( Contact.format( cnt ) );
        }
    }
    public static void main(String[] args) {
        try {
            Contact[] pl = new Contact[4];
            pl[0] = new Contact( "Joahn|1234567|9876543||joahn@gmail.com||" );
            pl[1] = new Contact( "Ann|2345678|8765432||nann@gmail.com||" );
            pl[2] = new Contact( "Mary", "3456789", "7654321", "", "mary@gmail.com" );
            pl[3] = new Contact( "Empty|0000000|||||" );
            Contact.setSortBy(0);
            sortAndPrint( pl );
            Contact.setSortBy(1);
            sortAndPrint( pl );
            Contact.setSortBy(2);
            sortAndPrint( pl );
            Contact.setSortBy(4);
            sortAndPrint( pl );
            // exception test:
            Contact n = new Contact("Test exception object");
        }
        catch ( Exception e ) {
            System.out.println( "Exception: " + e );
        }
    }
}
