/*

В матрице найти минимальный элемент и переместить его на место заданного
элемента путем перестановки строк и столбцов.

Ввести с консоли n - размерность матрицы a[n][n]. Задать значения элементов
матрицы в интервале значений от -n до n с помощью датчика случайных чисел.
Если решение отсутствует - вывести на экран сообщение по какой причине. 

----------------------------
*/

import java.util.Scanner;
import java.util.Random;

public class MatrixMin {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = in.nextInt();
        if ( n <= 1 ) {
            System.err.println("Invalid n value (require: n > 1");
            System.exit( 1 );
        }
        int[][] a = new int [n][n];
        Random rnd = new Random();
        rnd.setSeed( System.currentTimeMillis() );

        int min = n;
        int indexI = 0, indexJ = 0;

        System.out.println("Source values: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = rnd.nextInt();
                a[i][j] = temp % (n + 1);
                System.out.print(a[i][j] + "  ");
                if (a[i][j] <= min) {
                    min = a[i][j];
                    indexI = i;
                    indexJ = j;
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Min ["+indexI+"]["+indexJ+"]: " + min );
        System.out.println();

        System.out.print("Enter new indexI = ");
        int newI = in.nextInt();
        System.out.print("Enter new indexJ = ");
        int newJ = in.nextInt();
        in.close();

        if (newI >= n || newI < 0 || newJ < 0 || newJ >=n ) {
            System.err.println("Index is out of range");
            System.exit(1);
        }

        if (indexJ != newJ) {
            for (int i = 0; i < n; i++) {
                int temp = a[i][indexJ];
                a[i][indexJ] = a[i][newJ];
                a[i][newJ] = temp;
            }
        }
        if (indexI != newI) {
            int [] temp = a[newI];
            a[newI] = a[indexI];
            a[indexI] = temp;
        }

        System.out.println(); System.out.println("Processed values: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j<n; j++) {
                System.out.print(a[i][j] + "  ");
            }
            System.out.println();
        }
        System.exit(0);
    }
}

