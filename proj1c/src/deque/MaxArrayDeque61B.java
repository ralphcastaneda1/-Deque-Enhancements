package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        Iterator<T> wizPos = this.iterator();
        T max = wizPos.next();
        while (wizPos.hasNext()) {
            T wizItem = wizPos.next();
            if (wizItem != null) {
                if (max == null || c.compare(wizItem, max) > 0) {
                    max = wizItem;
                }
            }
        }
        return max;
    }

}




    

