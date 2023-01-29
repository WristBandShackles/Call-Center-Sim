import java.util.Arrays;
import java.util.NoSuchElementException;

public class HeapPriorityQueue<E extends Comparable<E>> {
    private E[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public HeapPriorityQueue() {
        elementData = (E[]) new Comparable<?>[10];
        size = 0;
    }


    public void add(E value) {
        if (size + 1 >= elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }

        elementData[size + 1] = value;

        int index = size + 1;
        boolean found = false;
        while (!found && hasParent(index)) {
            int parent = parent(index);
            if (elementData[index].compareTo(elementData[parent]) < 0) {
                swap(elementData, index, parent(index));
                index = parent(index);
            } else {
                found = true;
            }
        }

        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elementData[1];
    }

    public E remove() {
        E result = peek();

        elementData[1] = elementData[size];
        size--;

        int index = 1;
        boolean found = false;
        while (!found && hasLeftChild(index)) {
            int left = leftChild(index);
            int right = rightChild(index);
            int child = left;
            if (hasRightChild(index) &&
                    elementData[right].compareTo(elementData[left]) < 0) {
                child = right;
            }

            if (elementData[index].compareTo(elementData[child]) > 0) {
                swap(elementData, index, child);
                index = child;
            } else {
                found = true;
            }
        }

        return result;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String result = "[";
        if (!isEmpty()) {
            result += elementData[1];
            for (int i = 2; i <= size; i++) {
                result += ", " + elementData[i];
            }
        }
        return result + "]";
    }


    private int parent(int index) {
        return index / 2;
    }

    private int leftChild(int index) {
        return index * 2;
    }

    private int rightChild(int index) {
        return index * 2 + 1;
    }

    private boolean hasParent(int index) {
        return index > 1;
    }

    private boolean hasLeftChild(int index) {
        return leftChild(index) <= size;
    }

    private boolean hasRightChild(int index) {
        return rightChild(index) <= size;
    }

    private void swap(E[] a, int index1, int index2) {
        E temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }
}

