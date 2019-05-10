import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        PrintWriter writer = new PrintWriter(new File("output.txt"));
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                matrix[i][j] = scanner.nextInt();
        boolean[] visit = new boolean[n];
        int[] met = new int[n];
        for(int i = 0; i < n; i++){
            if (!visit[i])
                dfs(i, visit, met, matrix);
        }
        for(int i = 0; i < n; i++)
            writer.print(met[i] + " ");
        writer.close();
    }

    private static int currMet = 0;
    private static void dfs(int i, boolean[] visit, int[] met, int[][] matrix){
        visit[i] = true;
        met[i] = ++currMet;
        for(int j = 0; j < matrix.length; j++){
            if (matrix[i][j] == 1 && !visit[j]){
                dfs(j, visit, met, matrix);
            }
        }
    }
}
