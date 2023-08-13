package ds.heap;

public class MinHeap<T extends Comparable<? super T>> extends AbstractHeap<T> {

    @Override
    protected void reHeapUp(int idx) {
        int parentIdx = idx / 2;
        T currentObj = heap.get(idx);
        while (idx > 1) {
            T parentObj = heap.get(parentIdx);
            int res = currentObj.compareTo(parentObj);
            if (res < 0) {
                swap(idx, parentIdx);
            } else {
                break;
            }
            idx = parentIdx;
            parentIdx = idx / 2;
        }
    }

    @Override
    protected void reHeapDown(int idx) {
        int size = getSize();
        T currentObj = null;
        if (!isEmpty()) {
            currentObj = heap.get(idx);
        }
        while (idx <= size) {
            T leftChild = getLeftChild(idx);
            T rightChild = getLRightChild(idx);
            int swapIdx = -1;
            // If currentNode has a left and righ child
            if (leftChild != null && rightChild != null) {
                int result = leftChild.compareTo(rightChild);
                if (result <= 0) {
                    result = leftChild.compareTo(currentObj);
                    if (result < 0) {
                        swapIdx = idx * 2;
                    }
                } else {
                    result = rightChild.compareTo(currentObj);
                    if (result < 0) {
                        swapIdx = idx * 2 + 1;
                    }
                }
                // If the currentNode has just left child.
            } else if (leftChild != null) {
                int result = leftChild.compareTo(currentObj);
                if (result < 0) {
                    swapIdx = idx * 2;
                }
            }
            if (swapIdx != -1) {
                swap(idx, swapIdx);
                idx = swapIdx;
            } else {
                break;
            }
        }
    }

}
