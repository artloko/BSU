import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static int[] b = null;
    private static int[] result = null;
    private static int binarySearch(int value){
        int left = 0;
        int right = b.length;
        while(left != right){
            int middle = (left + right) / 2;
            if (result[middle] <= value)
                left = middle + 1;
            else
                right = middle;
        }
        return right;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        b = new int[m];
        HashMap<Integer, Integer> hashMap = new HashMap<>(n);
        for(int i = 0; i < n; i++)
            hashMap.put(scanner.nextInt(), i);
        for(int i = 0; i < m; i++){
            Integer idx = hashMap.remove(scanner.nextInt());
            if (idx != null)
                b[i] = idx;
            else
                b[i] = -1;
        }
        result = new int[m + 1];
        result[0] = -1;
        for(int i = 1; i < m + 1; i++)
            result[i] = Integer.MAX_VALUE;
        int idx, max = 0;
        for(int i = 0; i < m; i++){
            if (b[i] >= 0) {
                idx = binarySearch(b[i]);
                if (result[idx - 1] < b[i] && result[idx] > b[i]) {
                    result[idx] = b[i];
                    if (idx > max)
                        max = idx;
                }
            }
        }BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        writer.write(String.valueOf(max));
        writer.close();
    }
}