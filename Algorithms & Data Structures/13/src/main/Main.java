package main;


import tree.BinaryTree;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main implements Runnable{
    public static void main(String[] args){
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            File file = new File("tst.in");
            Scanner scanner = new Scanner(file);
            BinaryTree binaryTree = new BinaryTree(new File("tst.out"));
            while (scanner.hasNextInt()) {
                binaryTree.add(scanner.nextInt());
            }
            binaryTree.setHeightAndMSL(binaryTree.getRoot());
            binaryTree.findAllLongestWays(binaryTree.getRoot());
            binaryTree.deleteAverageNode();
            binaryTree.rightLeft(binaryTree.getRoot());
            binaryTree.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
