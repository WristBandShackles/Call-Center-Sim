public class HashSet<E> {
    private static final double MAX_LOAD_FACTOR = 0.75;
    private HashEntry<E>[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public HashSet() {
        elementData = (HashEntry<E>[])new HashEntry<?>[10];
        size = 0;
    }

    public void add(E value) {
        if (!contains(value)) {
            if (loadFactor() >= MAX_LOAD_FACTOR) {
                rehash();
            }

            int bucket = hashFunction(value);
            elementData[bucket] = new HashEntry<E>(value, elementData[bucket]);
            size++;
        }
    }

    public void clear() {
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    public boolean contains(E value) {
        int bucket = hashFunction(value);
        HashEntry<E> current = elementData[bucket];
        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(E value) {
        int bucket = hashFunction(value);
        if (elementData[bucket] != null) {
            if (elementData[bucket].data.equals(value)) {
                elementData[bucket] = elementData[bucket].next;
                size--;
            } else {
                HashEntry<E> current = elementData[bucket];
                while (current.next != null && !current.next.data.equals(value)) {
                    current = current.next;
                }

                if (current.next != null && current.next.data.equals(value)) {
                    current.next = current.next.next;
                    size--;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public String toString() {
        String result = "[";
        boolean first = true;
        if (!isEmpty()) {
            for (int i = 0; i < elementData.length; i++) {
                HashEntry<E> current = elementData[i];
                while (current != null) {
                    if (!first) {
                        result += ", ";
                    }
                    result += current.data;
                    first = false;
                    current = current.next;
                }
            }
        }
        return result + "]";
    }

    private int hashFunction(E value) {
        return Math.abs(value.hashCode()) % elementData.length;
    }

    private double loadFactor() {
        return (double) size / elementData.length;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        HashEntry<E>[] oldElementData = elementData;
        elementData = (HashEntry<E>[])new HashEntry<?>[2 * oldElementData.length];
        size = 0;

        for (int i = 0; i < oldElementData.length; i++) {
            HashEntry<E> current = oldElementData[i];
            while (current != null) {
                add(current.data);
                current = current.next;
            }
        }
    }

    private static class HashEntry<T> {
        public T data;
        public HashEntry<T> next;

        public HashEntry(T data) {
            this(data, null);
        }

        public HashEntry(T data, HashEntry<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
