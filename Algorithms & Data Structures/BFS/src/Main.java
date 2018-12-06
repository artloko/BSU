import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        PrintWriter writer = new PrintWriter(new File("output.txt"));
        int n = scanner.nextInt();
        boolean[] visit = new boolean[n];
        int[] met = new int[n];
        int counter = 0, unvisited;
        int[][] matrix = new int[n][n];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                matrix[i][j] = scanner.nextInt();
        while (findUnvisited(visit) != -1){
            unvisited = findUnvisited(visit);
            ((LinkedList<Integer>) queue).push(unvisited);
            met[unvisited] = ++counter;
            visit[unvisited] = true;
            while (!queue.isEmpty()){
                int current = queue.poll();
                for(int i = 0; i < n; i++){
                    if (matrix[current][i] == 1 && !visit[i]) {
                        queue.add(i);
                        visit[i] = true;
                        met[i] = ++counter;
                    }
                }
            }
        }
        for(int i = 0; i < n; i++)
            writer.print(met[i] + " ");
        writer.close();
    }

    private static Integer findUnvisited(boolean[] visit){
        for(int i = 0; i < visit.length; i++)
            if (!visit[i])
                return i;
        return -1;
    }
}
