/*
Создать программу печати стандартного входного потока, с заменой всех
непечатных символов на точку. Вывод осуществляется в стандартный выходной 
поток.
*/
import java.util.Scanner;

class print {

    static boolean isPrint(char c) { // equals C-function isprint(c)
        return Character.isSpaceChar(c) || Character.isLetterOrDigit(c);
    }

    public static void main( String[] args ) {
        Scanner in = new Scanner( System.in );
        while ( in.hasNextLine() ) {
            String s = in.nextLine();
            for ( int i = 0; i < s.length(); i++ ) {
                char c = s.charAt( i );
                System.out.print( isPrint(c) ? c : '.' );
            }
            System.out.println();
        }
        in.close();
    }
}

