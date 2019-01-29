/*
—оздать программу, котора€ копирует текст из стандартного входного потока
в выходной поток, удал€€ в каждой строке лидирующие и завершающие пробелы
и специальные символы. 
*/

import java.util.Scanner;

class trims {

    public static void main( String[] args ) {
        Scanner in = new Scanner( System.in );
        while ( in.hasNextLine() ) {
            String s = in.nextLine();
            System.out.println( s.trim() );
        }
        in.close();
    }
}

