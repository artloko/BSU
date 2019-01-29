import java.util.Random;
import java.util.Scanner;

public class FourthTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**************");
        System.out.println("task 4");
        System.out.println("(Using left shifting)");
        System.out.println("**************");
        System.out.println();
        fourthVar(scanner, args);
    }

    static void fourthVar(Scanner scanner, String[] args){
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        if (args.length < 1){
            System.err.println("Invalid number of arguments");
            System.exit(1);
        }
        if (n < 2) {
            System.err.println("Invalid n value (require: n > 1)");
            System.exit( 1 );
        }
        int k = Integer.parseInt(args[0]);
        if (k < 1 || k >= n) {
            System.err.println("Invalid k value (require: k > 0 && k < n)");
            System.exit( 1 );
        }
        int[][] matrix = new int [n][n];
        Random rnd = new Random();
        rnd.setSeed( System.currentTimeMillis() );

        System.out.println("Source values: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = rnd.nextInt();
                matrix[i][j] = temp % (n + 1);
                if (matrix[i][j] >= 0){
                    System.out.print(" ");
                }
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();


        System.out.println("Where to shift\n (1) - Left\n (2) - Right\n (3) - Up\n (4) - Down");
        System.out.print("Enter where to shit: ");
        int destination = scanner.nextInt();
        switch (destination){
            case 1:
                shiftLeft(matrix, k);
            case 2:
                shiftRight(matrix, k);
            case 3:
                shiftUp(matrix, k);
            case 4:
                shiftDown(matrix, k);
                default: break;
        }

        System.out.println("Shifted matrix: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] >= 0){
                    System.out.print(" ");
                }
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private static void shiftLeft(int[][] m, int steps) {
        for(int j = 0; j < m.length - steps; j++){
            for(int i = 0; i < m.length; i++){
                int temp = m[i][j];
                m[i][j] = m[i][j + steps];
                m[i][j + steps] = temp;
            }
        }
    }

    private static void shiftRight(int[][] m, int steps) {
        for(int j = m.length - 1; j >= steps; j--){
            for(int i = 0; i < m.length; i++){
                int temp = m[i][j];
                m[i][j] = m[i][j - steps];
                m[i][j - steps] = temp;
            }
        }
    }

    private static void shiftUp(int[][] m, int steps) {
        for(int i = 0; i < m.length - steps; i++){
            for(int j = 0; j < m.length; j++){
                int temp = m[i][j];
                m[i][j] = m[i + steps][j];
                m[i + steps][j] = temp;
            }
        }
    }

    private static void shiftDown(int[][] m, int steps) {
        for(int i = m.length - 1; i >= steps; i--) {
            for (int j = 0; j < m.length; j++) {
                int temp = m[i][j];
                m[i][j] = m[i - steps][j];
                m[i - steps][j] = temp;
            }
        }
    }



}