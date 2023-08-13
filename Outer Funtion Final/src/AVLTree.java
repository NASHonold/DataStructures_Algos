import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AVLTree<T extends Comparable<? super T>> {

	private Node root;
	private int size;

	// Do Not Modify
	public boolean isEmpty() {
		return size == 0;
	}

	// Do Not Modify
	public boolean add(T item) {
		boolean added = false;
		if (isEmpty()) {
			root = new Node(item);
			size += 1;
			added = true;
		} else {
			added = add(null, root, item);
		}

		return added;
	}

	// Do Not Modify
	private boolean add(Node parent, Node current, T data) {
		boolean added = false;
		if (current == null) {
			int result = data.compareTo(parent.data);

			if (result < 0) {
				parent.left = new Node(data);
			} else {
				parent.right = new Node(data);
			}
			size += 1;
			return true;
		} else if (data.compareTo(current.data) < 0) {
			added = add(current, current.left, data);
		} else if (data.compareTo(current.data) > 0) {
			added = add(current, current.right, data);
		} else {
			return false;
		}

		fixHeight(current);

		if (added) {
			rebalance(parent, current);
		}

		return added;
	}

	// Do Not Modify
	private int getHeight(Node node) {
		if (node == null) {
			return -1;
		}

		return Math.max(node.leftHeight, node.rightHeight);
	}

	// Do Not Modify
	private void fixHeight(Node node) {
		if (node != null) {
			node.leftHeight = getHeight(node.left) + 1;
			node.rightHeight = getHeight(node.right) + 1;
		}
	}

	// Do Not Modify
	private void rebalance(Node parent, Node node) {
		if (node == null) {
			return;
		}
		// Left
		if (balance(node) > 1) {
			// Handle Case III
			if (balance(node.left) < 0) {
				// leftrotation
				node.left = leftRotation(node.left);
			}
			// right rotation
			if (parent == null) {
				root = rightRotation(node);
			} else if (parent.left == node) {
				parent.left = rightRotation(node);
			} else {
				parent.right = rightRotation(node);
			}
			// Right
		} else if (balance(node) < -1) {
			// Handle Case IV
			if (balance(node.right) > 0) {
				node.right = rightRotation(node.right);
			}

			// left rotation
			if (parent == null) {
				root = leftRotation(node);
			} else if (parent.left == node) {
				parent.left = leftRotation(node);
			} else {
				parent.right = leftRotation(node);
			}
		}
	}

	// Do Not Modify
	private int balance(Node node) {
		int diff = node.leftHeight - node.rightHeight;
		return diff;
	}

	// Do Not Modify
	private Node rightRotation(Node n) {
		Node c = n.left;
		Node t2 = c.right;

		c.right = n;
		n.left = t2;

		fixHeight(n);
		fixHeight(c);

		return c;
	}

	// Do Not Modify
	private Node leftRotation(Node n) {
		Node c = n.right;
		Node t2 = c.left;

		c.left = n;
		n.right = t2;

		fixHeight(n);
		fixHeight(c);

		return c;
	}

	/*********** STUDENT CODE ******************/
	// Nash Honold
	// 12/09/21

	// Final Part 2 Question.
	public Set<T> outer() {

		Node parent = this.root;
		HashSet<T> results = new HashSet<T>();
		boolean rentLeftExt = true;
		boolean rentRightExt = true;

		if (parent == null) {
			return results;
		} else {
			results.add(parent.data);
			if (parent.left != null) {
				outerHelper(parent.left, results, rentLeftExt, false, true, false);
			}
			if (parent.right != null) {
				outerHelper(parent.right, results, false, rentRightExt, false, true);
			}
		}
		return results;
	}

	/**
	 * Recursive function called in outer() that modifies the Hashset when the node
	 * meets outer criteria.
	 * 
	 * @param parent
	 * @param results
	 * @param rentLeftExt
	 * @param rentRightExt
	 * @param amILeft
	 * @param amIRight
	 */
	private void outerHelper(Node parent, HashSet<T> results, boolean rentLeftExt, boolean rentRightExt,
			boolean amILeft,
			boolean amIRight) {

		boolean isOnlyChild = (parent.left != null ^ parent.right != null);

		if (isOnlyChild) {// a node with only one child is considered outer so needs to be added
			results.add(parent.data);
		}
		if (parent.left == null && parent.right == null) {// this is a leaf node and needs to be added
			results.add(parent.data);
		}
		if (amILeft) {
			if (rentLeftExt) {// this means the current node is also an extreme left
				results.add(parent.data);
				rentRightExt = false;
				if (parent.left != null) {
					outerHelper(parent.left, results, rentLeftExt, rentRightExt, true, false);
				}
				if (parent.right != null) {
					outerHelper(parent.right, results, false, rentRightExt, false, true);
				}
			} else {// this node is not extreme and none of its children can be
				if (parent.left != null) {
					outerHelper(parent.left, results, false, false, true, false);
				}
				if (parent.right != null) {
					outerHelper(parent.right, results, false, false, false, true);
				}

			}

		} else if (amIRight) {
			if (rentRightExt) {// this means the current node is also an extreme right
				results.add(parent.data);
				rentLeftExt = false;
				if (parent.left != null) {// the next node and all its children cannot be extreme
					outerHelper(parent.left, results, rentLeftExt, false, true, false);
				}
				if (parent.right != null) {// this next node will be extreme
					outerHelper(parent.right, results, rentLeftExt, rentRightExt, false, true);
				}
			} else {
				// this node is not extreme and none of its children can be
				if (parent.left != null) {
					outerHelper(parent.left, results, false, false, true, false);
				}
				if (parent.right != null) {
					outerHelper(parent.right, results, false, false, false, true);
				}
			}

		}

	}

	/****************************************/

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		if (!isEmpty()) {
			Queue<Node> q = new LinkedList<>();
			Node temp = root;
			do {
				sb.append(temp.data);
				if (temp.left != null) {
					q.add(temp.left);
				}
				if (temp.right != null) {
					q.add(temp.right);
				}
				if (!q.isEmpty()) {
					sb.append(", ");
					temp = q.remove();
				} else {
					break;
				}
			} while (true);
		}
		sb.append(" }");
		return sb.toString();
	}

	private class Node {
		private Node left;
		private Node right;
		private int leftHeight;
		private int rightHeight;
		private T data;

		public Node(T data) {
			this.data = data;
		}
	}

}
