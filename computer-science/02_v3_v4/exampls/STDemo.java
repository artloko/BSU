import java.util.StringTokenizer;

class STDemo {
    static String in = "Name: John Smith\n" +
                       "Company: Global Post Inc\n" +
		       "Address: 23, 5-th Avenue, NY, 12345\n";
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer( in, ":\n" );
        System.out.print( "---Source string:\n" + in );
        System.out.print( "---Result:\n" );
	while ( st.hasMoreTokens()) {
            String key = st.nextToken();
            String val = st.nextToken();
            System.out.println( "Key = " + key + "; Value = " + val );
        } 
    }
}
/* Program output

---Source string:
Name: John Smith
Company: Global Post Inc
Address: 23, 5-th Avenue, NY, 12345
---Result:
Key = Name; Value =  John Smith
Key = Company; Value =  Global Post Inc
Key = Address; Value =  23, 5-th Avenue, NY, 12345

*/