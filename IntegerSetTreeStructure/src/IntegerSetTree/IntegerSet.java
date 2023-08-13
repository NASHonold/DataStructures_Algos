package IntegerSetTree;

public class IntegerSet {

	private Node root;
	private int size;

	public IntegerSet() {
		clear();
	}

	public IntegerSet(int array[]) {
		for (int i : array) {
			add(i);
		}
	}

	public int magnitude() {
		return size;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean add(int item) {
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

	private boolean add(Node parent, Node current, int data) {
		boolean added = false;
		if (current == null) {
			int result = data - parent.data;

			if (result < 0) {
				parent.leftChild = new Node(data);
			} else {
				parent.rightChild = new Node(data);
			}
			size += 1;
			return true;
		} else if (data < current.data) {
			added = add(current, current.leftChild, data);
		} else if (data > current.data) {
			added = add(current, current.rightChild, data);
		} else {
			return false;
		}
		fixHeight(current);

		if (added) {
			rebalance(parent, current);
		}
		return added;
	}

	public boolean remove(int item) {
		if (isEmpty()) {
			return false;
		} else if (size == 1 && root.data == item) {
			clear();
			return true;
		} else if (removeTraversal(null, root, item)) {
			size -= 1;
			return true;
		} else {
			return false;
		}
	}

	private boolean removeTraversal(Node parent, Node current, int data) {
		boolean removed = true;
		if (current == null) {
			return false;
		} else if (data < current.data) {
			removed = removeTraversal(current, current.leftChild, data);
		} else if (data > current.data) {
			removed = removeTraversal(current, current.rightChild, data);
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
		if (current.leftChild == null && current.rightChild == null) {
			// Remove leaf node
			removeCase1(parent, current);
		} else if (current.leftChild != null && current.rightChild == null) {
			// Remove node with no right child
			removeCase2(parent, current);
		} else if (current.leftChild == null && current.rightChild != null) {
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
		} else if (parent.leftChild == current) {
			parent.leftChild = null;
		} else {
			parent.rightChild = null;
		}
	}

	private void removeCase2(Node parent, Node current) {
		if (parent == null) {
			root = root.leftChild;
		} else if (parent.leftChild == current) {
			parent.leftChild = current.leftChild;
		} else {
			parent.rightChild = current.leftChild;
		}
		current.leftChild = null;
	}

	private void removeCase3(Node parent, Node current) {
		if (parent == null) {
			root = root.rightChild;
		} else if (parent.leftChild == current) {
			parent.leftChild = current.rightChild;
		} else {
			parent.rightChild = current.rightChild;
		}
		current.rightChild = null;
	}

	private void removeCase4(Node parent, Node current) {
		Node leftMost = current.rightChild;
		Node leftMostParent = current;

		while (leftMost.leftChild != null) {
			leftMostParent = leftMost;
			leftMost = leftMost.leftChild;
		}

		current.data = leftMost.data;

		removeNode(leftMostParent, leftMost);
		rebalance(current, current.rightChild);
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
			node.leftHeight = getHeight(node.leftChild) + 1;
			node.rightHeight = getHeight(node.rightChild) + 1;
		}
	}

	private int balance(Node node) {
		// if the balance is greater than 1 that the imbalance is in the left subtree
		// if the balance is less that -1 then the imbalance is in the right subtree
		return node.leftHeight - node.rightHeight;
	}

	private Node rightRotation(Node n) {
		Node c = n.leftChild;
		Node t2 = c.rightChild;

		c.rightChild = n;
		n.leftChild = t2;
		fixHeight(n);
		fixHeight(c);
		return c;

	}

	private Node leftRotation(Node n) {
		Node c = n.rightChild;
		Node t2 = c.leftChild;

		c.leftChild = n;
		n.rightChild = t2;

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
			if (balance(node.leftChild) < 0) {
				// left rotation
				node.leftChild = leftRotation(node.leftChild);
			}

			if (parent == null) {
				root = rightRotation(node);
			} else if (parent.leftChild == node) {
				parent.leftChild = rightRotation(node);
			} else {
				parent.leftChild = rightRotation(node);
			}
			// imbalance in right subtree
		} else if (balance(node) < -1) {
			// handle case 4
			if (balance(node.rightChild) > 0) {
				node.rightChild = rightRotation(node.rightChild);
			}

			if (parent == null) {
				root = leftRotation(node);
			} else if (parent.leftChild == node) {
				parent.leftChild = leftRotation(node);
			} else {
				parent.rightChild = leftRotation(node);
			}
		}
	}

	public boolean contains(int itemToFind) {
		Node temp = this.root;
		boolean bool = containsHelper(temp, itemToFind);
		return bool;

	}

	private boolean containsHelper(Node current, int itemToFind) {
		boolean bool = false;
		boolean leftBool = false;
		boolean rightBool = false;
		if (current != null) {
			if (current.data == itemToFind) {
				bool = true;
			}
			leftBool = containsHelper(current.leftChild, itemToFind);
			rightBool = containsHelper(current.rightChild, itemToFind);
		}
		if (leftBool || rightBool || bool) {
			return true;
		} else {
			return false;
		}

	}

	public IntegerSet union(IntegerSet other) {
		IntegerSet newSet = this.copy();
		other.copyOnto(newSet);
		return newSet;
	}

	public IntegerSet intersection(IntegerSet other) {
		IntegerSet newSet = new IntegerSet();
		Node current = this.root;
		intersectionHelper(current, newSet, other);
		return newSet;
	}

	private void intersectionHelper(Node current, IntegerSet newSet, IntegerSet toScan) {
		if (current != null) {
			if (toScan.contains(current.data)) {
				newSet.add(current.data);
			}
			intersectionHelper(current.leftChild, newSet, toScan);
			intersectionHelper(current.rightChild, newSet, toScan);
		}
	}

	public IntegerSet difference(IntegerSet other) {
		IntegerSet diffSet = new IntegerSet();
		differenceHelper(this.root, other, diffSet);
		return diffSet;
	}

	private void differenceHelper(Node current, IntegerSet toCheck, IntegerSet newSet) {
		if (current != null) {
			if (!toCheck.contains(current.data)) {
				newSet.add(current.data);
			}
			differenceHelper(current.leftChild, toCheck, newSet);
			differenceHelper(current.rightChild, toCheck, newSet);
		}
	}

	public IntegerSet symmetricDifference(IntegerSet other) {
		IntegerSet symDif = other.copy();
		symDifferenceHelper(this.root, symDif);
		return symDif;
	}

	private void symDifferenceHelper(Node current, IntegerSet newSet) {
		if (current != null) {
			if (newSet.contains(current.data)) {
				newSet.remove(current.data);
			} else {
				newSet.add(current.data);
			}
			symDifferenceHelper(current.leftChild, newSet);
			symDifferenceHelper(current.rightChild, newSet);
		}
	}

	public int getMin() {
		Node temp = root;
		while (temp.leftChild != null) {
			temp = temp.leftChild;
		}
		return temp.data;

	}

	public int getMax() {
		Node temp = root;
		while (temp.rightChild != null) {
			temp = temp.rightChild;
		}
		return temp.data;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{ ");
		inOrderToString(root, sb);
		sb.append(" }");
		return sb.toString();
	}

	private void inOrderToString(Node current, StringBuffer sb) {
		if (current != null) {
			inOrderToString(current.leftChild, sb);
			if (current.leftChild != null) {
				sb.append(", ");

			}

			sb.append(current.data);

			if (current.rightChild != null) {
				sb.append(", ");
			}
			inOrderToString(current.rightChild, sb);
		}
	}

	private void copyOnto(IntegerSet copyTo) {
		Node current = this.root;
		copyHelper(current, copyTo);

	}

	public IntegerSet copy() {
		IntegerSet copy = new IntegerSet();
		Node current = this.root;
		copyHelper(current, copy);
		return copy;
	}

	private void copyHelper(Node current, IntegerSet copy) {
		if (current != null) {
			copy.add(current.data);
			copyHelper(current.leftChild, copy);
			copyHelper(current.rightChild, copy);
		}
	}

	private class Node {
		private int data;
		private Node leftChild;
		private Node rightChild;

		private int leftHeight;
		private int rightHeight;

		public Node(int data) {
			this.data = data;
		}
	}
}