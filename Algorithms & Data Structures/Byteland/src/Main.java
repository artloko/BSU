import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("input.txt");
        File output = new File("output.txt");
        Scanner scanner = new Scanner(input);
        PrintWriter writer = new PrintWriter(output);
        int n = 0, m = 0;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] prime = new int[n];
        for(int i = 0; i < n; i++){
            if (scanner.hasNext()){
                prime[i] = scanner.nextInt();
            }
        }
        int[][] data = new int[n][n];
        for(int i = 0; i < n; i++){
            data[i][i] = 0;
        }
        for(int p = 1; p < n; p++){
            for(int i = 0; i < n - p; i++){
                int j = i + p;
                int currSum = 0;
                for(int k = i; k < j; k++){
                    currSum += prime[k];
                }
                data[i][j] = data[i][j - 1] + prime[j] * currSum;
            }
        }

        int[][] f = new int[n][m + 1];

        int b = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m + 1; j++){
                f[i][j] = Integer.MAX_VALUE;
            }
        }

        while (b <= m){
            f[b][b] = 0;
            b++;
        }

        for(int q = 0; q < n; q++){
            f[q][0] = data[0][q];
        }

        for(int j = 0; j < m; j++){
            for(int i = j; i < n; i++){
                for(int k = 1; k < n - i; k++){
                    if (f[i + k][j + 1] > f[i][j] + data[i + 1][i + k])
                        f[i + k][j + 1] = f[i][j] + data[i + 1][i + k];
                }
            }
        }


        writer.print(f[n - 1][m]);
        writer.close();

    }
}
