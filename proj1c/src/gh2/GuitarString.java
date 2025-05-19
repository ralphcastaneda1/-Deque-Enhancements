package gh2;
import deque.LinkedListDeque61B;
import deque.Deque61B;

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque61B<Double> buffer;
    private int capacity;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        this.capacity = (int) Math.round(SR / frequency);
        this.buffer = new LinkedListDeque61B<>();
        for (int i = 0; i < capacity; i++) {
            this.buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        while (!buffer.isEmpty()) {
            buffer.removeFirst();
        }
        for (int i = 0; i < capacity; i++) {
            double r = Math.random() - 0.5;
            buffer.addLast(r);

        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        if (buffer == null) {
            return;
        }
        double first = buffer.removeFirst();
        double next = buffer.get(0);
        double newDouble = DECAY * 0.5 * (first + next);
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        if (buffer.isEmpty()) {
            return 0;
        }
        return buffer.get(0);
    }
}
