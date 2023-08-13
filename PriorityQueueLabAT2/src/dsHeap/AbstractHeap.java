package dsHeap;

import java.util.ArrayList;

public abstract class AbstractHeap<T extends Comparable<? super T>> {

    protected ArrayList<T> heap;

    public AbstractHeap() {
        clear();

    }

    public void add(T item) {
        heap.add(item);
        reHeapUp(getSize());

    }

    public T remove() {
        T toReturn = heap.get(1);
        swap(1, getSize());
        heap.remove(getSize());
        reHeapDown(1);
        return toReturn;
    }

    public void clear() {
        heap = new ArrayList<T>();
        heap.add(null);

    }

    public int getSize() {
        return heap.size() - 1;

    }

    public boolean isEmpty() {
        return getSize() == 0;

    }

    protected T getLeftChild(int idx) {
        int leftIdx = idx * 2;
        if (leftIdx <= getSize()) {
            return heap.get(leftIdx);
        }
        return null;
    }

    protected T getLRightChild(int idx) {
        int rightIdx = idx * 2 + 1;
        if (rightIdx <= getSize()) {
            return heap.get(rightIdx);
        }
        return null;
    }

    protected void swap(int idx1, int idx2) {
        T temp = heap.get(idx1);
        heap.set(idx1, heap.get(idx2));
        heap.set(idx2, temp);

    }

    protected abstract void reHeapUp(int idx);

    protected abstract void reHeapDown(int idx);

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 1; i < heap.size(); i++) {
            sb.append(heap.get(i));
            if (i < heap.size() - 1) {
                sb.append(", ");

            }
        }
        sb.append("]");
        return sb.toString();
    }

}
