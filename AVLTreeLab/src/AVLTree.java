import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<T extends Comparable<? super T>> {

	private Node root;
	private int size;

	public void clear() {
		root = null;
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

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

		return added;
	}

	public boolean remove(T item) {
		if (isEmpty()) {
			return false;
		} else if (size == 1 && root.data.equals(item)) {
			clear();
			return true;
		} else if (removeTraversal(null, root, item)) {
			size -= 1;
			return true;
		} else {
			return false;
		}
	}

	private boolean removeTraversal(Node parent, Node current, T data) {
		boolean removed = true;
		if (current == null) {
			return false;
		} else if (data.compareTo(current.data) < 0) {
			removed = removeTraversal(current, current.left, data);
		} else if (data.compareTo(current.data) > 0) {
			removed = removeTraversal(current, current.right, data);
		} else {
			removeNode(parent, current);
		}

		fixHeight(current);
		if (removed) {
			rebalance(parent, current);
		}
		return removed;
	}

	private void removeNode(Node parent, Node current) {
		if (current.left == null && current.right == null) {
			// Remove leaf node
			removeCase1(parent, current);
		} else if (current.left != null && current.right == null) {
			// Remove node with no right child
			removeCase2(parent, current);
		} else if (current.left == null && current.right != null) {
			// Remove node with no left child
			removeCase3(parent, current);
		} else {
			// Remove node with both children
			removeCase4(parent, current);
		}

		fixHeight(parent);

	}

	private void removeCase1(Node parent, Node current) {
		if (parent == null) {
			root = null;
		} else if (parent.left == current) {
			parent.left = null;
		} else {
			parent.right = null;
		}
	}

	private void removeCase2(Node parent, Node current) {
		if (parent == null) {
			root = root.left;
		} else if (parent.left == current) {
			parent.left = current.left;
		} else {
			parent.right = current.left;
		}
		current.left = null;
	}

	private void removeCase3(Node parent, Node current) {
		if (parent == null) {
			root = root.right;
		} else if (parent.left == current) {
			parent.left = current.right;
		} else {
			parent.right = current.right;
		}
		current.right = null;
	}

	private void removeCase4(Node parent, Node current) {
		Node leftMost = current.right;
		Node leftMostParent = current;

		while (leftMost.left != null) {
			leftMostParent = leftMost;
			leftMost = leftMost.left;
		}

		current.data = leftMost.data;

		removeNode(leftMostParent, leftMost);
		rebalance(current, current.right);
	}

	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Node node) {
		if (node == null) {
			return -1;
		}

		return Math.max(node.leftHeight, node.rightHeight);
	}

	private void fixHeight(Node node) {
		if (node != null) {
			node.leftHeight = getHeight(node.left) + 1;
			node.rightHeight = getHeight(node.right) + 1;
		}
	}

	private int balance(Node node) {
		// if the balance is greater than 1 that the imbalance is in the left subtree
		// if the balance is less that -1 then the imbalance is in the right subtree
		return node.leftHeight - node.rightHeight;
	}

	private Node rightRotation(Node n) {
		Node c = n.left;
		Node t2 = c.right;

		c.right = n;
		n.left = t2;
		fixHeight(n);
		fixHeight(c);
		return c;

	}

	private Node leftRotation(Node n) {
		Node c = n.right;
		Node t2 = c.left;

		c.left = n;
		n.right = t2;

		fixHeight(n);
		fixHeight(c);
		return c;
	}

	private void rebalance(Node parent, Node node) {
		if (node == null) {
			return;
		}
		// imblance in left subtree
		if (balance(node) > 1) {
			// handles case 3
			if (balance(node.left) < 0) {
				// left rotation
				node.left = leftRotation(node.left);
			}

			if (parent == null) {
				root = rightRotation(node);
			} else if (parent.left == node) {
				parent.left = rightRotation(node);
			} else {
				parent.right = rightRotation(node);
			}
			// imbalance in right subtree
		} else if (balance(node) < -1) {
			// handle case 4
			if (balance(node.right) > 0) {
				node.right = rightRotation(node.right);
			}

			if (parent == null) {
				root = leftRotation(node);
			} else if (parent.left == node) {
				parent.left = leftRotation(node);
			} else {
				parent.right = leftRotation(node);
			}
		}
	}

	@Override
	public String toString() {
		return levelOrderString();
	}

	public String levelOrderString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{");

		if (!isEmpty()) {
			Queue<Node> q = new LinkedList<>();

			q.add(root);
			Node current = null;

			while (!q.isEmpty()) {

				current = q.remove();
				if (current.left != null) {
					q.add(current.left);
				}

				if (current.right != null) {
					q.add(current.right);
				}

				sb.append(current);
				if (!q.isEmpty()) {
					sb.append(", ");
				}
			}

		}

		sb.append("}");

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

		public String toString() {
			String formatter = "(%s | %s | %s)";
			String lefString = left != null ? left.data.toString() : "";
			String rightString = right != null ? right.data.toString() : "";
			return String.format(formatter, data.toString(), lefString, rightString);
		}
	}
}
