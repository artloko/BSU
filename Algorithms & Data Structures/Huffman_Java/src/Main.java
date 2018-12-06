import java.io.*;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static BigInteger result = BigInteger.ZERO;
    private static void rightLeft(Node node, int depth){
        if (node != null){
            depth++;
            rightLeft(node.getLeftSon(), depth);
            rightLeft(node.getRightSon(), depth);
            if (node.getLeftSon() == null && node.getRightSon() == null){
                node.setDepth(depth);
                result = result.add(BigInteger.valueOf(node.getDepth()).multiply(node.getFrequency()));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new FileReader("huffman.in")));
        PrintWriter writer = new PrintWriter(new FileWriter("huffman.out"));
        int n = 0;
        if (tokenizer.nextToken() == StreamTokenizer.TT_NUMBER)
            n = (int) tokenizer.nval;
        Queue<Node> primeQueue = new LinkedList<>();
        Queue<Node> tempQueue = new LinkedList<>();
        for(int i = 0; i < n; i++)
            if (tokenizer.nextToken() == StreamTokenizer.TT_NUMBER)
                primeQueue.add(new Node(BigInteger.valueOf((int)tokenizer.nval)));
        Node leftSon = primeQueue.poll();
        Node rightSon = primeQueue.poll();
        Node newNode = new Node(leftSon, rightSon);
        tempQueue.add(newNode);
        while (primeQueue.size() > 0){
            if (!primeQueue.isEmpty() && primeQueue.peek().getFrequency().compareTo(tempQueue.peek().getFrequency()) == -1)
                leftSon = primeQueue.poll();
            else
                leftSon = tempQueue.poll();
            if (tempQueue.isEmpty())
                rightSon = primeQueue.poll();
            else if (!primeQueue.isEmpty() && primeQueue.peek().getFrequency().compareTo(tempQueue.peek().getFrequency()) == -1)
                rightSon = primeQueue.poll();
            else
                rightSon = tempQueue.poll();
            newNode = new Node(leftSon, rightSon);
            tempQueue.add(newNode);
        }
        while (tempQueue.size() != 1){
            leftSon = tempQueue.poll();
            rightSon = tempQueue.poll();
            newNode = new Node(leftSon, rightSon);
            tempQueue.add(newNode);
        }
        rightLeft(tempQueue.peek(), -1);
        writer.println(result);
        writer.close();
    }
}