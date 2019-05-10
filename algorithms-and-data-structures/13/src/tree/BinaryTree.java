package tree;

import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



public class BinaryTree {
    Node root;
    List<Node> tree;
    Map<Node, List<Node>> halfWays;
    File file;
    FileWriter fileWriter;

    public Node getRoot() {
        return root;
    }

    public void close() throws IOException{
        fileWriter.close();
    }

    public BinaryTree(File file) throws IOException {
        this.file = file;
        this.tree = new ArrayList<>();
        this.halfWays = new HashMap<>();
        fileWriter = new FileWriter(this.file);
    }

    private Node addRecursive(Node current, int value){
        if (current == null){
            return new Node(value);
        }

        if (value > current.value){
            current.rightSon = addRecursive(current.rightSon, value);
        }
        else if (value < current.value){
            current.leftSon = addRecursive(current.leftSon, value);
        }
        else{
            return current;
        }

        return current;
    }

    private Node deleteRecursive(Node current, int value){
        if (current == null){
            return null;
        }

        if (value > current.value){
            current.rightSon = deleteRecursive(current.rightSon, value);
        }
        else if (value < current.value){
            current.leftSon = deleteRecursive(current.leftSon, value);
        }
        else{
            if (current.leftSon == null && current.rightSon == null){
                return null;
            }
            if (current.leftSon == null){
                return current.rightSon;
            }
            if (current.rightSon == null){
                return current.leftSon;
            }
            int substituteValue = rightDelete(current.rightSon);
            current.value = substituteValue;
            current.rightSon = deleteRecursive(current.rightSon, substituteValue);
            return current;
        }
        return current;
    }

