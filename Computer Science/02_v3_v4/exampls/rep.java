/* Создать программу rep поиска и замены подстроки в строках входного файла,
который считывается из стандартного входного потока. Искомая подстрока и строка
на которую нужно заменить, вводятся в качестве параметров в виде
последовательности символов заключённых в двойные кавычки. Например

    rep "искомая подстрока" "замена"

Строки входного файла, в которых встречается искомая подстрока, выводятся в
стандартный выходной поток с заменой всех вхождений искомой подстроки. 
Строки в которых искомая подстрока не найдена выводятся в стандартный выходной 
поток в неизменном виде
*/

import java.util.Scanner;

class rep {

    public static void main( String[] args ) {
        if ( args.length != 2 ) {
            System.err.println("Invalid number of arguments");
            System.exit(1);
        }
        Scanner in = new Scanner( System.in );
        while ( in.hasNextLine() ) {
            String s = in.nextLine();
            int idx = 0, idxFrom = 0;
            while (( idx = s.indexOf( args[0], idxFrom ))!= -1 ) {
                System.out.print( s.substring( idxFrom, idx ) + args[1] );
                idxFrom = idx + args[0].length();
            }
            if ( idxFrom == 0 )
                System.out.println( s );
            else
                System.out.println( s.substring( idxFrom ));
        }
        in.close();
        System.exit(0);
    }
}
