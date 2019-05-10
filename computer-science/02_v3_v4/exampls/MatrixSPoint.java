/*

Найти количество всех седловых точек матрицы. (Матрица А имеет седловую
точку Aij, если Aij является минимальным элементом в i-й строке и
максимальным в j-м столбце).

Ввести с консоли n - размерность матрицы a[n][n]. Задать значения элементов
матрицы в интервале значений от -n до n с помощью датчика случайных чисел.
Учесть, что решение может отсутствовать. В этом случае вывести на
экран сообщение, по какой причине оно отсутствует. 

При решении предполагаем единственный минимум в строке.

*/

import java.util.Scanner;
import java.util.Random;
import java.util.Date;

public class MatrixSPoint
{
    public static void main(String args[])
    {
        System.out.print("Enter n: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        if ( n <= 1 ) {
            System.err.println("Invalid n value, require n > 1");
            System.exit( 1 );
        }
        Random rnd = new Random( (new Date()).getTime() );
        int [][] arr = new int [n][n];
        System.out.println("Source values: ");
        for( int i = 0; i < n; i++ ) {
            for( int j = 0; j < n; j++ ) {
                arr[i][j] = rnd.nextInt() % ( n + 1 );
                System.out.print( arr[i][j] + " " );
            }
            System.out.println();
        }
        int[][] mins = new int [n][2];
        for( int i = 0; i < n; i++) {
            mins[i][0] = arr[i][0];
            mins[i][1] = 0;
            for( int j = 1; j < n; j++) {
                if ( arr[i][j] < mins[i][0] ) {
                    mins[i][0] = arr[i][j];
                    mins[i][1] = j;
                }
            }
        }
	int counter = n;
        for( int i = 0; i < n; i++) {
            int m = mins[i][1];
            for( int j = 0; j < n; j++) {
                if( arr[j][m] > mins[i][0]) {
                    counter--;
                    break;
                }
            }
        }
        System.out.println("Answer: " + counter );
        System.exit(0);
    }
}