    private int rightDelete(Node node){
        return node.leftSon == null ? node.value : rightDelete(node.leftSon);
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public void delete(int value){
        root = deleteRecursive(root, value);
    }


    public void rightLeft(Node node) throws IOException{
        if (node != null){
            fileWriter.write(node.value + "\n");
            rightLeft(node.leftSon);
            rightLeft(node.rightSon);
        }
    }

    public void setHeightAndMSL(Node node) {                             //sets height and MSL for each node
        if (node != null){
            setHeightAndMSL(node.leftSon);
            setHeightAndMSL(node.rightSon);
            if (node.leftSon == null && node.rightSon == null){
                node.height = 0;
                node.MSL = 0;
            }
            else if (node.rightSon == null){
                node.height = node.leftSon.height + 1;
                node.MSL = node.leftSon.height + 1;
            }
            else if (node.leftSon == null){
                node.height = node.rightSon.height + 1;
                node.MSL = node.rightSon.height + 1;
            }
            else{
                if (node.leftSon.height > node.rightSon.height){
                    node.height = node.leftSon.height + 1;
                }
                else {
                    node.height = node.rightSon.height + 1;
                }
                node.MSL = node.leftSon.height + node.rightSon.height + 2 - 1;
            }
            if (Node.maxNodeMSL < node.MSL){
                Node.maxNodeMSL = node.MSL;
            }
        }
    }

    public void findAllLongestWays(Node node){
        if (node != null){
            findAllLongestWays(node.leftSon);
            findAllLongestWays(node.rightSon);
            if (node.MSL == Node.maxNodeMSL){
                tree.add(node);
            }
        }
    }

    public void deleteAverageNode(){
        for (Node node: tree) {
            calculateSumForNode(node);
        }

        for (int i = 0; i < tree.size(); i++){
            if (tree.get(i).sum == Node.minSum){
                if (Node.minValueFromMinSum > tree.get(i).value){
                    Node.minValueFromMinSum = tree.get(i).value;
                }
            }
        }
        for(int i = 0; i < tree.size(); i++){
            if (tree.get(i).sum == Node.minSum && tree.get(i).value == Node.minValueFromMinSum){
                if (tree.get(i).canditate){
                    Node node = tree.get(i);
                    halfWays.get(node).remove(node.get(node.leftLast));
                    Node tempRight = findAverage(node);
                    halfWays.get(node).add(node.get(node.leftLast));
                    halfWays.get(node).remove(node.get(node.rightLast));
                    Node tempLeft = findAverage(node);
                    halfWays.get(node).add(node.get(node.rightLast));
                    if (tempRight != tempLeft){
                        Node.stop = true;
                    }
                    else{
                        halfWays.get(node).remove(node.get(node.rightLast));
                    }
                }

                if (!Node.stop && findAverage(tree.get(i)) != null){
                    delete(findAverage(tree.get(i)).value);
                }
            }
        }
    }

    private Node findAverage(Node node){
        int size = halfWays.get(node).size();
        if (size % 2 == 1)
        {
            Collections.sort(halfWays.get(node), (Node a, Node b) -> Integer.compare(a.value, b.value));
            return halfWays.get(node).get(size / 2);
        }
        return null;
    }

    private void calculateSumForNode(Node node){
        Integer leftLast = 0;
        Integer rightLast = 0;
        Integer leftBfrLast = 0;
        Integer rightBfrLast = 0;

        boolean isDeleted = false;

        Pair<Integer, Integer> leftResult = new Pair<>(0, 0);
        Pair<Integer, Integer> rightResult = new Pair<>(0, 0);

        halfWays.put(node, new ArrayList<>());
        halfWays.get(node).add(node);

        if (node.leftSon == null && node == getRoot()){
            leftBfrLast = node.value;
            rightResult = toTheLeave(node.rightSon, rightLast, rightBfrLast, rightResult, halfWays.get(node));
            rightLast = rightResult.getKey();
            node.sum = leftBfrLast + rightLast;
        }
        else if (node.rightSon == null && node == getRoot()){
            rightBfrLast = node.value;
            leftResult = toTheLeave(node.leftSon, leftLast, leftBfrLast, leftResult, halfWays.get(node));
            leftLast = leftResult.getKey();
            node.sum = rightBfrLast + leftLast;
        }
        else{
            rightResult = toTheLeave(node.rightSon, rightLast, rightBfrLast, rightResult, halfWays.get(node));
            leftResult = toTheLeave(node.leftSon, leftLast, leftBfrLast, leftResult, halfWays.get(node));
            leftBfrLast = leftResult.getValue();
            leftLast = leftResult.getKey();
            rightBfrLast = rightResult.getValue();
            rightLast = rightResult.getKey();
            if (node.rightSon.height == 0){
                rightBfrLast = node.value;
            }
            else if (node.leftSon.height == 0) {
                leftBfrLast = node.value;
            }
            node.sum = Math.min(leftBfrLast + rightLast, leftLast + rightBfrLast);

            if (!isDeleted){
                if (leftBfrLast + rightLast < rightBfrLast + leftLast){
                    halfWays.get(node).remove(node.get(leftLast));
                }
                else if (leftBfrLast + rightLast > rightBfrLast + leftLast){
                    halfWays.get(node).remove(node.get(rightLast));
                }
                else{
                    node.canditate = true;
                    node.leftLast = leftLast;
                    node.rightLast = rightLast;
                }
            }
        }

        if (Node.minSum > node.sum){
            Node.minSum = node.sum;
        }

    }

    private Pair<Integer, Integer> toTheLeave(Node node, Integer last, Integer bfrLast, Pair<Integer, Integer> result, List<Node> tree){
        if (node != null){
            tree.add(node);
            if (node.height == 0){
                last = node.value;
                return new Pair<>(last, bfrLast);
            }
            else if (node.height == 1){
                bfrLast = node.value;
            }

            if (node.leftSon != null && node.rightSon != null){
                if (node.rightSon.height > node.leftSon.height){
                    result = toTheLeave(node.rightSon, last, bfrLast, result, tree);
                }
                else{
                    result = toTheLeave(node.leftSon, last, bfrLast, result, tree);
                }
            }
            else{
                if (node.leftSon == null){
                    result = toTheLeave(node.rightSon, last, bfrLast, result, tree);
                }
                else if (node.rightSon == null){
                    result = toTheLeave(node.leftSon, last, bfrLast, result, tree);
                }
            }
        }
        return result;
    }
}