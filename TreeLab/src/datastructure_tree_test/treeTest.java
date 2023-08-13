package datastructure_tree_test;
import datastructure_tree.BinaryTree;


public class treeTest {

    public static void main(String[] args) {
        BinaryTree<Character> bt = new BinaryTree<>();
        for(char c = 'A'; c <= 'Z'; c++){
            bt.add(c);

        }
        System.out.println("Level Order");
    
        System.out.println(bt);
        System.out.println("Preorder");
        System.out.println(bt.preorderToSting());
        System.out.println("Inorder");
        System.out.println(bt.inorderToString());
        System.out.println("Postorder");
        System.out.println(bt.postorderToString());
        System.out.println(bt.getHeight());
        System.out.println(bt.getHeight());
        System.out.println(bt.getHeight('B'));
        

        BinaryTree<Integer> btNew = new BinaryTree<>();
        btNew.add(10);
        btNew.add(9);
        btNew.add(11);
        btNew.add(8);
        btNew.add(12);
        btNew.add(7);
        btNew.add(13);
        btNew.add(6);
        btNew.add(14);
        btNew.add(5);
        btNew.add(15);
        System.out.println(btNew);
        btNew.remove(11);
        System.out.println(btNew);

     
    
}}
