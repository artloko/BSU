/*
—оздать программу, котора€ копирует текст из стандартного входного потока
в выходной поток, удал€€ в каждой строке лидирующие и завершающие пробелы,
символы табул€ции не удал€ть.
*/

import java.util.Scanner;

class trim {

    public static void main( String[] args ) {
        Scanner in = new Scanner( System.in );
        while ( in.hasNextLine() ) {
            String s = in.nextLine();
            int n = 0, m = s.length() - 1;
            while ( n <= m && s.charAt( n ) == ' ' ) {
                n++;
            }
            while ( m > 0 && m >= n && s.charAt( m ) == ' ' ) {
                m--;
            }
            /* by a char output */
            while ( n <= m ) {
                System.out.print( s.charAt( n ));
                n++;
            }
            System.out.println();
            /*                                      */
            /*  output using substring:             * /
            if ( n <= m ) {
                System.out.print( s.substring( n, m + 1 ));
            }
            System.out.println();
            / *                                      */
            in.close();
        }
    }
}
