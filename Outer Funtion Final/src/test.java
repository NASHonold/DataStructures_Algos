import java.util.HashSet;

public class test {
    public static void main(String[] args) throws Exception {
        // this is my own test to check for random cases

        AVLTree tree = new AVLTree<>();
        tree.add(8);
        tree.add(10);
        System.out.println(tree.toString());

        tree.outer();
        System.out.println();

    }
}
