import java.util.Random;

public class AVLTreeTest {
	
	public static void main(String args[]) {
		AVLTree<Integer> avlTree = new AVLTree<>();
		AVLTree<Integer> myTree = new AVLTree<>();
		Random rand = new Random();
		for(int i = 1; i <= 16; i++) {
			avlTree.add(i);
		}

		for (int i = 0; i < 101; i++) {
			myTree.add(rand.nextInt(100));
		}

		System.out.println(avlTree);
		System.out.println(avlTree.getHeight());
		System.out.println(myTree);
		System.out.println(myTree.getHeight());
	}
}
