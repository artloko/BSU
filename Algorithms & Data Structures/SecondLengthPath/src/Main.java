import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new FileReader("input.in"));
        PrintWriter writer = new PrintWriter(new File("output.out"));
        String currString = scanner.readLine();
        StringTokenizer tokenizer = new StringTokenizer(currString);
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        LinkedList<MinePair>[] list = new LinkedList[n];
        boolean[] firstBlocking = new boolean[n];
        boolean[] secondBlocking = new boolean[n];
        Pair<Integer, Boolean>[] firstPath = new Pair[n];
        Pair<Integer, Boolean>[] secondPath = new Pair[n];
        long distance = 0;
        for(int i = 0; i < n; i++)
            list[i] = new LinkedList<>();
        PriorityQueue<MinePair> queue = new PriorityQueue<>(n);
        for(int i = 0; i < m; i++) {
            currString = scanner.readLine();
            tokenizer = new StringTokenizer(currString);
            int from = Integer.parseInt(tokenizer.nextToken()) - 1;
            int to = Integer.parseInt(tokenizer.nextToken()) - 1;
            int value = Integer.parseInt(tokenizer.nextToken());
            list[from].add(new MinePair(value, to, -1, false));
        }
        currString = scanner.readLine();
        tokenizer = new StringTokenizer(currString);
        int from = Integer.parseInt(tokenizer.nextToken()) - 1;
        int to = Integer.parseInt(tokenizer.nextToken()) - 1;
        queue.add(new MinePair(0, from, -1, false));                                  //key - distance, value - nod
        MinePair currNode;
        while (!queue.isEmpty()){
            currNode = queue.poll();
            long currNodeKey = currNode.getKey();
            int currNodeValue = currNode.getValue();
            if (currNodeValue == to && firstBlocking[currNodeValue]) {
                if (!currNode.isSecond()) {
                    secondPath[currNodeValue] = new Pair<>(currNode.getParent(), false);
                } else {
                    secondPath[currNodeValue] = new Pair<>(currNode.getParent(), true);
                }
                distance = currNodeKey;
                break;
            }
            if (secondBlocking[currNodeValue])
                continue;

            if (!firstBlocking[currNodeValue]) {
                firstBlocking[currNodeValue] = true;
                firstPath[currNodeValue] = new Pair<>(currNode.getParent(), false);
            } else {
                secondBlocking[currNodeValue] = true;
                if (currNode.isSecond()){
                    secondPath[currNodeValue] = new Pair<>(currNode.getParent(), true);
                } else {
                    secondPath[currNodeValue] = new Pair<>(currNode.getParent(), false);
                }
            }
            ListIterator<MinePair> iterator = list[currNodeValue].listIterator(0);
            while(iterator.hasNext()){
                MinePair currInListNode = iterator.next();
                boolean isSecond = secondBlocking[currNodeValue];
                queue.add(new MinePair(currNodeKey + currInListNode.getKey(), currInListNode.getValue(), currNodeValue, isSecond));
            }
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        int b = to;
        boolean start = false;
        while (b != -1){
            arrayList.add(b + 1);
            if (!start){
                if (!secondPath[b].getValue())
                    start = true;
                b = secondPath[b].getKey();
            } else {
                b = firstPath[b].getKey();
            }
        }

        writer.println(distance);
        for(int i = arrayList.size() - 1; i >= 0; i--)
            writer.print(arrayList.get(i) + " ");
        writer.close();
    }
}

class MinePair implements Comparable{
    long key;
    int value;
    int parent;
    boolean isSecond;

    MinePair(long key, int value, int parent, boolean isSecond){
        this.key = key;
        this.value = value;
        this.parent = parent;
        this.isSecond = isSecond;
    }

    public boolean isSecond() {
        return isSecond;
    }

    public int getValue() {
        return value;
    }

    public long getKey() {
        return key;
    }

    public int getParent() {
        return parent;
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(this.key, ((MinePair)o).key);
    }
}