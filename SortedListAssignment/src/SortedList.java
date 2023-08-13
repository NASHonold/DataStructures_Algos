
import java.lang.Comparable;

public class SortedList<T extends Comparable<? super T>> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    // Constructor
    public SortedList() {
        clear();
    }

    /**
     * returns true if list contains no Nodes
     * 
     * @return
     */
    public boolean isEmpty() { // DONE
        return size == 0;
    }

    /**
     * Resets the SortedList object and is also called in the constructor
     */
    public void clear() { // DONE
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * returns the current size of SortedList
     * 
     * @return
     */
    public int getSize() { // DONE
        return size;
    }

    /**
     * adds a node with given data in its sorted position
     * 
     * @param data
     */
    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (size == 0) {
            head = new Node<T>(data);
            tail = head;
            size++;
            ;
        } else {
            Node<T> temp = head;
            for (int i = 0; i < size; i++) {
                if (i == 0 && newNode.data.compareTo(temp.data) <= 0) {
                    insertStart(data);
                    break;
                } else if ((newNode.data.compareTo(temp.data) <= 0)) {
                    insertAt(i, data);
                    break;
                } else if (i == size - 1) {
                    insertEnd(data);
                    break;
                }
                temp = temp.next;
            }
        }
    }

    /**
     * Will remove a Node in the SortedList object given
     * a specific index and return its data
     * 
     * @param index
     * @return
     */
    public T removeAt(int index) {
        if (isEmpty()) {
            throw new EmptyCollectionException("Cannot remove from an empty collection!");
        }
        validateIndex(index, 0, size - 1);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<T> toRemove = getNode(index);
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
            toRemove.next = null;
            toRemove.prev = null;
            size -= 1;
            return toRemove.data;

        }
    }

    /**
     * This will retrieve the data from the node at the given index
     * without changing the list
     * 
     * @param index
     * @return
     */
    public T get(int index) {
        if (isEmpty()) {
            throw new EmptyCollectionException("Can't get node from an empty collection!");
        }
        validateIndex(index, 0, size - 1);

        Node<T> temp = getNode(index);
        return temp.data;
    }

    /**
     * Will return true if data passed to method is
     * contained in SortedList
     * 
     * @param data
     * @return
     */
    public boolean contains(T data) {
        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(data)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * If data passed to method is in SortedList
     * will return the index it is located at
     * 
     * @param data
     * @return
     */
    public int find(T data) {
        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(data)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    /**
     * Returns the number of instances the data passed to
     * method is present in the SortedList
     * 
     * @param data
     * @return
     */
    public int count(T data) {
        int count = 0;
        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(data)) {
                count++;
            }
            temp = temp.next;
        }
        return count;
    }

    /**
     * Will compare the values in SortedList object argument
     * and will remove all Nodes that are equivalent to any Nodes in
     * the argument
     * 
     * @param otherList
     */
    public void removeAll(SortedList<T> otherList) {
        Node<T> firstTemp = this.head;
        Node<T> secondTemp = otherList.head;
        int indexA = 0;
        int indexB = 0;
        int secondSize = otherList.getSize() - 1;
        while ((indexA + indexB) < ((this.size + otherList.getSize()))) {
            if (firstTemp.data.compareTo(secondTemp.data) == 0) {
                if (indexA < this.size - 1) {
                    firstTemp = firstTemp.next;
                    removeAt(indexA);

                } else {
                    removeAt(indexA);
                    indexA++;
                    break;
                }
                continue;
            }
            while (firstTemp.data.compareTo(secondTemp.data) < 0 && indexA < this.size - 1) {
                firstTemp = firstTemp.next;
                indexA++;
            }
            while (firstTemp.data.compareTo(secondTemp.data) > 0 && indexB < secondSize) {
                secondTemp = secondTemp.next;
                indexB++;
            }
            if (firstTemp.data.compareTo(secondTemp.data) > 0 && indexB == secondSize) {
                break;
            }
        }
    }

    /**
     * returns string representing the SortedList
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        if (!isEmpty()) {
            Node<T> temp = head;
            for (int i = 0; i < size - 1; i++) {
                sb.append(temp.data.toString());
                sb.append(", ");
                temp = temp.next;
            }
            sb.append(temp.data.toString());

        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Will return Node with given index
     * 
     * @param index
     * @return
     */
    private Node<T> getNode(int index) {
        Node<T> temp = index <= size / 2 ? head : tail;
        if (temp == head) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;

            }
            return temp;
        } else {
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
            return temp;
        }
    }

    /**
     * Will insert a Node object into SortedList given an index
     * that is NOT the head or tail
     * 
     * @param index
     * @param data
     */
    private void insertAt(int index, T data) {
        Node<T> newNode = new Node<T>(data);
        Node<T> temp = getNode(index);
        temp.prev.next = newNode;
        newNode.prev = temp.prev;
        temp.prev = newNode;
        newNode.next = temp;
        size++;
    }

    /**
     * Inserts Node at head of SortedList
     * 
     * @param data
     */
    private void insertStart(T data) {
        Node<T> newNode = new Node<T>(data);
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
        size++;

    }

    /**
     * Inserts Node at tail of SortedList
     * 
     * @param data
     */
    private void insertEnd(T data) {
        Node<T> newNode = new Node<T>(data);
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    /**
     * Removes last Node from SortedList
     * 
     * @return
     */
    private T removeLast() {
        if (isEmpty()) {
            throw new EmptyCollectionException("Can't remove from an empty collection.");
        }
        Node<T> temp = tail;
        if (size == 1) {
            clear();
        } else {
            tail = tail.prev;
            tail.next.prev = null;
            tail.next = null;
            size -= 1;
        }
        return temp.data;
    }

    /**
     * Will remove Node at head of SortedList
     * 
     * @return
     */
    private T removeFirst() {
        if (isEmpty()) {
            throw new EmptyCollectionException("Can't remove from an empty collection.");
        }
        Node<T> temp = head;
        if (size == 1) {
            clear();
        } else {

            head = head.next;
            head.prev.next = null;
            head.prev = null;
            size -= 1;
        }
        return temp.data;

    }

    /**
     * Checks that given index is present in SortedList
     * 
     * @param index
     * @param lowerBound
     * @param upperBound
     */
    private void validateIndex(int index, int lowerBound, int upperBound) {
        if (!(index >= lowerBound && index <= upperBound)) {
            throw new IndexOutOfBoundsException(
                    String.format("Index must be between %d and %d", lowerBound, upperBound));
        }

    }

    // =============== class Node ================
    private class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        // Constructor
        public Node(E data) {
            this.data = data;

        }

    }
}
