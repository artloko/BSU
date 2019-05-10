import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter(new File("output.txt"));
        String currString = scanner.readLine();
        StringTokenizer tokenizer = new StringTokenizer(currString);
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        LinkedList<MinePair>[] list = new LinkedList[n];
        boolean[] blocked = new boolean[n];
        long distance = 0;
        for(int i = 0; i < n; i++) {
            list[i] = new LinkedList<>();
        }
        PriorityQueue<MinePair> queue = new PriorityQueue<>(n);
        queue.add(new MinePair(0, 0));                                  //key - distance, value - node
        for(int i = 0; i < m; i++) {
            currString = scanner.readLine();
            tokenizer = new StringTokenizer(currString);
            int from = Integer.parseInt(tokenizer.nextToken()) - 1;
            int to = Integer.parseInt(tokenizer.nextToken()) - 1;
            int value = Integer.parseInt(tokenizer.nextToken());
            list[from].add(new MinePair(value, to));
            list[to].add(new MinePair(value, from));
        }
        MinePair currNode;
        while (!queue.isEmpty()){
            currNode = queue.poll();
            long currNodeKey = currNode.getKey();
            int currNodeValue = currNode.getValue();
            if (blocked[currNodeValue])
                continue;
            if (currNodeValue == n - 1) {
                distance = currNodeKey;
                break;
            }
            blocked[currNodeValue] = true;
            ListIterator<MinePair> iterator = list[currNodeValue].listIterator(0);
            while(iterator.hasNext()){
                MinePair currInListNode = iterator.next();
                queue.add(new MinePair(currNodeKey + currInListNode.getKey(), currInListNode.getValue()));
            }
        }
        writer.println(distance);
        writer.close();
    }
}

class MinePair implements Comparable{
    long key;
    int value;

    MinePair(long key, int value){
        this.key = key;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public long getKey() {
        return key;
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(this.key, ((MinePair)o).key);
    }
}