package tree;

import java.util.Objects;

public class Node {
    static boolean stop;
    static int maxNodeMSL;
    static int minSum;
    static int minValueFromMinSum;

    int leftLast;
    int rightLast;
    boolean canditate;

    int value;
    int height;
    int MSL;
    int sum;
    Node leftSon;
    Node rightSon;

    public Node(int value){
        leftLast = -1;
        rightLast = -1;
        canditate = false;

        stop = false;
        this.value = value;
        height = -1;
        MSL = -1;
        sum = 0;
        minSum = Integer.MAX_VALUE;
        minValueFromMinSum = Integer.MAX_VALUE;
        leftSon = null;
        rightSon = null;
    }

    public Node get(int value)
    {
        if (this.value ==  value)
        {
            return this;
        }

        if (this.value >  value)
        {
            return leftSon == null ? null : leftSon.get( value );
        }
        else
        {
            return rightSon == null ? null : rightSon.get( value );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value &&
                height == node.height &&
                MSL == node.MSL &&
                Objects.equals(leftSon, node.leftSon) &&
                Objects.equals(rightSon, node.rightSon);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, height, MSL, leftSon, rightSon);
    }
}
