package deque;

// import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

//import static org.eclipse.jetty.webapp.MetaDataComplete.True;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public String toString() {
        return toList().toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Deque61B<?> other2) {
            if (this.size() != other2.size()) {
                return false;
            }
            Iterator<?> otherIterator = other2.iterator();
            Iterator<T> thisIterator = this.iterator();
            while (thisIterator.hasNext()) {
                T wizItem = thisIterator.next();
                Object wizOther = otherIterator.next();
                if (!wizItem.equals(wizOther)) {
                    return false;
                }

            }
            return true;
        }
        return false;
    }


    private class LinkedListDequeIterator implements Iterator<T> {
        private Node p;

        public LinkedListDequeIterator() {
            p = sentinel.next;

        }

        @Override
        public boolean hasNext() {
            return p != sentinel;
        }

        @Override
        public T next() {
            T returnItem = p.item;
            p = p.next;
            return returnItem;
        }
    }


    public class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

    }

    @Override
    public void addFirst(T x) {
        Node firstNode = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = firstNode;
        sentinel.next = firstNode;
        size += 1;

    }

    @Override
    public void addLast(T x) {
        Node lastNode = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = lastNode;
        sentinel.prev = lastNode;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel.next;
        while (p != sentinel) {
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        size--;

        return firstNode.item;

    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;
        size--;

        return lastNode.item;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    public T getRecursiveHelper(int index, Node p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }
}

