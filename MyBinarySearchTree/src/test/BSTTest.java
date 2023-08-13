package test;

import binarySearchTree.binarySearchTree;

public class BSTTest {

    public static void main(String[] args) {
        binarySearchTree<Integer> bst = new binarySearchTree<>();

        bst.add(150);
        bst.add(90);
        bst.add(200);
        bst.add(175);
        bst.add(225);
        bst.add(165);
        bst.add(185);
        bst.add(160);
        bst.add(170);

        System.out.println(bst);

        bst.remove(160);
        bst.remove(165);

        System.out.println(bst.levelOrderToString());

        System.out.println("Height of tree is: " + bst.getHeight());
        bst.remove(170);
        bst.remove(185);
        System.out.println("Height of tree is: " + bst.getHeight());
        bst.remove(200);
        System.out.println(bst);
    }

}
