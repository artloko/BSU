import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main implements Runnable {
    private static int[] parents;
    private static long[] values;
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));
            int n = scanner.nextInt();
            parents = new int[n];
            values = new long[n];
            for (int i = 0; i < n; i++)
                parents[i] = i;
            while (scanner.hasNext()) {
                switch (scanner.next()) {
                    case "E": {
                        int currNode = Integer.parseInt(scanner.next()) - 1;
                        long height = getHeight(currNode, 0);
                        commandE(currNode, 0, height);
                        writer.println(values[currNode]);
                        writer.flush();
                        break;
                    }
                    case "I": {
                        commandI(Integer.parseInt(scanner.next()) - 1, Integer.parseInt(scanner.next()) - 1);
                        break;
                    }
                    case "O": {
                        return;
                    }
                }
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static long getHeight(int node, int result){
        if (parents[node] == node)
            return result;
        result += values[node];
        return getHeight(parents[node], result);
    }

    private static int commandE(int node, long result, long height){
        if (parents[node] == node)
            return node;
        long curr = values[node];
        values[node] = height - result;
        result += curr;
        return parents[node] = commandE(parents[node], result, height);
    }

    private static void commandI(int from, int to){
        parents[from] = to;
        values[from] = Math.abs(from - to) % 1000;
    }
}