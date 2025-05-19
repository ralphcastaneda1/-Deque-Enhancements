import org.junit.jupiter.api.*;

//import java.util.Comparator;
import java.util.Iterator;

import deque.LinkedListDeque61B;

import static com.google.common.truth.Truth.assertThat;
//import static com.google.common.truth.Truth.assertWithMessage;


public class LinkedListDeque61BTest {

    @Test
    public void testIterLinkedList() {
        LinkedListDeque61B<Integer> deq = new LinkedListDeque61B<>();
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
    public void testEqualLinkedList() {
        LinkedListDeque61B<Integer> deq1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> deq2 = new LinkedListDeque61B<>();
        deq1.addLast(1);
        deq1.addLast(2);
        deq2.addLast(1);
        deq2.addLast(2);
        assertThat(deq1).isEqualTo(deq2);
    }
}
