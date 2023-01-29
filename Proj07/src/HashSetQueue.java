public class HashSetQueue<E> {
    private HashSet<E> hashSet;
    private QueueNode<E> front;

    public HashSetQueue() {
        hashSet = new HashSet<>();
        front = null;
    }

    public void add(E value) {
        if (!(hashSet.contains(value))) {
            if (front == null) {
                front = new QueueNode<>(value);
            }
            else {
                QueueNode<E> current = front;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = new QueueNode<>(value);
            }
            hashSet.add(value);
        }
    }

    public E remove() {
        if (!(isEmpty())) {
            E data = front.data;
            front = front.next;
            hashSet.remove(data);
            return data;
        }
        return null;
    }

    public E peek() {
        return front.data;
    }

    public boolean isEmpty() {
        return hashSet.isEmpty();
    }

    public void clear() {
        hashSet.clear();
    }

    public int size() {
        return hashSet.size();
    }

    public String toString() {
        String result = "[";
        if (front == null) {
            return result + "null]";
        }
        else {
            QueueNode<E> current = front;
            result += current.data;
            while (current.next != null) {
                current = current.next;
                result += ", " + current.data;
            }
        }
        return result + "]";
    }

    private static class QueueNode<E> {
        public E data;
        public QueueNode<E> next;

        public QueueNode(E data) {
            this(data, null);
        }

        public QueueNode(E data, QueueNode<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
