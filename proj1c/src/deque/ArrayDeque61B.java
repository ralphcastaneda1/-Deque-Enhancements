package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private int newResize;

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }


    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
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
            Iterator<T> thisIterator = this.iterator();
            Iterator<?> otherIterator = other2.iterator();
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

    @Override
    public String toString() {
        return toList().toString();
    }


    @Override
    // @source: From https://www.geeksforgeeks.org/math-floormod-method-in-java/
    // @source: From https://stackoverflow.com/questions/52377246/the-difference-between-math-floormod-and-in-java
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size++;

    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;

    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size++;

    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        int index = 0;
        while (index < size) {
            list.add(get(index));
            index++;
        }
        return list;
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
        if (size > 0) {
            return size;
        } else {
            return 0;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size--;

        if (items.length > newResize && size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;

    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = (nextLast - 1 + items.length) % items.length;
        T item = items[nextLast];
        items[nextLast] = null;
        size--;

        if (items.length > newResize && size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int getIndex = nextFirst + index + 1;
        return items[getIndex % items.length];

    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int wizPos;
        private int iterCount;

        public ArrayDequeIterator() {
            wizPos = Math.floorMod(nextFirst + 1, items.length);
            iterCount = 0;
        }
        @Override
        public boolean hasNext() {
            return iterCount < size;
        }

        @Override
        public T next() {
            T returnItem = items[wizPos];
            wizPos = Math.floorMod(wizPos + 1, items.length);
            iterCount++;
            return returnItem;
        }

    }
}

