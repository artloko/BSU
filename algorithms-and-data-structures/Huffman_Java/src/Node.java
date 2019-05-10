import java.math.BigInteger;

public class Node {
    private int depth;
    private BigInteger frequency;
    private Node leftSon;
    private Node rightSon;
    public Node(BigInteger frequency){
        this.leftSon = this.rightSon = null;
        this.frequency = frequency;
        this.depth = 0;
    }
    public Node(Node leftSon, Node rightSon){
        this.leftSon = leftSon;
        this.rightSon = rightSon;
        this.frequency = leftSon.frequency.add(rightSon.frequency);
        this.depth = 0;
    }
    public Node getLeftSon() { return leftSon; }
    public Node getRightSon() { return rightSon; }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public BigInteger getFrequency() {
        return frequency;
    }
    public int getDepth() {
        return depth;
    }
}