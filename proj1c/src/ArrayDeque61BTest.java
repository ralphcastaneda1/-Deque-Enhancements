import org.junit.jupiter.api.*;

//import java.util.Comparator;
import java.util.Iterator;

import deque.ArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
//import static com.google.common.truth.Truth.assertWithMessage;



public class ArrayDeque61BTest {

    @Test
    public void testIter() {
        ArrayDeque61B<Integer> deq = new ArrayDeque61B<>();
        deq.addFirst(1);
        deq.addLast(2);
        Iterator<Integer> iter = deq.iterator();
        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.next()).isEqualTo(1);
        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.next()).isEqualTo(2);
        assertThat(iter.hasNext()).isFalse();
    }

    @Test
    public void testEquals() {
        ArrayDeque61B<Integer> deq1 = new ArrayDeque61B<>();
        ArrayDeque61B<Integer> deq2 = new ArrayDeque61B<>();
        deq1.addLast(1);
        deq1.addLast(2);
        deq2.addLast(1);
        deq2.addLast(2);
        assertThat(deq1).isEqualTo(deq2);

    }



}
