
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.Queue.MyQueue;
import uy.edu.um.prog2.adt.Queue.MyQueueImpl;
import uy.edu.um.prog2.adt.Queue.EmptyQueueException;
import org.junit.jupiter.api.Assertions;


public class MyQueueTest {

    @Test
    void enqueue_dequeue() {
        MyQueue<Integer> queueTest = new MyQueueImpl<>();

        queueTest.enqueue(3);
        queueTest.enqueue(5);
        queueTest.enqueue(8);
        queueTest.enqueue(11);
        queueTest.enqueue(15);

        try {
            Integer eliminado = queueTest.dequeue();
            Assertions.assertEquals(15,eliminado);
        } catch (EmptyQueueException e) {
            System.out.println("Queue Empty");
        }
    }
}
